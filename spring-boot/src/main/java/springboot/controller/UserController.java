package springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springboot.models.User;
import springboot.servise.UserService;


@Controller
public class UserController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/")
    public String allUsers(Model model) {
        model.addAttribute("users", userService.allUsers());
        return "users";
    }

    @GetMapping(value = "/edit")
    public String editPage(@RequestParam("id") Long id, Model model) {
        model.addAttribute("user", userService.getById(id));
        return "editPage";
    }

    @PatchMapping(value = "/edit")
    public String editUser(@ModelAttribute User user) {
        userService.edit(user);
        return "redirect:/";
    }

    @PostMapping(value = "/delete")
    public String deleteUser(@RequestParam("id") Long id) {
        User user = userService.getById(id);
        userService.delete(user);
        return "redirect:/";
    }


    @GetMapping(value = "/add")
    public String addPage(Model model) {
        model.addAttribute("users", new User());
        return "add";
    }

    @PostMapping(value = "/add")
    public String addUser(@ModelAttribute User user) {
        userService.add(user);
        return "redirect:/";
    }

}
