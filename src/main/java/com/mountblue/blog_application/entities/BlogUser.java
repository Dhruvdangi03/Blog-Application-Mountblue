package com.mountblue.blog_application.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "blog_user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BlogUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    @Column(nullable = false)
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "commenter", cascade = CascadeType.ALL, orphanRemoval = true)
    @Column(nullable = false)
    private List<Comment> commentResponses = new ArrayList<>();
}
