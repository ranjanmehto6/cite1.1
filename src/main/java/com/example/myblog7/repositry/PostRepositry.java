package com.example.myblog7.repositry;

import com.example.myblog7.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepositry extends JpaRepository<Post, Long> {
}
