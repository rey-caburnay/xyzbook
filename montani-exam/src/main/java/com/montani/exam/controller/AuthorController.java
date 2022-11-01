package com.montani.exam.controller;

import com.montani.exam.converter.AuthorConverter;
import com.montani.exam.converter.BookConverter;
import com.montani.exam.dto.ApiResponse;
import com.montani.exam.dto.AuthorDTO;
import com.montani.exam.dto.BookDTO;
import com.montani.exam.dto.CriteriaDTO;
import com.montani.exam.model.Author;
import com.montani.exam.model.BookDtls;
import com.montani.exam.service.AuthorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AuthorController {

    private AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @Operation(summary = "get all authors", tags={"authors"})
    @ApiResponses(value = {@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "successful operation",
            content = @Content(schema = @Schema(implementation = ApiResponse.class)))})
    @GetMapping("/authors")
    public ResponseEntity getAllAuthor(CriteriaDTO criteria) {

        ApiResponse<List<AuthorDTO>> response = new ApiResponse<>();
        response.setStatus(HttpStatus.OK.value());
        response.setTimestamp(new Date());

        List<Author> authors = authorService.getAuthors();

        List<AuthorDTO> dtos = authors.stream().map(e-> AuthorConverter.toDTO(e)).collect(Collectors.toList());
        response.setData(dtos);
        return response.returnAsResponseEntity();
    }
}
