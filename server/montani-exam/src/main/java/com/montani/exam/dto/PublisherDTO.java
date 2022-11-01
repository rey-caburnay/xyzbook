package com.montani.exam.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class PublisherDTO implements Serializable {

    private long id;
    private String name;
}
