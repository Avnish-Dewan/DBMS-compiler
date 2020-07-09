package com.company.ExecuteQuery;

import java.util.ArrayList;

public class ExecuteQuery {
    ArrayList<String> startingWords;

    public ExecuteQuery(){
        startingWords = new ArrayList<>();

        startingWords.add("insert");
        startingWords.add("select");
        startingWords.add("update");
        startingWords.add("delete");
        startingWords.add("create");

    }

    public void executeQuery(String query) throws Exception{

        String[] words = query.split(" ");



        if(startingWords.contains(words[0].trim())){
            Query command=null;
            switch (words[0]){
                case "create":
                    command = new CreateQuery();
                    break;
                case "select":
                    command = new SelectQuery();
                    break;
//                case "update":
//                    break;
//                case "delete":
//                    break;
                case "insert":
                    command = new InsertQuery();
                    break;
                default:
                    throw new Exception("Not a Valid command");
            }
            command.executeQuery(query);

        }else{
            throw new Exception("Not a Valid Query");
        }

    }


}
