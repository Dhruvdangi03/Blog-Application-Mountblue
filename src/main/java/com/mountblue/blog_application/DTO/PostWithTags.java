package com.mountblue.blog_application.DTO;

import com.mountblue.blog_application.entities.Tag;
import com.mountblue.blog_application.entities.BlogUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostWithTags {
    private long id;
    private String title;
    private String excerpt;
    private BlogUser author;
    private String content;
    private LocalDateTime publishedAt;
    private List<Tag> tags;
}
