package com.springboot.blog.controllers;

import com.springboot.blog.dtos.CommentDto;
import com.springboot.blog.services.CommentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/addComment/postId/{postId}")
    public ResponseEntity<CommentDto> addComment(@Valid @RequestBody CommentDto  commentDto, @PathVariable Long postId){
        CommentDto createdComment = commentService.createComment(commentDto,postId);
        return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
    }

    @GetMapping("/allComments/postId/{postId}")
    public ResponseEntity<List<CommentDto>> allComments(@PathVariable Long postId){
        List<CommentDto> allComments = commentService.getAllCommentsByPostId(postId);
        return new ResponseEntity<>(allComments,HttpStatus.OK);
    }

    @GetMapping("/postId/{postId}/commentId/{commentId}")
    public ResponseEntity<CommentDto> singleComment(
            @PathVariable Long postId,
            @PathVariable Long commentId
    ){
        return new ResponseEntity<>(commentService.getCommentById(postId,commentId),HttpStatus.OK);

    }

    @PutMapping("/postId/{postId}/commentId/{commentId}")
    public ResponseEntity<CommentDto> updateComment(
            @PathVariable Long postId,
            @PathVariable Long commentId,
            @Valid @RequestBody CommentDto commentDto
    ){
        return new ResponseEntity<>(commentService.updateCommentById(postId,commentId,commentDto),HttpStatus.OK);
    }

    @DeleteMapping("/postId/{postId}/commentId/{commentId}")
    public ResponseEntity<String> deleteComment(
            @PathVariable Long postId,
            @PathVariable Long commentId
    ){
        commentService.deleteCommentById(postId, commentId);
        return new ResponseEntity<>("Comment deleted successfully",HttpStatus.OK);
    }
}
