package ua.nure.tkp.trainingday.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.nure.tkp.trainingday.entity.User;
import ua.nure.tkp.trainingday.entity.dto.UserDto;
import ua.nure.tkp.trainingday.service.LoginUserService;

@Controller
@RequestMapping("/auth")
public class LoginController {
    private final LoginUserService userService;

    @Autowired
    public LoginController(LoginUserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping(value = "/success")
    public String getSuccessPage() {
        return "redirect:/home";
    }

    @GetMapping(value = "/register")
    public String registerPage(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String processRegister(UserDto user) {
        userService.saveNewUser(user);
        return "redirect:/home";
    }
}
