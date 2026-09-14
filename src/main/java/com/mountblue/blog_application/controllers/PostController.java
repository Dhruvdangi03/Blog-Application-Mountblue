package com.mountblue.blog_application.controllers;

import com.mountblue.blog_application.DTO.PostWithTags;
import com.mountblue.blog_application.entities.Comment;
import com.mountblue.blog_application.entities.Post;
import com.mountblue.blog_application.services.CommentService;
import com.mountblue.blog_application.services.PostService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PostController {
    private final PostService postService;
    private final CommentService commentService;

    @Autowired
    public PostController(PostService postService, CommentService commentService) {
        this.postService = postService;
        this.commentService = commentService;
    }

    @GetMapping("/post/{id}")
    public String postById(@PathVariable long id, Model model){
        PostWithTags post = postService.getPostById(id);
        List<Comment> comments = commentService.getCommentsByPostId(id);

        model.addAttribute("post", post);
        model.addAttribute("comments", comments);
        return "post";
    }

    @DeleteMapping("/post/{id}")
    public String deletePostById(@PathVariable long id){
        postService.deletePostById(id);
        return "redirect:/home";
    }

    @GetMapping("/post/edit/{id}")
    public String showEditPost(
            @PathVariable long id,
            Model model) {

        PostWithTags post = postService.getPostById(id);

        model.addAttribute("post", post);

        return "edit-post";
    }

    @PostMapping("/post/edit/{id}")
    public String updatePost(
            @PathVariable long id,
            @ModelAttribute Post post) {

        postService.updatePost(id, post);

        return "redirect:/post/" + id;
    }
}
