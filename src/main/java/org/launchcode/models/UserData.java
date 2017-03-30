package org.launchcode.models;

import java.util.ArrayList;

/**
 * Created by Debbie on 3/25/2017.
 */
public class UserData {


    private static ArrayList<User> users = new ArrayList<>();


    // get all

    public static ArrayList<User> getAll() {
        return users;
    }

    // add user

    public static void add(User newUser) {
        users.add(newUser);
    }

    // remove
    public static void remove(int id) {
        User userToRemove = getById(id);
        users.remove(userToRemove);
    }

    // get by Id

    public static User getById(int id) {
        User foundUser = null;

        for (User theUser : users) {
            if (theUser.getUserId() == id) {
                foundUser = theUser;
            }
        }
        return foundUser;
    }

}


