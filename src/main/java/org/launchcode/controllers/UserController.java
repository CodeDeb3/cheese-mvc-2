package org.launchcode.controllers;

import org.launchcode.models.User;
import org.launchcode.models.UserData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
    public String index(Model model){
//                        HttpServletRequest request) {
//        model.addAttribute("username", request.getAttribute("username"));

        model.addAttribute("users", UserData.getAll());
        model.addAttribute("title","My Users" );
        return "users/index";
    }



    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {

        model.addAttribute("title", "Add User");
        // add empty user so our table has something to workwith
        model.addAttribute(new User());
        return "users/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAdd(Model model, @ModelAttribute @Valid User newUser,
                             Errors errors, String verify) {

//        if (errors.hasErrors()) {
//            model.addAttribute("title", "User Login");
//            model.addAttribute("User", user.getUsername());
//        }
//            if (user.getPassword().equals(verify)) {
//            return "users/index";
//        }
//        else {
//            model.addAttribute("error", "Passwords do not match.");
//        }
//        return "users/add";
//        public String processAddUserForm(@RequestParam String verify, @ModelAttribute @Valid User newUser, Errors errors, Model model) {

            // Here, we see checking for validation errors, as well as checking the verification password together
            // This is done to reduce code repeat, and ensure that we can return both validation errors,
            // as well as our password verification error together

            if (errors.hasErrors() || !newUser.getPassword().equals(verify)) {
                model.addAttribute("title", "Add User");
                if (!newUser.getPassword().equals(verify)) {
                    model.addAttribute("verifyError", "Password did not match verification.");
                }
                return "users/add";
            }
// make sure to add newUser and redirect to /user/
        UserData.add(newUser);
        return "redirect:";

    }

    @RequestMapping(value = "edit/{userId}", method = RequestMethod.GET)
    public String displayEditUserForm(@PathVariable("userId") int userId, Model model) {
        model.addAttribute("title", "Edit User");
        model.addAttribute(UserData.getById(userId));
        return "users/edit";
    }

    @RequestMapping(value = "edit/{userId}", method = RequestMethod.POST)
    public String processEditUserForm(@ModelAttribute @Valid User editedUser, Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Edit User");
            return "users/edit";
        }

        UserData.remove(editedUser.getUserId());
        UserData.add(editedUser);

        // this is needed to go to view after editing users!!!
        model.addAttribute("users", UserData.getAll());
        model.addAttribute("title", "User List");


        return "/users/index";
    }

}


