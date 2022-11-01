package com.montani.exam.controller;

import com.montani.exam.converter.BookConverter;
import com.montani.exam.converter.ISBNConverter;
import com.montani.exam.dto.ApiResponse;
import com.montani.exam.dto.BookDTO;
import com.montani.exam.dto.CriteriaDTO;
import com.montani.exam.dto.ISBNDto;
import com.montani.exam.helper.ISBNValidator;
import com.montani.exam.model.Book;
import com.montani.exam.model.BookDtls;
import com.montani.exam.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Tag(name="books", description="Book controller")
public class BookController {

    private BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @Operation(summary = "get book using ISBN-13", tags={"books"})
    @ApiResponses(value = {@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "successful operation",
    content = @Content(schema = @Schema(implementation = ApiResponse.class)))})
    @GetMapping("/book/{isbn13}")
    public ResponseEntity getBookByISBN13(@PathVariable("isbn13") String isbn13) {

        ApiResponse<BookDTO> response = new ApiResponse<>();
        response.setStatus(HttpStatus.OK.value());
        response.setTimestamp(new Date());

        BookDtls book = bookService.getBookByISBN13(isbn13);
        if(book == null) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return response.returnAsResponseEntity();
        }

        BookDTO dto = BookConverter.toBookDTO(book);
        response.setData(dto);
        return response.returnAsResponseEntity();
    }


    @Operation(summary = "get all books by criteria", tags={"books"})
    @ApiResponses(value = {@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "successful operation",
            content = @Content(schema = @Schema(implementation = ApiResponse.class)))})
    @GetMapping("/books")
    public ResponseEntity getAllBooks(CriteriaDTO criteria) {

        ApiResponse<List<BookDTO>> response = new ApiResponse<>();
        response.setStatus(HttpStatus.OK.value());
        response.setTimestamp(new Date());

        List<BookDtls> books = bookService.getAllBooks(criteria);

        List<BookDTO> dtos = books.stream().map(e->BookConverter.toBookDTO(e)).collect(Collectors.toList());
        response.setData(dtos);
        return response.returnAsResponseEntity();
    }

    @Operation(summary = "validate isbn value and convert if valid ", tags={"books"})
    @ApiResponses(value = {@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "successful operation",
            content = @Content(schema = @Schema(implementation = ApiResponse.class)))})
    @GetMapping("/books/{isbn}/validate")
    public ResponseEntity isValidISBNDigit(@PathVariable("isbn") String isbn) {

        ApiResponse<ISBNDto> response = new ApiResponse<>();
        response.setStatus(HttpStatus.OK.value());
        response.setTimestamp(new Date());
        ISBNDto dto = new ISBNDto();
        //strip any dash
        isbn = isbn.replace("-", "");
        boolean result = ISBNValidator.isISBN13(isbn);
        if(!result) { //check if it's isbn 10, since it fail to isbn 13
            result = ISBNValidator.isISBN10(isbn);
            if(result) { //valid isbn 10
                dto.setISBN10(ISBNConverter.formatISBN10(isbn));
                String isbn13 = ISBNConverter.toISBN13(isbn);
                dto.setISBN13(ISBNConverter.formatISBN13(isbn13));
                response.setStatus(HttpStatus.OK.value());

            } else {
                response.setStatus(HttpStatus.BAD_REQUEST.value());
                response.setMessage("Invalid ISBN");
            }
        } else { //valid isbn 13
            dto.setISBN13(ISBNConverter.formatISBN13(isbn));
            //convert to isbn 10
            String isbn10 = ISBNConverter.toISBN10(isbn);
            dto.setISBN10(ISBNConverter.formatISBN10(isbn10));
            response.setStatus(HttpStatus.OK.value());

        }

        response.setData(dto);
        return response.returnAsResponseEntity();
    }


    @Operation(summary = "save book ", tags={"books"})
    @ApiResponses(value = {@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "successful operation",
            content = @Content(schema = @Schema(implementation = ApiResponse.class)))})
    @PostMapping(path = "/book", produces={MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity saveBook(@ModelAttribute BookDTO dto) {

        System.out.println("book:" + dto);

//        System.out.println("Image"+ image);
        ApiResponse<BookDTO> response = new ApiResponse<>();
        response.setStatus(HttpStatus.OK.value());
        response.setTimestamp(new Date());
        bookService.saveBook(dto);
        response.setData(dto);
        return response.returnAsResponseEntity();
    }

    @Operation(summary = "update book cover  ", tags={"books"})
    @ApiResponses(value = {@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "successful operation",
            content = @Content(schema = @Schema(implementation = ApiResponse.class)))})
    @PutMapping(path = "/book/{isbn}/cover", produces={MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity updateCover(@PathVariable String isbn,  MultipartFile file) {

        ApiResponse<BookDTO> response = new ApiResponse<>();
        response.setStatus(HttpStatus.OK.value());
        response.setTimestamp(new Date());
        BookDtls book = bookService.updateBookCover(isbn, file);
        BookDTO dto = BookConverter.toBookDTO(book);
        response.setData(dto);
        return response.returnAsResponseEntity();
    }

    @Operation(summary = "delete  book   ", tags={"books"})
    @ApiResponses(value = {@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "successful operation",
            content = @Content(schema = @Schema(implementation = ApiResponse.class)))})
    @DeleteMapping(path = "/book/{isbn}")
    public ResponseEntity deleteBook(@PathVariable String isbn) {

        ApiResponse<String> response = new ApiResponse<>();
        response.setStatus(HttpStatus.OK.value());
        response.setTimestamp(new Date());
        bookService.deleteBook(isbn);
        response.setData("Delete successfully");
        return response.returnAsResponseEntity();
    }


}
