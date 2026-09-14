package com.mountblue.blog_application.services;

import com.mountblue.blog_application.entities.BlogUser;
import com.mountblue.blog_application.repositories.BlogUserRepo;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogUserService {
    private final BlogUserRepo blogUserRepo;

    public BlogUserService(BlogUserRepo blogUserRepo) {
        this.blogUserRepo = blogUserRepo;
    }

    public void saveBlogUser(BlogUser blogUser){
        blogUserRepo.save(blogUser);
    }

    public List<BlogUser> getAllUsers() {
        return blogUserRepo.findAll();
    }
}
