package com.example.myblog7.services;

import com.example.myblog7.entity.Post;
import com.example.myblog7.exception.ResourceNotFound;
import com.example.myblog7.payload.PostDto;
import com.example.myblog7.payload.PostResponse;
import com.example.myblog7.repositry.PostRepositry;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepositry postRepositry;
    private ModelMapper modelMapper;

    public PostServiceImpl(PostRepositry postRepositry, ModelMapper modelMapper) {
        this.postRepositry = postRepositry;
        this.modelMapper = modelMapper;
    }
    //    public PostServiceImpl(PostRepositry postRepositry) {
//        this.postRepositry = postRepositry;
//    }

    @Override
    public PostDto savePost(PostDto postDto) {
        Post post = mapToPost(postDto);
        Post save = postRepositry.save(post);
        PostDto dto1 = mapToDto(save);

        return dto1;

//        PostDto dto = new PostDto();
//        dto.setId(save.getId());
//        dto.setContent(save.getContent());
//        dto.setDescription(save.getDescription());
//        dto.getTittle(save.getTittle());
//        return  dto;
    }

    @Override
    public void deletePost(long id) {

        postRepositry.deleteById(id);

    }

    @Override
    public PostDto updatePost(long id, PostDto postDto) {

        Post post = postRepositry.findById(id).orElseThrow(()-> new ResourceNotFound("POst not found "+id));

        post.setTittle(postDto.getTittle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        Post updatedPost = postRepositry.save(post);
        PostDto dto1 = mapToDto(updatedPost);
        return dto1;

    }

    @Override
    public PostDto getPostById(long id) {
   Post post =  postRepositry.findById(id).orElseThrow(()-> new ResourceNotFound("NOT FOUND"));
    PostDto dto = mapToDto(post);
    return new ResponseEntity<>(dto, HttpStatus.OK).getBody();
    }


//public List<PostDto> getPosts(int pageNo, int pageSize, String sortBy, String sortDir) {
    @Override
    public PostResponse getPosts(int pageNo, int pageSize, String sortBy, String sortDir) {
    Sort sort =     sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
      Pageable pageable =  PageRequest.of(pageNo,pageSize,sort);
        Page<Post> pagePost = postRepositry.findAll(pageable);
//        Stream<Post> postStream = posts.getContent();
        List<Post> posts = pagePost.getContent();
        List<PostDto> postDtos = posts.stream().map(this::mapToDto).collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setPostDto(postDtos);
        postResponse.setPageNo(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalElement(pagePost.getTotalElements());
        postResponse.setLasts(pagePost.isLast());
        postResponse.setTotalPages(pagePost.getTotalPages());
        return postResponse;
    }

    PostDto mapToDto(Post post){
        PostDto dto = new PostDto();
        dto.setId(post.getId());
        dto.setContent(post.getContent());
        dto.setDescription(post.getDescription());
        dto.setTittle(post.getTittle());
        return dto;

    }

    Post mapToPost(PostDto dto){

        Post post = new Post();
        post.setTittle(dto.getTittle());
        post.setDescription(dto.getDescription());
        post.setContent(dto.getContent());

        return  post;

    }

}