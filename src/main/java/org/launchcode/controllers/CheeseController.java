package org.launchcode.controllers;

import org.launchcode.models.CheeseData;
import org.launchcode.models.CheeseType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import org.launchcode.models.Cheese;

import javax.validation.Valid;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("cheese")
public class CheeseController {

    // array list allow values with same name vs hashmap does not
    // remove controller to models package to cheeseData in models
//    static HashMap<String, String> cheeses = new HashMap<>();


    // Request path: /cheese
    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("cheeses", CheeseData.getAll());
        model.addAttribute("title", "My Cheeses");

        return "cheese/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddCheeseForm(Model model) {
        model.addAttribute("title", "Add Cheese");
        /* add an empty object and passing the skeleton into view
         *we can use the properties of it to help render the form
         **/
        model.addAttribute(new Cheese());
        model.addAttribute("cheeseTypes", CheeseType.values());
        return "cheese/add";
    }
/* model binding removes too much code when having an object with many fields
    have springboot automatically create a cheese object when method called

*    public String processAddCheeseForm(@RequestParam String cheeseName,
//        @RequestParam String cheeseDescription) {
//        Cheese newCheese = new Cheese (cheeseName, cheeseDescription);

    /* modelattribute will look at "cheese class" in cheese.java models
     and create an new cheese object with those
    properties with getters and setters POST request any data that matches will be
    inserted into the class when controller receives the object will be created
    with full data
    **/
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddCheeseForm(@ModelAttribute @Valid Cheese newCheese,
                                       Errors errors, Model model) {

// this returns the form inside the view but types need to be populated by addAttr
        if (errors.hasErrors()){
            model.addAttribute("title", "Adding Cheese");
            model.addAttribute("cheeseTypes", CheeseType.values());

            return "cheese/add";
        }
        /*
        *  Springboot when identifies model type cheese will create new
        *  object using default contructor which is what initializes our ID in cheese
        *  model  does not know which contructors will use but just default
         *  for all our classes
        *
        * Cheese newCheese = new Cheese();
        * newCheese.setName(Request.getParameter("name"); look for parm and set it
        * newCheese.setDescription(Request.getParmeter("description"));
        * name and description need to match field names in data
        *
        **/
            CheeseData.add(newCheese);
//            CheeseData.add(type.newCheese);
//        cheeses.put(cheeseName, cheeseDescription);
        return "redirect:";
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveCheeseForm(Model model) {
        model.addAttribute("cheeses", CheeseData.getAll());// no sending cheese names but all arraylist
        model.addAttribute("title", "Remove Cheese");
        return "cheese/remove";
    }

//     update to integer from string to keep integer ids rather than string
// goto removetemp
    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveCheeseForm(@RequestParam int[] cheeseIds) {

        // in hashmap you can remove but in array with just a string
        for (int cheeseId : cheeseIds) {
            CheeseData.remove(cheeseId);
        }

        return "redirect:";
    }

    @RequestMapping(value = "edit/{cheeseId}", method=RequestMethod.GET)
    public String displayEditForm(Model model, @PathVariable("cheeseId") int cheeseId){
        Cheese currentCheese = CheeseData.getbyId(cheeseId);
        model.addAttribute("cheese", currentCheese);
        model.addAttribute("cheeseTypes", CheeseType.values());

        return "cheese/edit";
    }

    @RequestMapping(value = "edit/{currentCheese.cheeseId}", method=RequestMethod.POST)
    public String processEditForm(Model model, @ModelAttribute @Valid Cheese currentCheese,
                                  Errors errors){
        if (errors.hasErrors()) {
            model.addAttribute("title", "Edit Cheese");
            model.addAttribute("cheeseTypes", CheeseType.values());

            return "cheese/edit";
        }

// the modelAttribute built the newcheese above
// Cheese currentCheese = CheeseData.getbyId(cheeseId);

//        currentCheese.setName(name);
//        currentCheese.setDescription(description);

        CheeseData.remove(currentCheese.getCheeseId());
        CheeseData.add(currentCheese);

        // this is needed to go to view after editing cheeses!!!
        model.addAttribute("cheeses", CheeseData.getAll());
        model.addAttribute("title", "My Cheeses");


        return "/cheese/index";
    }

}
