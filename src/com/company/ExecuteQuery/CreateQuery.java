package com.company.ExecuteQuery;

import com.company.FileHandler;

public class CreateQuery implements Query{

    public CreateQuery(){

    }

    @Override
    public void executeQuery(String query) throws Exception {
        int openBracket = query.indexOf('(');
        int closeBracket = query.lastIndexOf(')');

        if(openBracket == -1 || closeBracket == -1){
            throw new Exception("Not a Valid Create Query");
        }

        String startQuery = query.substring(0,openBracket);

        String[] words = startQuery.split(" ");

        if(words[1].equalsIgnoreCase("TABLE")){
            System.out.println("Creating Table");

            FileHandler fileHandler = new FileHandler(words[2]);

            String attributes = query.substring(openBracket+1,closeBracket);
            fileHandler.setAttributes(attributes);

            System.out.println(attributes);

            System.out.println("Created Table");
        }else{
            throw new Exception("Invalid Create Command");
        }
    }
}
