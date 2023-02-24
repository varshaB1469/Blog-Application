package com.springboot.blog.repositories;

import com.springboot.blog.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepo extends JpaRepository<Comment, Long> {
    List<Comment> findAllCommentsByPostId(Long postId);
}
