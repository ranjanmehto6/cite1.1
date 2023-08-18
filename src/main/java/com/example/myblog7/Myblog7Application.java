package com.example.myblog7;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Myblog7Application {

    public static void main(String[] args) {
        SpringApplication.run(Myblog7Application.class, args);


    }

    //We create bean when we want use external library is added for which IOC unable to create object we use
    // @Bean annotation
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

}
