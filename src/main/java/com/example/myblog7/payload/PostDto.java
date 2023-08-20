package com.example.myblog7.payload;


import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

public class PostDto {
    private Long id;

    @NotEmpty
    @Size(min = 2,message = "Post tittle should be atleast 2 charcter")
    private String tittle;
    @NotEmpty
    @Size(min = 4,message = "Post description should be atleast 2 charcter")
    private String description;
    @NotEmpty
    @Size(min = 6,message = "Post content should be atleast 2 charcter")
    private String content;


    public PostDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTittle() {
        return this.tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

