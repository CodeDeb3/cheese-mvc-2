package org.launchcode.models;

/**
 * Created by Debbie on 3/23/2017.
 */
public class Cheese {

    // things i want to store
    private String name;
    private String description;
    private int cheeseId; // integer identifier to be a unique class
    private static int nextId = 1; // to use to initialize for every new cheese we make

    public Cheese(String name, String description) {
        this(); // first line to call cheese id constructor must b first line
        this.name = name; // this means the field name not the class name
        this.description = description;
    }
    public Cheese() {
        cheeseId = nextId;
        nextId++;
    }
    public int getCheeseId() {
        return cheeseId;
    }

    public void setCheeseId(int cheeseId) {
        this.cheeseId = cheeseId;
    }

    public static int getNextId() {
        return nextId;
    }

    public static void setNextId(int nextId) {
        Cheese.nextId = nextId;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
