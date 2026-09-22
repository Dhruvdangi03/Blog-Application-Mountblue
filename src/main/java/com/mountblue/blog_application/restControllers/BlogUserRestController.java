package com.mountblue.blog_application.restControllers;

import com.mountblue.blog_application.services.BlogUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BlogUserRestController {
    private final BlogUserService blogUserService;

    public BlogUserRestController(BlogUserService blogUserService) {
        this.blogUserService = blogUserService;
    }

    @GetMapping("/rest/authors")
    public ResponseEntity<?> getAllAuthors(){
        return ResponseEntity.ok(blogUserService.getAllUsers());
    }
}
