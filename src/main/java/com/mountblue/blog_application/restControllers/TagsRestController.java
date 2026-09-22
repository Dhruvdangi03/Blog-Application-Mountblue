package com.mountblue.blog_application.restControllers;

import com.mountblue.blog_application.services.TagService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/rest/tags")
public class TagsRestController {
    private final TagService tagService;

    public TagsRestController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping
    public ResponseEntity<?> getAllTags(){
        return ResponseEntity.ok(tagService.getAllTags());
    }
}
