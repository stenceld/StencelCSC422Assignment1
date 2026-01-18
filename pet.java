/* I certify that the HTML file I am submitting is all my own work. 
None of it is copied from any source or any person.
Signed:Dominic Stencel  Date:1/18/2026
Author: Dominic Stencel

Date: 1/18/2026
Class: CSC422 - Software Engineering
Project: Assignment 1 
File Name: StencelCSC422Assignment1\pets.java
Sources: 
Used for syntax help since I haven't used java in awhile
https://www.w3schools.com/java/java_syntax.asp
 */


/*
 Pets class so user can create objects and
to perform CRUD features on them
*/

// Imports
import java.util.ArrayList;

public class pet {
    // Intitialize Variables for pets
    private int id;
    private String name;
    private int age;

    // Constructor
    public void Pet(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    /////////////////////////
    //       GETTERS       //
    /////////////////////////
    public int getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    /////////////////////////
    //       SETTERS       //
    /////////////////////////

    public void setName(String newName) {
        this.name = newName;
    }

    public void setAge(int newAge) {
        this.age = newAge;
        }

    // method to convert to ArrayList format for HashMap storage
    public ArrayList<String> toArrayList() {
        ArrayList<String> info = new ArrayList<>();
        info.add(this.name);
        info.add(String.valueOf(this.age));
        return info;
    }

}