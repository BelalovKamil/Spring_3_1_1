package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import web.service.UserService;

@Controller
public class IndexController {
    private final UserService userService;

    public IndexController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String startPage() {
        return "start_page";
    }

    @GetMapping("/reset")
    public String factoryReset() {
        userService.factoryReset();
        return "redirect:/";
    }
}
