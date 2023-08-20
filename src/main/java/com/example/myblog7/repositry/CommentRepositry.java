package com.example.myblog7.repositry;

import com.example.myblog7.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepositry extends JpaRepository<Comment,Long> {
}
