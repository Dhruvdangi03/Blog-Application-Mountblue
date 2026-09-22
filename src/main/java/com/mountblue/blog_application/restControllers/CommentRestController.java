package com.mountblue.blog_application.restControllers;

import com.mountblue.blog_application.DTO.CommentDTO;
import com.mountblue.blog_application.services.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("/rest/comments")
public class CommentRestController {
    private final CommentService commentService;

    public CommentRestController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addComments(CommentDTO commentDTO){
        commentService.createComment(commentDTO.getComment(), commentDTO.getPostId());

        return ResponseEntity.ok("Your Comment is Created !");
    }

    @GetMapping("/{postId}")
    public ResponseEntity<?> getCommentsByPostId(@PathVariable long postId){
        return ResponseEntity.ok(commentService.getCommentsByPostId(postId));
    }

    @PutMapping("/{Id}")
    public ResponseEntity<?> updateCommentsById(@PathVariable long id, String content){
        try{
            commentService.updateComment(id, content);
        }catch (RuntimeException e){
            ResponseEntity.badRequest().body(e.getMessage());
        }

        return ResponseEntity.ok("Comment Update Successfully !");
    }

    @DeleteMapping("/{Id}")
    public ResponseEntity<?> deleteCommentsById(@PathVariable long id){
        try{
            commentService.deleteComment(id);
        }catch (RuntimeException e){
            ResponseEntity.badRequest().body(e.getMessage());
        }

        return ResponseEntity.ok("Comment Deleted Successfully !");
    }
}
