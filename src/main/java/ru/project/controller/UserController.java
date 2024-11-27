package ru.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.project.model.User;
import ru.project.service.UserService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String getAllUsers(ModelMap model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping(value = "/new")
    public String newUser(ModelMap model) {
        model.addAttribute("user", new User());
        return "createuser";
    }

    @PostMapping(value = "/new")
    public String newUser(@ModelAttribute User user) {
        userService.saveUser(user);
        return "redirect:/users/";
    }

    @GetMapping(value = "/edit")
    public String editUser(@RequestParam long id, ModelMap model) {
        Optional<User> user = userService.getUserById(id);
        model.addAttribute("user", user.get());
        return "edituser";
    }

    @PostMapping(value = "/edit")
    public String editUser(@ModelAttribute User user) {
        userService.updateUser(user);
        return "redirect:/users/";
    }

    @GetMapping(value = "/delete")
    public String deleteUser(@RequestParam long id) {
        Optional<User> user = userService.getUserById(id);
        if (user != null) {
            userService.deleteUser(id);
        }
        return "redirect:/users/";
    }
}
