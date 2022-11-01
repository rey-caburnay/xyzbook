package com.montani.exam.converter;

import com.montani.exam.dto.AuthorDTO;
import com.montani.exam.helper.Formatter;
import com.montani.exam.model.Author;

public class AuthorConverter {

    public static AuthorDTO toDTO(Author author) {
        AuthorDTO dto = new AuthorDTO();
        dto.setFirstName(author.getFirstname());
        dto.setLastName(author.getLastname());
        dto.setMiddleName(author.getMiddlename());
        dto.setFullName(Formatter.formatAuthorName(author));
        dto.setId(author.getId());
        return dto;
    }
}
