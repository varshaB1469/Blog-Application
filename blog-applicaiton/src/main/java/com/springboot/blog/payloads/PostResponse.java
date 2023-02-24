package com.springboot.blog.payloads;

import com.springboot.blog.dtos.PostDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Component
public class PostResponse {

    private List<PostDto>  content;
    private int pageNo;
    private int pageSize;
    private long totalElements;

    private  int totalPages;

    private boolean  lastPage;
}
