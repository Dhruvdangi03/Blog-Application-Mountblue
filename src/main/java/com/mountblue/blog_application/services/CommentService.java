package com.mountblue.blog_application.services;

import com.mountblue.blog_application.entities.Comment;
import com.mountblue.blog_application.entities.Post;
import com.mountblue.blog_application.repositories.CommentRepo;
import com.mountblue.blog_application.repositories.PostRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    private final CommentRepo commentRepo;
    private final PostRepo postRepo;
    private final BlogUserService blogUserService;

    public CommentService(CommentRepo commentRepo, PostRepo postRepo, BlogUserService blogUserService) {
        this.commentRepo = commentRepo;
        this.postRepo = postRepo;
        this.blogUserService = blogUserService;
    }

    public List<Comment> getCommentsByPostId(long id) {
        return commentRepo.findByPostId(id);
    }

    public void createComment(String content, Long postId) {
        Comment comment = new Comment();
        comment.setComment(content);
        comment.setCommenter(blogUserService.getCurrentUser());
        comment.setEmail(blogUserService.getCurrentUser().getEmail());
        comment.setCreatedAt(LocalDateTime.now());
        comment.setUpdatedAt(LocalDateTime.now());

        Optional<Post> post = postRepo.findById(postId);
        if(post.isEmpty())
            return;

        comment.setPost(post.get());

        commentRepo.save(comment);
    }

    public void deleteComment(Long id) {
        Comment comment = commentRepo.findById(id).orElseThrow(() -> new RuntimeException("Not able to find comment by id inside CommentService"));

        if(!comment.getCommenter().getUsername().equals(blogUserService.getCurrentUser().getUsername())
                && !blogUserService.getCurrentUser().getRole().equals("ADMIN")){
            throw new RuntimeException("You don't have the permission to delete this comment !");
        }

        commentRepo.deleteById(id);
    }

    public Comment getCommentById(Long id) {
        return commentRepo.findById(id).orElseThrow(() -> new RuntimeException("Not able to find comment by id inside CommentService"));
    }

    public Comment updateComment(long id, String content) {
        Comment comment = getCommentById(id);

        if(!comment.getCommenter().getUsername().equals(blogUserService.getCurrentUser().getUsername())
                && !blogUserService.getCurrentUser().getRole().equals("ADMIN")){
            throw new RuntimeException("You don't have the permission to update this comment !");
        }

        comment.setComment(content);
        return commentRepo.save(comment);
    }
}
