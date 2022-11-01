package com.montani.exam.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class ISBNDto implements Serializable {

    private String ISBN13;

    private String ISBN10;

    private String message;
}
