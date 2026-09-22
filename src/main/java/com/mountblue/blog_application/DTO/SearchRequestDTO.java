package com.mountblue.blog_application.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchRequestDTO {
    private String search;
    private Long author;
    private Long tag;
    private String sort;
    private int page;
}
