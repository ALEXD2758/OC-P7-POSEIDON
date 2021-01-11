package com.alex.poseidon.controllers;

import com.alex.poseidon.config.ValidPassword;
import com.alex.poseidon.models.UserModel;
import com.alex.poseidon.services.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

@Controller
public class UserController {


    private static final Logger logger = LogManager.getLogger("UserController");

    @Autowired
    private UserService userService;

    /**
     * Render the view user/list
     * Adds attribute user to the model, containing all users available in DB
     * Only for user with Authority/Role : "ADMIN"
     *
     * @param model Model Interface, to add attributes to it
     * @return a string to the address "user/list", returning the associated view
     * with attribute
     */
    @RolesAllowed("ADMIN")
    @RequestMapping("/user/list")
    public String home(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        logger.info("GET /user/list : OK");
        return "user/list";
    }

    /**
     * Render the view user/add
     * Adds attribute user to the model, containing a new UserModel
     * Only for user with Authority/Role : "ADMIN"
     *
     * @param model for the Model Interface, to add attributes to it
     * @return a string to the address "user/add", returning the associated view
     * with attribute
     */
    @RolesAllowed("ADMIN")
    @GetMapping("/user/add")
    public String addUser(Model model) {
        model.addAttribute("user", new UserModel());
        logger.info("GET /user/add : OK");
        return "user/add";
    }

    /**
     * Save new user to the table user if BindingResult has no errors and if user does not exist
     * Add Flash Attribute with success message
     * Add attribute user to the model, containing all users available in DB
     * Only for user with Authority/Role : "ADMIN"
     *
     * @param user the UserModel with annotation @Valid (for the possible constraints)
     * @param result to represent binding results
     * @param model the Model Interface, to add attributes to it
     * @param ra the RedirectAttributes to redirect attributes in redirect
     * @return a string to the address "user/list", returning the associated view,
     * with attributes if no errors in BindingResult
     * @return a string to the address "user/add", returning the associated view,
     *  if there is an error in BindingResult
     */
    @RolesAllowed("ADMIN")
    @PostMapping("/user/validate")
    public String validate(@Valid @ValidPassword @ModelAttribute("user") UserModel user, BindingResult result,
                           Model model, RedirectAttributes ra) {
        if (userService.checkIfUserExistsByUsername(user.getUsername())) {
            ra.addFlashAttribute("ErrorUserExistantMessage", "The user is already registered");
            return "redirect:/user/add";
        }
        String password = user.getNonHashedPassword();
        if (!result.hasErrors()) {
            Pbkdf2PasswordEncoder encoder = new Pbkdf2PasswordEncoder();
            user.setPassword(encoder.encode(password));


            userService.saveUser(user);
            ra.addFlashAttribute("successSaveMessage", "The user was successfully added");
            model.addAttribute("users", userService.getAllUsers());

            user.setNonHashedPassword("");
            logger.info("POST /user/validate : OK");
            return "redirect:/user/list";
        }
        logger.info("POST /user/validate : NOK");
        return "user/add";
    }

    /**
     * Render the view user/update with the chosen id in a model attribute
     * with the associated data of the chosen ID
     * Add attribute user to the model
     * Only for user with Authority/Role : "ADMIN"
     *
     * @param id the int of the ID chosen by the admin
     * @param model the Model Interface, to add attributes to it
     * @return a string to the address "user/update", returning the associated view
     * with attribute (if no Exception)
     */
    @RolesAllowed("ADMIN")
    @GetMapping("/user/update/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        try {
            if (userService.checkIfUserExistsById(id) == false) {
                return "redirect:/user/list";
            }
            UserModel user = userService.getUserById(id);
            user.setPassword("");
            model.addAttribute("user", user);
            logger.info("GET /user/update : OK");
        } catch (Exception e) {
            logger.info("/user/update : NOK " + "Invalid user ID " + id);
        }
        return "user/update";
    }

    /**
     * Update existing user to the table user if BindingResult has no errors
     * Add Flash Attribute with success message
     * Add attribute users to the model, containing all users available in DB
     * Only for user with Authority/Role : "ADMIN"
     *
     * @param user the UserModel with annotation @Valid (for the possible constraints)
     * @param result to represent binding results
     * @param model the Model Interface, to add attributes to it
     * @param ra the RedirectAttributes to redirect attributes in redirect
     * @return a string to the address "user/list", returning the associated view,
     * with attributes
     */
    @RolesAllowed("ADMIN")
    @PostMapping("/user/update/{id}")
    public String updateUser(@PathVariable("id") int id, @Valid @ModelAttribute("user") UserModel user,
                             BindingResult result, Model model, RedirectAttributes ra) {
        if (userService.checkIfUserExistsById(id) == false) {
            logger.info("POST /user/update : Non existent id");
            return "redirect:/user/list";
        }
        if (result.hasErrors()) {
            logger.info("POST /user/update : NOK");
            return "user/update";
        }

        String password = user.getNonHashedPassword();
        Pbkdf2PasswordEncoder encoder = new Pbkdf2PasswordEncoder();
        user.setPassword(encoder.encode(password));
        user.setId(id);
        userService.saveUser(user);
        ra.addFlashAttribute("successUpdateMessage", "The user was successfully updated");
        model.addAttribute("users", userService.getAllUsers());

        logger.info("POST /user/update : OK");
        return "redirect:/user/list";
    }

    /**
     * Delete existing user from the table user
     * Add Flash Attribute with success message
     * Add attribute users to the model, containing all users available in DB
     * Only for user with Authority/Role : "ADMIN"
     *
     * @param id the int of the ID chosen by the admin
     * @param model the Model Interface, to add attributes to it
     * @param ra the RedirectAttributes to redirect attributes in redirect
     * @return a string to the address "user/list", returning the associated view,
     * with attributes
     */
    @RolesAllowed("ADMIN")
    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") int id, Model model, RedirectAttributes ra) {
        try {
            if (userService.checkIfUserExistsById(id) == false) {
                logger.info("GET /user/delete : Non existent id");
                return "redirect:/user/list";
            }
            userService.deleteUserById(id);
            model.addAttribute("users", userService.getAllUsers());
            ra.addFlashAttribute("successDeleteMessage", "This user was successfully deleted");

            logger.info("/user/delete : OK");
        } catch (Exception e) {
            ra.addFlashAttribute("errorDeleteMessage", "Error during deletion of this user");
            logger.info("/user/delete : NOK " + "Invalid user ID " + id);
        }
        return "redirect:/user/list";
    }
}