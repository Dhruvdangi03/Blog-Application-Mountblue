package com.mountblue.blog_application.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostRequest {
    private String title;
    private String excerpt;
    private String content;
    private String tags;
}
