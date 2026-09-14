package com.mountblue.blog_application.services;

import com.mountblue.blog_application.entities.BlogUser;
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

    public CommentService(CommentRepo commentRepo, PostRepo postRepo) {
        this.commentRepo = commentRepo;
        this.postRepo = postRepo;
    }

    public List<Comment> getCommentsByPostId(long id) {
        return commentRepo.findByPostId(id);
    }

    public void createComment(String content, Long postId, BlogUser blogUser) {
        Comment comment = new Comment();
        comment.setComment(content);
        comment.setCommenter(blogUser);
        comment.setEmail(blogUser.getEmail());
        comment.setCreatedAt(LocalDateTime.now());
        comment.setUpdatedAt(LocalDateTime.now());

        Optional<Post> post = postRepo.findById(postId);
        if(post.isEmpty())
            return;

        comment.setPost(post.get());

        commentRepo.save(comment);
    }

    public void deleteComment(Long id) {
        commentRepo.deleteById(id);
    }

    public Comment getCommentById(Long id) {
        return commentRepo.findById(id).orElseThrow(()->new RuntimeException("Not able to find comment by id inside CommentService"));
    }

    public void saveComment(Comment comment) {
        commentRepo.save(comment);
    }
}
