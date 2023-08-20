package com.example.myblog7.services;

import com.example.myblog7.entity.Comment;
import com.example.myblog7.payload.CommentDto;
import com.example.myblog7.repositry.CommentRepositry;
import com.example.myblog7.repositry.PostRepositry;
import org.modelmapper.ModelMapper;

public class CommentServiceImpl implements CommentService{
    private CommentRepositry commentRepositry;
    private PostRepositry postRepositry;
    private ModelMapper modelMapper;

    public CommentServiceImpl(CommentRepositry commentRepositry, PostRepositry postRepositry, ModelMapper modelMapper) {
        this.commentRepositry = commentRepositry;
        this.postRepositry = postRepositry;
        this.modelMapper = modelMapper;
    }

    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {
        Comment comment = mapToEntity(commentDto);
        CommentDto dto = mapToDto(comment);
        return dto;
    }

    private Comment mapToEntity(CommentDto commentDto) {
        Comment comment = modelMapper.map(commentDto, Comment.class);
        return comment;
    }

    private CommentDto mapToDto(Comment comment){
        CommentDto dto = modelMapper.map(comment,CommentDto.class);
        return dto;
    }
}
