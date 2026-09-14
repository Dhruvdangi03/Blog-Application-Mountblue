package com.mountblue.blog_application.repositories;

import com.mountblue.blog_application.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepo extends JpaRepository<Tag, Long> {
    Tag findTagByName(String name);
}
