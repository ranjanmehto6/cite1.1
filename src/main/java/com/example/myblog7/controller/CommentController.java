package com.example.myblog7.controller;

import com.example.myblog7.payload.CommentDto;
import com.example.myblog7.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class CommentController {

    @Autowired
    private CommentService commentService;

    //http://localhost:8080/api/posts/1/comments
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable(value = "postId") long postId, @RequestBody CommentDto commentDto)

    {
       return new ResponseEntity<>(commentService.createComment(postId,commentDto), HttpStatus.CREATED);
    }


    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<?> deleteComment(@PathVariable(value = "postId") long postId){
        commentService.deleteComment(postId);
        return new ResponseEntity<>("POST IS DELETED",HttpStatus.OK);
    }
}
