package com.montani.exam.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
public class BookDTO implements Serializable {

    private String title;
    private String isbn13;
    private String isbn10;
    private int publication;
    private String publisher;
    private String edition;
    private double price;
    private String authors;
    @JsonIgnore
    private MultipartFile image;
    private String cover; //image in base64 format
}
