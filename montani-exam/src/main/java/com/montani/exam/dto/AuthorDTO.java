package com.montani.exam.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class AuthorDTO implements Serializable {

    private String firstName;
    private String lastName;
    private String middleName;
    private long id;
    private String fullName;
}
