package com.mountblue.blog_application.controllers;

import com.mountblue.blog_application.DTO.PostWithTags;
import com.mountblue.blog_application.DTO.SearchRequestDTO;
import com.mountblue.blog_application.services.BlogUserService;
import com.mountblue.blog_application.services.PostService;
import com.mountblue.blog_application.services.TagService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

    private final PostService postService;
    private final TagService tagService;
    private final BlogUserService blogUserService;

    public HomeController(PostService postService, TagService tagService, BlogUserService blogUserService) {
        this.postService = postService;
        this.tagService = tagService;
        this.blogUserService = blogUserService;
    }

    @GetMapping("/home")
    public String home(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) Long author,
            @RequestParam(required = false) Long tag,
            @RequestParam(required = false) String sort,
            @RequestParam(defaultValue = "0") int page,
            Model model) {

        Page<PostWithTags> posts = postService.filterPosts(
                new SearchRequestDTO(search, author, tag, sort, page),
                10
        );

        model.addAttribute("posts", posts.getContent());
        model.addAttribute("page", page);
        model.addAttribute("hasNext", posts.hasNext());
        model.addAttribute("hasPrevious", posts.hasPrevious());

        model.addAttribute("search", search);
        model.addAttribute("selectedAuthor", author);
        model.addAttribute("selectedTag", tag);
        model.addAttribute("sort", sort);

        // For dropdowns
        model.addAttribute("authors", blogUserService.getAllUsers());
        model.addAttribute("tags", tagService.getAllTags());

        return "home";
    }
}