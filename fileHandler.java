/* I certify that the HTML file I am submitting is all my own work. 
None of it is copied from any source or any person.
Signed:Dominic Stencel  Date:1/26/2026
Author: Dominic Stencel

Date: 1/26/2026
Class: CSC422 - Software Engineering
Project: Assignment 2 
File Name: StencelCSC422Assignment1\fileHandler.java
Sources: 
Used for file I/O syntax help
https://www.w3schools.com/java/java_files.asp
 */

// Imports
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class fileHandler {
    
    // File name constant
    private static final String FILE_NAME = "pets_database.txt";

    /////////////////////////
    //    loadDatabase()   //
    /////////////////////////
    public static HashMap<Integer, ArrayList<String>> loadDatabase() {
        HashMap<Integer, ArrayList<String>> dataBase = new HashMap<>();
        
        try {
            File file = new File(FILE_NAME);
            
            // Check if file exists and is not empty
            if (!file.exists() || file.length() == 0) {
                System.out.println("No existing database found. Starting with empty database.");
                return dataBase;
            }

            // Create Reader to read file
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            
            // Read each line from the file
            while ((line = reader.readLine()) != null) {
                // Skip empty lines
                if (line.trim().isEmpty()) {
                    continue;
                }
                
                // Split the line by comma
                String[] parts = line.split(",");
                
                // Validate that we have 3 parts (id, name, age)
                if (parts.length == 3) {
                    int id = Integer.parseInt(parts[0].trim());
                    String name = parts[1].trim();
                    String age = parts[2].trim();
                    
                    // Add to database
                    dataBase.put(id, new ArrayList<>(Arrays.asList(name, age)));
                }
            }
            
            reader.close();
            System.out.println("Database loaded successfully from " + FILE_NAME);
            System.out.println(dataBase.size() + " pets loaded.");
            System.out.println("");
            
        } catch (FileNotFoundException e) {
            System.out.println("No existing database file found. Starting fresh.");
        } catch (IOException e) {
            System.out.println("Error reading database file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error parsing database file. Starting with empty database.");
        }
        
        return dataBase;
    }

    /////////////////////////
    //    saveDatabase()   //
    /////////////////////////
    public static void saveDatabase(HashMap<Integer, ArrayList<String>> dataBase) {
        try {
            // Create FileWriter to write to file
            FileWriter writer = new FileWriter(FILE_NAME);
            
            // Loop through database and write each pet to file
            for (int id : dataBase.keySet()) {
                ArrayList<String> info = dataBase.get(id);
                String name = info.get(0);
                String age = info.get(1);
                
                // Write in format: id,name,age
                writer.write(id + "," + name + "," + age + "\n");
            }
            
            writer.close();
            System.out.println("Database saved successfully to " + FILE_NAME);
            
        } catch (IOException e) {
            System.out.println("Error saving database: " + e.getMessage());
        }
    }

    /////////////////////////
    //  getMaxID()         //
    /////////////////////////
    // Helper method to find the highest ID in database for nextID tracking
    public static int getMaxID(HashMap<Integer, ArrayList<String>> dataBase) {
        int maxID = 0; // Default starting value
        
        for (int id : dataBase.keySet()) {
            if (id > maxID) {
                maxID = id;
            }
        }
        
        return maxID;
    }
}