package com.mountblue.blog_application.repositories;

import com.mountblue.blog_application.entities.BlogUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogUserRepo extends JpaRepository<BlogUser, Long> {
}
