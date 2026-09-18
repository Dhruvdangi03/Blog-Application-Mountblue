package com.mountblue.blog_application.controllers;

import com.mountblue.blog_application.entities.Comment;
import com.mountblue.blog_application.services.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/comments/add")
    public String addComment(
            @RequestParam Long postId,
            @RequestParam String content) {

        commentService.createComment(content, postId);

        return "redirect:/post/" + postId;
    }

    @GetMapping("/comments/edit/{id}")
    public String editComment(
            @PathVariable Long id,
            Model model) {

        Comment comment = commentService.getCommentById(id);

        model.addAttribute("comment", comment);

        return "edit-comment";
    }

    @PostMapping("/comments/edit/{id}")
    public String updateComment(
            @PathVariable Long id,
            @RequestParam String content) {

        Comment comment = commentService.getCommentById(id);

        comment.setComment(content);

        commentService.saveComment(comment);

        return "redirect:/post/" + comment.getPost().getId();
    }

    @PostMapping("/comments/delete/{id}")
    public String deleteComment(@PathVariable Long id) {

        Comment comment = commentService.getCommentById(id);

        Long postId = comment.getPost().getId();

        commentService.deleteComment(id);

        return "redirect:/post/" + postId;
    }
}
