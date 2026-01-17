// Imports
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class database {
    public static void main(String[] args) {
        // Creating Scanner object
        Scanner scanner = new Scanner(System.in);

        // Creating Object for user input option
        int option;

        // Using Hashmap as the database for fast performance and key-value pairs
        // Each key (id) will have a value of (Arraylist [name, age])
        // Want Array list to be name then age, so we know 0 = name & 1 = age
        HashMap<Integer, ArrayList<String>> dataBase = new HashMap<>();

        // Variable to store the running total for ID
        int nextID = 6;

        // Test inputs for the database 
        dataBase.put(1, new ArrayList<>(Arrays.asList("Max", "3")));
        dataBase.put(2, new ArrayList<>(Arrays.asList("Bella", "5")));
        dataBase.put(3, new ArrayList<>(Arrays.asList("Charlie", "2")));
        dataBase.put(4, new ArrayList<>(Arrays.asList("Luna", "7")));
        dataBase.put(5, new ArrayList<>(Arrays.asList("Cooper", "4")));

        /* Create do-while loop to display database options until
        user selects exit */
        do{
            // Display datbase options
            System.out.println("Pet Database Program");
            System.out.println("");
            System.out.println("1) View all pets");
            System.out.println("2) Add more pets");
            System.out.println("7) Exit Program");
            System.out.println("Pet Database Program");
            System.out.println("=====================");
            System.out.print("Enter your choice: ");

            // Call scanner object to read next int
            option = scanner.nextInt();

            switch(option) {
                case 1 -> displayDataBase(dataBase);
                
                case 2 -> addPet(dataBase, nextID);

                case 7 -> System.out.println("Goodbye!");
                
                default -> System.out.println("Invalid Choice! Enter Number between 1 & 7:");
            }
                    }
        while (option != 7);
    }

    // Function to display the entire database
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

    // Function to add a pet to the data base
     public static void addPet(HashMap<Integer, ArrayList<String>> dataBase, Integer nextID) {
        // Creating Scanner object
        Scanner scanner = new Scanner(System.in);

        // Asking for users input on pets name
        System.out.println("Pet name: ");
        String petName = scanner.nextLine();

        // Asking for users input on pets age
        System.out.println("Pet age: ");
        String petAge = scanner.nextLine();

        // Adding pet to database
        dataBase.put(nextID, new ArrayList<>(Arrays.asList(petName, petAge)));

        // increment next id
        nextID ++;

     }



}