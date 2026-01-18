/* Pets class so user can create objects and be
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