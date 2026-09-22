package com.mountblue.blog_application.restControllers;

import com.mountblue.blog_application.DTO.CommentDTO;
import com.mountblue.blog_application.services.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentRestController {
    private final CommentService commentService;

    public CommentRestController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/rest/comments/add")
    public ResponseEntity<?> addComments(CommentDTO commentDTO){
        commentService.createComment(commentDTO.getComment(), commentDTO.getPostId());

        return ResponseEntity.ok("Your Comment is Created !");
    }

    @GetMapping("/rest/comments/{postId}")
    public ResponseEntity<?> getCommentsByPostId(@PathVariable long postId){
        return ResponseEntity.ok(commentService.getCommentsByPostId(postId));
    }

    @PutMapping("/rest/comments/{Id}")
    public ResponseEntity<?> updateCommentsById(@PathVariable long id, String content){
        return ResponseEntity.ok(commentService.updateComment(id, content));
    }

    @DeleteMapping("/rest/comments/{Id}")
    public ResponseEntity<?> deleteCommentsById(@PathVariable long id){
        commentService.deleteComment(id);

        return ResponseEntity.ok("Comment Deleted Successfully !");
    }
}
