package com.mountblue.blog_application.services;

import com.mountblue.blog_application.DTO.PostRequest;
import com.mountblue.blog_application.DTO.PostWithTags;
import com.mountblue.blog_application.DTO.SearchRequestDTO;
import com.mountblue.blog_application.entities.BlogUser;
import com.mountblue.blog_application.entities.Comment;
import com.mountblue.blog_application.entities.Post;
import com.mountblue.blog_application.repositories.CommentRepo;
import com.mountblue.blog_application.repositories.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class PostService {

    private final PostRepo postRepo;
    private final TagService tagService;
    private final CommentRepo commentRepo;
    private final BlogUserService blogUserService;

    @Autowired
    public PostService(
            PostRepo postRepo,
            TagService tagService,
            CommentRepo commentRepo, BlogUserService blogUserService) {

        this.postRepo = postRepo;
        this.tagService = tagService;
        this.commentRepo = commentRepo;
        this.blogUserService = blogUserService;
    }

    public void createPost(PostRequest postRequest) {

        Post post = new Post();

        post.setAuthor(blogUserService.getCurrentUser());
        post.setTags(tagService.saveTags(postRequest.getTags().split(",")));
        post.setContent(postRequest.getContent());
        post.setExcerpt(postRequest.getExcerpt());
        post.setIsPublished(true);
        post.setTitle(postRequest.getTitle());
        post.setCreatedAt(LocalDateTime.now());
        post.setUpdatedAt(LocalDateTime.now());
        post.setPublishedAt(LocalDateTime.now());

        postRepo.save(post);
    }

    public PostWithTags getPostById(long id) {

        Optional<Post> opt = postRepo.findById(id);

        PostWithTags postWithTags = new PostWithTags();

        if (opt.isEmpty()) {
            return postWithTags;
        }

        return makePostWithTags(opt.get());
    }

    private PostWithTags makePostWithTags(Post post) {

        PostWithTags postWithTags = new PostWithTags();

        postWithTags.setId(post.getId());
        postWithTags.setContent(post.getContent());
        postWithTags.setAuthor(post.getAuthor());
        postWithTags.setExcerpt(post.getExcerpt());
        postWithTags.setTitle(post.getTitle());
        postWithTags.setPublishedAt(post.getPublishedAt());
        postWithTags.setTags(post.getTags());

        return postWithTags;
    }

    public void deletePostById(long id) {
        Post post = postRepo.findById(id).orElseThrow(() -> new RuntimeException( "Not able to findPostById in delete post" ));
        List<Comment> commentList = commentRepo.findByPostId(id);

        for (Comment comment : commentList) {
            commentRepo.delete(comment);
        }

        postRepo.delete(post);
    }

    public Page<PostWithTags> filterPosts(SearchRequestDTO searchRequestDTO, int size) {
        Pageable pageable;

        if ("asc".equalsIgnoreCase(searchRequestDTO.getSort())) {
            pageable = PageRequest.of(
                    searchRequestDTO.getPage(),
                    size,
                    Sort.by("publishedAt").ascending()
            );
        } else {
            pageable = PageRequest.of(
                    searchRequestDTO.getPage(),
                    size,
                    Sort.by("publishedAt").descending()
            );
        }

        Page<Post> posts = postRepo.filterPosts(
                searchRequestDTO.getSearch(),
                searchRequestDTO.getAuthor(),
                searchRequestDTO.getTag(),
                pageable
        );

        return posts.map(this::makePostWithTags);
    }

    public void updatePost(long id, Post post) {
        Post oldPost = postRepo.findById(id).orElseThrow(() -> new RuntimeException( "Not able to findPostById in Update post"));

        oldPost.setTitle(post.getTitle());
        oldPost.setExcerpt(post.getExcerpt());
        oldPost.setContent(post.getContent());

        postRepo.save(oldPost);
    }
}