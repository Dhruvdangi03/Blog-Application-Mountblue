package com.mountblue.blog_application.restControllers;

import com.mountblue.blog_application.DTO.PostRequest;
import com.mountblue.blog_application.DTO.SearchRequestDTO;
import com.mountblue.blog_application.entities.Post;
import com.mountblue.blog_application.services.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("/rest/posts")
public class PostRestController {
    private final PostService postService;

    public PostRestController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<?> createPost(PostRequest postRequest) {
        postService.createPost(postRequest);

        return ResponseEntity.ok("Post Created Successfully !");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPostById(@PathVariable long id) {
        return ResponseEntity.ok(postService.getPostById(id));
    }

    @GetMapping
    public ResponseEntity<?> getPostSearch(SearchRequestDTO searchRequestDTO) {
        return ResponseEntity.ok(postService.filterPosts(
                searchRequestDTO,
                10));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePostById(@PathVariable long id) {
        postService.deletePostById(id);
        return ResponseEntity.ok("Post Deleted Successfully !");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePostById(@PathVariable long id, PostRequest postRequest) {
        Post post = new Post();
        post.setTitle(postRequest.getTitle());
        post.setExcerpt(postRequest.getExcerpt());
        post.setContent(postRequest.getContent());

        postService.updatePost(id, post);

        return ResponseEntity.ok("Post Updated Successfully !");
    }
}
