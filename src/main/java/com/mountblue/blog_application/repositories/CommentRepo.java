package com.mountblue.blog_application.repositories;

import com.mountblue.blog_application.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepo extends JpaRepository<Comment, Long> {
    List<Comment> findByPostId(Long id);
}
