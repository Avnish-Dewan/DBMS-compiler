package com.company;

import com.company.ExecuteQuery.ExecuteQuery;

import java.util.Scanner;

public class Main {

    public static void main(String[] args)  {

        ExecuteQuery executor = new ExecuteQuery();
        Scanner in  = new Scanner(System.in);
        System.out.println("Enter Your Query:");
        String query=in.nextLine();
        while(!query.equalsIgnoreCase("EXIT")){

            query = query.toLowerCase().trim().replaceAll("\\s"," ");
            System.out.println(query);
            try {
                executor.executeQuery(query);
            } catch (Exception e) {
//                e.printStackTrace();
                System.out.println(e.getMessage());
            }
            query = in.nextLine();
        }

    }
}
