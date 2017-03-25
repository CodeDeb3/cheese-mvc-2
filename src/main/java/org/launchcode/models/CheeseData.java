package org.launchcode.models;

import java.util.ArrayList;

/**
 * Created by Debbie on 3/23/2017.
 */

// remove controller to models package from cheesce controller

public class CheeseData {
    static ArrayList<Cheese> cheeseList = new ArrayList<Cheese>();

    //getAll
    public static ArrayList<Cheese> getAll(){
        return cheeseList;
    }
    //add
    public static void add(Cheese newCheese){
        cheeseList.add(newCheese);
    }

    // remove given object from list (then go to controller update model

    public static void remove(int id) {
        Cheese cheeseToRemove = getbyId(id);
        cheeseList.remove(cheeseToRemove);
    }

    // getbyId
    // loop thru loop and get the cheese

    public static Cheese getbyId(int id) {

        Cheese theCheese = null;

        for (Cheese candidateCheese: cheeseList) {
            if (candidateCheese.getCheeseId() == id) {
                theCheese = candidateCheese;
            }
        }
        return theCheese;
    }
}
