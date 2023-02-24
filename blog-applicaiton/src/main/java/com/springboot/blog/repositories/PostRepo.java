package com.springboot.blog.repositories;

import com.springboot.blog.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Long> {

    List<Post> findAllByCategoryId(Long categoryId);
}
