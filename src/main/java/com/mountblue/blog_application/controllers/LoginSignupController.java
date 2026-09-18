package com.mountblue.blog_application.controllers;

import com.mountblue.blog_application.DTO.SignupRequest;
import com.mountblue.blog_application.services.BlogUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginSignupController {
    private final BlogUserService blogUserService;

    public LoginSignupController(BlogUserService blogUserService) {
        this.blogUserService = blogUserService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/signup")
    public String signUp() {
        return "signup";
    }

    @PostMapping("/signup")
    public String createUser(@ModelAttribute SignupRequest signupRequest) {

        blogUserService.saveBlogUser(signupRequest);

        return "redirect:/login";
    }
}
