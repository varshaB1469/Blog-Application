package com.springboot.blog.services;

import com.springboot.blog.dtos.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(CommentDto commentDto, Long postId);
    List<CommentDto> getAllCommentsByPostId(Long postId);
    CommentDto getCommentById(Long postId, Long commentId);
    CommentDto updateCommentById(Long postId, long commentId,CommentDto commentDto);
    void deleteCommentById(Long postId, long commentId);
}
