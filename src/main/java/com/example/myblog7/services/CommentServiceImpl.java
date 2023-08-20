package com.example.myblog7.services;

import com.example.myblog7.entity.Comment;
import com.example.myblog7.entity.Post;
import com.example.myblog7.exception.ResourceNotFound;
import com.example.myblog7.payload.CommentDto;
import com.example.myblog7.repositry.CommentRepositry;
import com.example.myblog7.repositry.PostRepositry;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
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
        Post post =postRepositry.findById(postId).orElseThrow(()-> new ResourceNotFound("Post not found with id "+postId));
        comment.setPost(post);
        Comment save = commentRepositry.save(comment);
        CommentDto dto = mapToDto(save);
        return dto;
    }

    @Override
    public void deleteComment(long postId) {
        commentRepositry.deleteById(postId);

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
