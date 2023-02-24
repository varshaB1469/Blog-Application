package com.springboot.blog.serviceImpls;

import com.springboot.blog.dtos.CommentDto;
import com.springboot.blog.exceptions.BlogApiException;
import com.springboot.blog.exceptions.ResourceNotFoundException;
import com.springboot.blog.models.Comment;
import com.springboot.blog.models.Post;
import com.springboot.blog.repositories.CommentRepo;
import com.springboot.blog.repositories.PostRepo;
import com.springboot.blog.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CommentDto createComment(CommentDto commentDto, Long postId) {
        Post post = postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "Id",postId));

        Comment comment = this.modelMapper.map(commentDto, Comment.class);
        comment.setPost(post);
        Comment savedComment = commentRepo.save(comment);
        return this.modelMapper.map(savedComment, CommentDto.class);
    }

    @Override
    public List<CommentDto> getAllCommentsByPostId(Long postId) {
        Post post = postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "Id",postId));
        List<Comment> comments = commentRepo.findAllCommentsByPostId(postId);
        return comments.stream().map((com)-> this.modelMapper.map(com,CommentDto.class)).collect(Collectors.toList());

    }

    @Override
    public CommentDto getCommentById(Long postId, Long commentId) {
        Post post = postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "Id",postId));
        Comment comment = commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment","Id",commentId));

        if (comment.getPost().getId() != post.getId()){
            throw new BlogApiException(HttpStatus.BAD_REQUEST,"comment does not belong to this post.");
        }
        return this.modelMapper.map(comment,CommentDto.class);
    }

    @Override
    public CommentDto updateCommentById(Long postId, long commentId, CommentDto commentDto) {
        Post post = postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "Id",postId));
        Comment comment = commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment","Id",commentId));

        if (comment.getPost().getId() != post.getId()){
            throw new BlogApiException(HttpStatus.BAD_REQUEST,"comment does not belong to this post.");
        }

        comment.setName(commentDto.getName());
        comment.setBody(commentDto.getBody());
        comment.setEmail(commentDto.getEmail());
        Comment updatedComment = commentRepo.save(comment);

        return this.modelMapper.map(updatedComment,CommentDto.class);
    }

    @Override
    public void deleteCommentById(Long postId, long commentId) {
        Post post = postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "Id",postId));
        Comment comment = commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment","Id",commentId));

        if (comment.getPost().getId() != post.getId()){
            throw new BlogApiException(HttpStatus.BAD_REQUEST,"comment does not belong to this post.");
        }

        commentRepo.deleteById(commentId);
    }
}
