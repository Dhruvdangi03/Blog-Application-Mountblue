package com.mountblue.blog_application.controllers;

import com.mountblue.blog_application.DTO.PostRequest;
import com.mountblue.blog_application.services.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PostFormController {
    private final PostService postService;

    public PostFormController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/newpost")
    public String newPost(Model model) {
        model.addAttribute("postRequest", new PostRequest());
        return "post-form";
    }

    @PostMapping("/posts")
    public String createPost(@ModelAttribute PostRequest postRequest) {

        postService.createPost(postRequest);

        return "redirect:/home";
    }
}
