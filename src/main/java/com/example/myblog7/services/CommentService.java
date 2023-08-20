package com.example.myblog7.services;

import com.example.myblog7.payload.CommentDto;

public interface CommentService {

    CommentDto createComment(long postId, CommentDto commentDto);
}
