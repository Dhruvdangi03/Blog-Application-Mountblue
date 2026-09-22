package com.mountblue.blog_application.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchRequestDTO {
    private String search;
    private long author;
    private long tag;
    private String sort;
    private int page;
}
