package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String userTablePage(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "all_user_info";
    }

    @PostMapping("/users")
    public String create(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/users/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "new";
    }


    @GetMapping("/users/remove/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.removeUserById(id);
        return "redirect:/admin";
    }

    @GetMapping("/users/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id){
        model.addAttribute("user", userService.getUserById(id));
        return "edit";
    }

    @PatchMapping("/users/{id}")
    public String update(@ModelAttribute("updateUser") User user
            , @PathVariable("id") Long id) {
        userService.updateUserById(id, user);
        return "redirect:/admin";
    }
}
