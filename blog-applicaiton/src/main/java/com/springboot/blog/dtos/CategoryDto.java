package com.springboot.blog.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {

    private long id;

    @NotEmpty
    @Size(min = 2, message = "Post title should have at least 2 characters.")
    private String name;

    @NotEmpty
    @Size(min = 10, message = "Post description should have at least 10 characters.")
    private String description;

}
