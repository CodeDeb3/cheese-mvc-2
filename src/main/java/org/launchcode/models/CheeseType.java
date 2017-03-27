package org.launchcode.models;

/**
 * Created by Debbie on 3/27/2017.
 */
// enum instead of class with list of only possible values once initialized
    // we dont want types to be changed hence its a FINAL field (H,S,F)
public enum CheeseType {
    HARD("Hard"),
    SOFT("Soft"),
    FAKE("Fake");

    // create display name for enum

    private final String name;

    // constructor

    CheeseType(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
