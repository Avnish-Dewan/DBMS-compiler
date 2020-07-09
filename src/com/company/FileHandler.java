package com.company;

import java.io.*;
import java.util.HashMap;

public class FileHandler {
    BufferedWriter writer;
    BufferedReader reader;
    File file;

    final String path = "./DBFiles/";

    public FileHandler(){

    }

    public FileHandler(String fileName) throws IOException {

        System.out.println(path+fileName+".db");

        file = new File(path+fileName+".db");
        file.getParentFile().mkdirs();
        if(!file.createNewFile()){
            throw new IOException("Table Already Exists");
        }
        writer = new BufferedWriter(new FileWriter(file,true));
        reader = new BufferedReader(new FileReader(file));
    }

    public void setAttributes(String attributes) throws IOException {
        String[] attr = attributes.split(",");


        writer.write(attributes.replaceAll(",","|")+"\n");
        writer.flush();
    }

    public void insertData(String tableName,String values) throws Exception{
        File file = new File(path+tableName+".db");

        if(file.exists()){

            reader = new BufferedReader(new FileReader(file));

            String attributes = reader.readLine();

            int numOfAttr = Utils.countCharacters(attributes,'|');
            int numOfValues = Utils.countCharacters(values,',');

            if(numOfAttr!=numOfValues){
                throw new Exception("Number of Attributes Different");
            }

            writer  = new BufferedWriter(new FileWriter(file,true));
            writer.write(values.replaceAll(",","|")+"\n");
            writer.flush();
        }else{
            throw new Exception("Table Doesn't Exist");
        }
    }

    public String getData(String table,String attributes) throws Exception {
        file = new File(path+table+".db");
        HashMap<String ,Integer> map = new HashMap<>();
        reader = new BufferedReader(new FileReader(file));
        String[] values = attributes.split(",");
        attributes = attributes.replaceAll(",","|");

        String attrFile = reader.readLine();

        String[] attr = attrFile.split("\\|");


        for (String value : values) {
            for (int j = 0; j < attr.length; j++) {
                if (value.equalsIgnoreCase(attr[j])) {
                    map.put(value, j);
                }
            }
        }

        if(map.size() != values.length){
            throw new Exception("No Column with specified name(s) found");
        }else{
            String data = "";
            String fileData = reader.readLine();
            while (fileData!=null){

                String[] currValues = fileData.split("\\|");

                for (String val: values) {
                    if(map.containsKey(val)){
                        data+=currValues[map.get(val)]+"|";
                    }
                }
                data = data.substring(0,data.length()-1);
                data+="\n";
//                System.out.println(data+" "+fileData );
                fileData = reader.readLine();
            }
            System.out.println(attributes);
            System.out.print(data);
            return attributes+"\n"+data;
        }
    }

    public void close() throws IOException{
        writer.close();
        reader.close();
    }

}
