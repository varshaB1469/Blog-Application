package com.springboot.blog.services;

import com.springboot.blog.dtos.PostDto;
import com.springboot.blog.payloads.PostResponse;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto, Long categoryId);
    PostResponse getAllPost(Integer pageNo, Integer pageSize,String sortBy, String sortDir);

    PostDto getSinglePostById(Long postId);

    PostDto updatePostById(Long postId, PostDto postDto);

    void deletePost(Long postId);

    List<PostDto> getAllPostsByCategoryId(Long categoryId);

}
