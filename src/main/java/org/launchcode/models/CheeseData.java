package org.launchcode.models;

import java.util.ArrayList;

/**
 * Created by Debbie on 3/23/2017.
 */

// remove controller to models package from cheesce controller

public class CheeseData {
    static ArrayList<Cheese> cheeses = new ArrayList<Cheese>();

    //getAll
    public static ArrayList<Cheese> getAll(){
        return cheeses;
    }
    //add
    public static void add(Cheese newCheese){
        cheeses.add(newCheese);
    }

    // remove given object from list (then go to controller update model

    public static void remove(int id) {
        Cheese cheeseToRemove = getbyId(id);
        cheeses.remove(cheeseToRemove);
    }

    // getbyId
    // loop thru loop and get the cheese

    public static Cheese getbyId(int id) {

        Cheese theCheese = null;

        for (Cheese candidateCheese: cheeses) {
            if (candidateCheese.getCheeseId() == id) {
                theCheese = candidateCheese;
            }
        }
        return theCheese;
    }
}
