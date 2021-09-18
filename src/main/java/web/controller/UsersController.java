package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

@Controller
public class UsersController {

    private UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String userTablePage(Model model) {
        System.out.println("Загрузка начальной страницы");
        model.addAttribute("users", userService.getAllUsers());

        return "users/index";
    }

    @PostMapping("/users")
    public String create(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/";
    }

    @GetMapping("/users/remove/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.removeUserById(id);
        return "redirect:/";
    }

    @GetMapping("/users/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "users/new";
    }

    @GetMapping("/users/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id){
        model.addAttribute("user", userService.getUserById(id));
        return "users/edit";
    }

    @PatchMapping("/users/{id}")
    public String update(@ModelAttribute("updateUser") User user
            , @PathVariable("id") Long id) {
        userService.updateUserById(id, user);
        return "redirect:/";
    }
}
