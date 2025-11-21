package org.example;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class TaskProvider {
// 1 READ JSON FILE
    public static String readJson(String filename) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filename)));
    }
//2 Parse JSON STRING - EXTRACT VALUE BY KEY
    public static String getValue(String json, String key){
        String pattern = "\"" + key + "\":";
        int start = json.indexOf(pattern);
        if (start == -1) return "";

        start += pattern.length();
        // Handle null
        if (json.substring(start).trim().startsWith("null")){
            return "null";
        }

        // Handle string value
        start = json.indexOf("\"",start) + 1;
        int end = json.indexOf("\"", start );
        return json.substring(start, end);
    }
// 3 Change values - replace value for a Key
public static String changeValue(String json, String key, String newValue){
    String pattern = "\"" + key + "\":";
    int start = json.indexOf(pattern);
    if (start == -1) return json;
    start += pattern.length();
    //Find
    int valueStart, valueEnd;

    if(json.substring(start).trim().startsWith("null")){
        //replacing null
        valueStart = json.indexOf("null", start);
        valueEnd = valueStart + 4;
    } else {
        valueStart = json.indexOf("\"", start);
        valueEnd = json.indexOf("\"", valueStart + 1) + 1;
    }
    // Build new value with quotes (or null)
    String replacement;
    if(newValue.equals("null")) {
        replacement = "null";
    } else {
        replacement = "\"" + newValue + "\"";
    }
    return json.substring(0, valueStart) + replacement + json.substring(valueEnd);
}

//4 Save json file
    public static void saveJson(String filename,String json) throws IOException{
        Files.write(Paths.get(filename),json.getBytes());
    }

    public void useStatus(TaskControl control) {
        EnumTask status = control.getStat();
        System.out.println("Status: " + status);
    }

    public static void updateTask(String newDesc) throws IOException {
        String filename = "TaskStorage.json";
        String json = readJson(filename);
        json = changeValue(json, "desc", newDesc);
        json = changeValue(json, "status", "TODO");
        json = changeValue(json, "updatedAt", "2025-11-20");
        saveJson(filename, json);
        System.out.println("✔ JSON updated");
    }

    public static void updateEnum(Enum ee) throws IOException {
        String filename = "TaskStorage.json";
        String json = readJson(filename);
        json = changeValue(json, "status", String.valueOf(ee));
        saveJson(filename, json);
        System.out.println("✔ JSON updated");
    }

    public static void printerTask(String newDesc){

        try {
            String filename = "TaskStorage.json";

            // STEP 1: Read the JSON
            String json = readJson(filename);
            System.out.println("Original JSON:");
            System.out.println(json);
            // STEP 2: Parse - Get current values
            System.out.println("\nCurrent values:");
            System.out.println("\n-----------------------------------------------------:");
            System.out.println("ID: " + getValue(json, "id"));
            System.out.println("Desc: " + getValue(json, "desc"));
            System.out.println("Status: " + getValue(json, "status"));
            System.out.println("UpdatedAt: " + getValue(json, "updatedAt"));
            System.out.println("\n-----------------------------------------------------:");
            // STEP 4: Save back to file
            saveJson(filename, json);
            System.out.println("\nModified JSON:");
            System.out.println(json);
            System.out.println("\nSaved to " + filename);

        }catch (IOException e){
            e.printStackTrace();
        }
}


}
