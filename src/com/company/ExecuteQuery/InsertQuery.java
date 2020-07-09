package com.company.ExecuteQuery;

import com.company.FileHandler;

public class InsertQuery implements Query{
    @Override
    public void executeQuery(String query) throws Exception {
        int openBracket = query.indexOf('(');
        int closeBracket = query.lastIndexOf(')');

        if(openBracket == -1 || closeBracket == -1){
            throw new Exception("Not a Valid Insert Query");
        }
        System.out.println(query);

        FileHandler fileHandler = new FileHandler();
        String startQuery = query.substring(0,openBracket);

        String[] words = startQuery.split(" ");

        if(words[1].equalsIgnoreCase("INTO") && words[3].equalsIgnoreCase("VALUES")){

            String values = query.substring(openBracket+1,closeBracket);
            fileHandler.insertData(words[2] , values);


        }else{
            throw new Exception("Invalid Insert Query");
        }
    }
}
