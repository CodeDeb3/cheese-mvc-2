package org.launchcode.controllers;

import org.launchcode.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created by Debbie on 3/23/2017.
 */

@Controller
@RequestMapping("users")
public class UserController {
//    public String add(Model model, User user, String verify);

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model, HttpServletRequest request) {

        model.addAttribute("username", request.getAttribute("username"));
        return "users/index";
    }



    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {

        model.addAttribute(new User());
        return "users/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAdd(Model model, @ModelAttribute @Valid User user,
                             Errors errors, String verify) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "User Login");
            model.addAttribute("User", user.getUsername());
        }

            if (user.getPassword().equals(verify)) {
            return "users/index";
        }
        else {
            model.addAttribute("error", "Passwords do not match.");
        }
        return "users/add";
    }
}


