package org.launchcode.models;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Debbie on 3/23/2017.
 */
public class Cheese {


    // things i want to store

    @NotNull
    @Size(min=3, max=15)
    private String name;

    @NotNull
    @Size(min=1, message="Description must not be empty")
    private String description;

    /**
     * Min and Max provide validation for low and high limits to our rating
     */
    @Max(5)
    @Min(1)
    private int rating;


    // add type of cheese with only a few values
    // add a default hard. go to models CheeseType type = CheeseType.HARD

    private CheeseType type;

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

    public CheeseType getType() {
        return type;
    }

    public void setType(CheeseType type) {
        this.type = type;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
