package com.springboot.blog.serviceImpls;

import com.springboot.blog.dtos.PostDto;
import com.springboot.blog.exceptions.ResourceNotFoundException;
import com.springboot.blog.models.Category;
import com.springboot.blog.models.Post;
import com.springboot.blog.payloads.PostResponse;
import com.springboot.blog.repositories.CategoryRepo;
import com.springboot.blog.repositories.PostRepo;
import com.springboot.blog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private PostRepo postRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PostResponse postResponse;
    @Override
    public PostDto createPost(PostDto postDto, Long categoryId) {
        Category cat = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","Id",categoryId));

        Post post = this.modelMapper.map(postDto, Post.class);
        post.setCategory(cat);
        Post savedPost = postRepo.save(post);
        return this.modelMapper.map(savedPost, PostDto.class);
    }

    @Override
    public PostResponse getAllPost(Integer pageNo, Integer pageSize, String sortBy,String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable =  PageRequest.of(pageNo,pageSize,sort);
        Page<Post> posts = postRepo.findAll(pageable);
        List<Post> listOfPost  = posts.getContent();
        List<PostDto> content =   listOfPost.stream().map(post -> this.modelMapper.map(post ,PostDto.class)).collect(Collectors.toList());

        postResponse.setContent(content);
        postResponse.setPageNo(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalElements(posts.getTotalElements());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setLastPage(posts.isLast());
        return postResponse;

    }

    @Override
    public PostDto getSinglePostById(Long postId) {
        Post post = postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","Id",postId));
        return this.modelMapper.map(post, PostDto.class);
    }

    @Override
    public PostDto updatePostById(Long postId, PostDto postDto) {
        Category cat = categoryRepo.findById(postDto.getCategoryId())
                .orElseThrow(()-> new ResourceNotFoundException("Category","Id",postDto.getCategoryId()));
        Post post = postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","Id",postId));
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        post.setCategory(cat);
        Post updatedPost = postRepo.save(post);
        return this.modelMapper.map(updatedPost, PostDto.class);
    }

    @Override
    public void deletePost(Long postId) {
        Post post = postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","Id",postId));
        postRepo.delete(post);
    }

    @Override
    public List<PostDto> getAllPostsByCategoryId(Long categoryId) {
        Category cat = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","Id",categoryId));
        List<Post> posts = postRepo.findAllByCategoryId(categoryId);
        return posts.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
    }


}
