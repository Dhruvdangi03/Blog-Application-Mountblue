package com.mountblue.blog_application.services;

import com.mountblue.blog_application.entities.BlogUser;
import com.mountblue.blog_application.repositories.BlogUserRepo;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final BlogUserRepo blogUserRepo;

    public CustomUserDetailsService(BlogUserRepo blogUserRepo) {
        this.blogUserRepo = blogUserRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        BlogUser user = blogUserRepo.findByUsername(username);

        if(user == null)
            return User.builder().build();

        return User.builder()
                .username(user.getUsername())
                .password("{noop}" + user.getPassword())
                .roles("USER")
                .build();
    }
}