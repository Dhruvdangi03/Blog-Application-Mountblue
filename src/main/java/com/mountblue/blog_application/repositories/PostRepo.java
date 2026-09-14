package com.mountblue.blog_application.repositories;

import com.mountblue.blog_application.entities.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepo extends JpaRepository<Post, Long> {
    @Query("""
        SELECT DISTINCT p
        FROM Post p
        LEFT JOIN p.tags t
        WHERE
            (:search IS NULL OR :search = ''
                OR LOWER(p.title) LIKE LOWER(CONCAT('%', :search, '%')))
            AND
            (:author IS NULL OR p.author.id = :author)
            AND
            (:tag IS NULL OR t.id = :tag)
    """)
    Page<Post> filterPosts(
            @Param("search") String search,
            @Param("author") Long author,
            @Param("tag") Long tag,
            Pageable pageable
    );

    Page<Post> findAll(Pageable pageable);
}