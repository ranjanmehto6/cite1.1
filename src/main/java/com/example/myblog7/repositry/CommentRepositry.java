package com.example.myblog7.repositry;

import com.example.myblog7.entity.Comment;
import com.example.myblog7.payload.CommentDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepositry extends JpaRepository<Comment,Long> {


    List<Comment> findByPostId(long postId);


}
