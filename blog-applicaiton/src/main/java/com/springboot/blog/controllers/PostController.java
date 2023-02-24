package com.springboot.blog.controllers;


import com.springboot.blog.dtos.PostDto;
import com.springboot.blog.exceptions.ResourceNotFoundException;
import com.springboot.blog.models.Category;
import com.springboot.blog.payloads.PostResponse;
import com.springboot.blog.services.PostService;
import com.springboot.blog.utils.AppConstants;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/posts")
public class PostController {

    @Autowired
    private PostService postService;


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/addPost/category/{categoryId}")
    public ResponseEntity<PostDto> addPost(@Valid @RequestBody PostDto postDto, @PathVariable Long categoryId ){
        PostDto createdPost = postService.createPost(postDto,categoryId);
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);

    }


    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/updateBy/{postId}")
    public ResponseEntity<PostDto> updatePost(@PathVariable Long postId,
                                              @Valid @RequestBody PostDto postDto

    ){

        PostDto updated = postService.updatePostById(postId,postDto);
        return new ResponseEntity<>(updated,HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/deleteBy/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable Long postId){
        postService.deletePost(postId);
        return new ResponseEntity<>("Post deleted successfully", HttpStatus.OK);
    }

    @GetMapping("/allPost")
    public PostResponse getAll(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize" , defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy" , defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIR, required = false) String sortDir
    ){
       return  postService.getAllPost(pageNo,pageSize,sortBy,sortDir);
    }

    @GetMapping("/postBy/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Long postId){
        PostDto postById = postService.getSinglePostById(postId);
        return new ResponseEntity<>(postById,HttpStatus.OK);
    }

    @GetMapping("/categoryId/{categoryId}")
    public ResponseEntity<List<PostDto>> getPostByCategoryId(@PathVariable Long categoryId){
        List<PostDto> list = postService.getAllPostsByCategoryId(categoryId);
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

}
