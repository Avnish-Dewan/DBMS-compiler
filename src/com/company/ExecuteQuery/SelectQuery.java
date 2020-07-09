package com.company.ExecuteQuery;

import com.company.FileHandler;

public class SelectQuery implements Query {

    @Override
    public void executeQuery(String query) throws Exception {
        query = query.replaceAll("\\s+,\\s+",",");
        if(query.charAt(query.length()-1) == ';'){
            query = query.substring(0,query.length()-1);
        }
        String[] words = query.split(" ");
        if(words[2].equalsIgnoreCase("FROM")){

            FileHandler fileHandler = new FileHandler();
            String data = fileHandler.getData(words[3],words[1]);

            if(words.length > 4){
                if(words[4].equalsIgnoreCase("WHERE")){
                    String condition = null;
                    if(words.length>6){
                        condition=words[5]+words[6]+words[7];
                    }else{
                        condition = words[5];
                    }
                    System.out.println(condition);
                }
            }

        }else{
            throw new Exception("Invalid Select Query");
        }

        System.out.println(query);
    }
}
