package com.mountblue.blog_application.services;

import com.mountblue.blog_application.DTO.SignupRequest;
import com.mountblue.blog_application.entities.BlogUser;
import com.mountblue.blog_application.repositories.BlogUserRepo;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogUserService {
    private final BlogUserRepo blogUserRepo;

    public BlogUserService(BlogUserRepo blogUserRepo) {
        this.blogUserRepo = blogUserRepo;
    }

    public void saveBlogUser(SignupRequest signupRequest){
        BlogUser blogUser = new BlogUser();
        blogUser.setUsername(signupRequest.getUsername());
        blogUser.setEmail(signupRequest.getEmail());
        blogUser.setPassword(signupRequest.getPassword());

        blogUserRepo.save(blogUser);
    }

    public List<BlogUser> getAllUsers() {
        return blogUserRepo.findAll();
    }

    public BlogUser getCurrentUser(){
        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        return blogUserRepo.findByUsername(username);
    }
}
