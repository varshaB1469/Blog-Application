package com.springboot.blog.dtos;


import com.springboot.blog.models.Category;
import com.springboot.blog.models.Comment;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
public class PostDto {

    private Long id;

    @NotEmpty
    @Size(min = 2, message = "Post title should have at least 2 characters.")
    private String title;

    @NotEmpty
    @Size(min = 10, message = "Post description should have at least 10 characters.")
    private String description;

    @NotEmpty
    private String content;

    private Long categoryId;

    private Set<CommentDto> comments;
}
