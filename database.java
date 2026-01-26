/* I certify that the HTML file I am submitting is all my own work. 
None of it is copied from any source or any person.
Signed:Dominic Stencel  Date:1/18/2026
Author: Dominic Stencel

Date: 1/18/2026
Class: CSC422 - Software Engineering
Project: Assignment 1 
File Name: StencelCSC422Assignment1\database.java
Sources: 
Used for syntax help since I haven't used java in awhile
https://www.w3schools.com/java
 */

// Imports
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class database {
    // Static variable to track the next available ID
    static int nextID = 0;
    
    // Maximum number of pets allowed in database
    static final int MAX_PETS = 5;

    public static void main(String[] args) {
        // Creating Scanner object
        Scanner scanner = new Scanner(System.in);

        // Creating Object for user input option
        int option;

        // Using Hashmap as the database for fast performance and key-value pairs
        // Each key (id) will have a value of (Arraylist [name, age])
        // Want Array list to be name then age, so we know 0 = name & 1 = age
        
        // Load database from file (or start with empty if file doesn't exist)
        HashMap<Integer, ArrayList<String>> dataBase = fileHandler.loadDatabase();

        // Set nextID to be one more than the highest ID in the loaded database
        nextID = fileHandler.getMaxID(dataBase) + 1;
        
        /* Create do-while loop to display database options until
        user selects exit */
        do{
            // Display datbase options
            System.out.println("Pet Database Program");
            System.out.println("");
            System.out.println("1) View all pets");
            System.out.println("2) Add more pets");
            System.out.println("3) Update an existing pet");
            System.out.println("4) Delete an existing pet");
            System.out.println("5) Search pets by name");
            System.out.println("6) Search pets by age");
            System.out.println("7) Exit Program");
            System.out.println("=====================");
            System.out.print("Enter your choice: ");

            // Call scanner object to read next int
            option = scanner.nextInt();

            switch(option) {
                case 1 -> displayDataBase(dataBase);
                
                case 2 -> addPet(dataBase, scanner);

                case 3 -> updatePet(dataBase, scanner);

                case 4 -> deletePet(dataBase, scanner);

                case 5 -> searchPetsByName(dataBase, scanner);

                case 6 -> searchPetsByAge(dataBase, scanner);

                case 7 -> {
                    fileHandler.saveDatabase(dataBase);
                    System.out.println("Goodbye!");
                }
                
                default -> System.out.println("Invalid Choice! Enter Number between 1 & 7:");
            }
        }
        while (option != 7);

        // Closing scanner object
        scanner.close();
    }

    /////////////////////////
    //  displayDataBase()  //
    /////////////////////////
    public static void displayDataBase(HashMap<Integer, ArrayList<String>> dataBase) {
        
        // Display Header for database
        System.out.println("+-----------------------------+");
        System.out.println("| ID | Name             | Age |");
        System.out.println("+-----------------------------+");

        // Loop through database hashmap and display each pet
        for (int id : dataBase.keySet()) {
            // Fetching the id's arraylist containing name and age
            ArrayList<String> info = dataBase.get(id);
            String name = info.get(0);
            String age = info.get(1);

            // Displaying single pet line
            // Using format specifier to get even spaces everytime
            System.out.printf("| %-2d | %-16s | %-3s |\n", id, name, age);
        }

        // Displaying footer
        System.out.println("+-----------------------------+"); 
    }

    /////////////////////////
    //      addPet()       //
    /////////////////////////
    public static void addPet(HashMap<Integer, ArrayList<String>> dataBase,
         Scanner scanner) {

        System.out.println("Enter pet information (name and age on each line).");
        System.out.println("Type 'done' when finished.");
        
        // Clear any leftover input
        scanner.nextLine();
        
        // Loop to add multiple pets
        String input = "";
        while (true) {
            // Check if database is full
            if (dataBase.size() >= MAX_PETS) {
                System.out.println("Database is full! Maximum " + MAX_PETS + " pets allowed.");
                break;
            }
            
            // Asking for users input on pets name and age
            System.out.print("Add pet (name, age): ");
            input = scanner.nextLine().trim();

            // Check if user typed done to exit loop
            if (input.equalsIgnoreCase("done")) {
                break;
            }
            
            // Split input by spaces
            String[] parts = input.split("\\s+");
            
            // Check if user provided both name and age
            if (parts.length < 2) {
                System.out.println("Error: " + input + " is not a valid input.");
                continue;
            }
            
            String petName = parts[0];
            String petAgeStr = parts[1];
            
            // Validate age is a number
            int petAge;
            try {
                petAge = Integer.parseInt(petAgeStr);
            } catch (NumberFormatException e) {
                System.out.println("Error: " + petAgeStr + " is not a valid age.");
                continue;
            }
            
            // Validate age is between 1 and 20
            if (petAge < 1 || petAge > 20) {
                System.out.println("Error: " + petAgeStr + " is not a valid age.");
                continue;
            }

            // Adding pet to database
            dataBase.put(nextID, new ArrayList<>(Arrays.asList(petName, String.valueOf(petAge))));
            
            System.out.println("Pet added successfully!");
            
            // increment next id
            nextID++;
        }
        
        System.out.println("=====================");
        System.out.println("");
    }

    /////////////////////////
    //  searchPetsByName() //
    /////////////////////////
    public static void searchPetsByName(HashMap<Integer, ArrayList<String>> dataBase,
         Scanner scanner) {

        // Asking for users input on pets name to search
        System.out.print("Enter pet name to search: ");
        String searchName = scanner.next();

        // Display Header for search results
        System.out.println("+-----------------------------+");
        System.out.println("| ID | Name             | Age |");
        System.out.println("+-----------------------------+");

        // Loop through database hashmap and display each pet that matches the name
        for (int id : dataBase.keySet()) {
            // Fetching the id's arraylist containing name and age
            ArrayList<String> info = dataBase.get(id);
            String name = info.get(0);
            String age = info.get(1);

            // Check if name matches search term
            if (name.equalsIgnoreCase(searchName)) {
                // Displaying single pet line
                // Using format specifier to get even spaces everytime
                System.out.printf("| %-2d | %-16s | %-3s |\n", id, name, age);
            }
        }

        // Displaying footer
        System.out.println("+-----------------------------+"); 
    }

    /////////////////////////
    //  searchPetsByAge()  //
    /////////////////////////
    public static void searchPetsByAge(HashMap<Integer, ArrayList<String>> dataBase,
         Scanner scanner) {

        // Asking for users input on pets age to search
        System.out.print("Enter pet age to search: ");
        String searchAge = scanner.next();

        // Display Header for search results
        System.out.println("+-----------------------------+");
        System.out.println("| ID | Name             | Age |");
        System.out.println("+-----------------------------+");

        // Loop through database hashmap and display each pet that matches the age
        for (int id : dataBase.keySet()) {
            // Fetching the id's arraylist containing name and age
            ArrayList<String> info = dataBase.get(id);
            String name = info.get(0);
            String age = info.get(1);

            // Check if age matches search term
            if (age.equals(searchAge)) {
                // Displaying single pet line
                // Using format specifier to get even spaces everytime
                System.out.printf("| %-2d | %-16s | %-3s |\n", id, name, age);
            }
        }

        // Displaying footer
        System.out.println("+-----------------------------+"); 
    }

    /////////////////////////
    //     updatePet()     //
    /////////////////////////
    public static void updatePet(HashMap<Integer, ArrayList<String>> dataBase,
         Scanner scanner) {

        // Prompt user for pet ID to update
        System.out.print("Enter pet ID to update: ");
        int petID = scanner.nextInt();
        scanner.nextLine(); // Clear the newline
        
        // Validate that the ID exists in the database
        if (!dataBase.containsKey(petID)) {
            System.out.println("Error: ID " + petID + " does not exist in the database.");
            System.out.println("=====================");
            return;
        }
        
        // Store existing pet info
        ArrayList<String> petInfo = dataBase.get(petID);

        // Prompt user for new name and age
        System.out.print("Enter new pet name and age: ");
        String input = scanner.nextLine().trim();
        
        // Split input by spaces
        String[] parts = input.split("\\s+");
        
        // Check if user provided both name and age
        if (parts.length < 2) {
            System.out.println("Error: " + input + " is not a valid input.");
            System.out.println("=====================");
            return;
        }
        
        String newName = parts[0];
        String newAgeStr = parts[1];
        
        // Validate age is a number
        int newAge;
        try {
            newAge = Integer.parseInt(newAgeStr);
        } catch (NumberFormatException e) {
            System.out.println("Error: " + newAgeStr + " is not a valid input.");
            System.out.println("=====================");
            return;
        }
        
        // Validate age is between 1 and 20
        if (newAge < 1 || newAge > 20) {
            System.out.println("Error: Age must be between 1 and 20.");
            System.out.println("=====================");
            return;
        }

        // Update the pet in the database
        dataBase.put(petID, new ArrayList<>(Arrays.asList(newName, String.valueOf(newAge))));

        // Confirmation message
        System.out.println(petInfo.get(0) + " has been updated to "
         + newName + ", age " + newAge + ".");
        
        // Footer
        System.out.println("=====================");
    }

    /////////////////////////
    //     deletePet()     //
    /////////////////////////
    public static void deletePet(HashMap<Integer, ArrayList<String>> dataBase,
         Scanner scanner) {
        
        // Prompt user for pet ID to delete
        System.out.print("Enter pet ID to delete: ");
        int petID = scanner.nextInt();

        // Validate that the ID exists in the database
        if (!dataBase.containsKey(petID)) {
            System.out.println("Error: ID " + petID + " does not exist in the database.");
            return;
        }

        // Remove the pet from the database
        ArrayList<String> removedPet = dataBase.remove(petID);

        // Confirmation message
        System.out.println(removedPet.get(0) + " " + removedPet.get(1) +
         " has been removed from the database.");
    }
}