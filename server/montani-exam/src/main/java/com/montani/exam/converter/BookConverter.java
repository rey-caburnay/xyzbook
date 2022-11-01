package com.montani.exam.converter;

import com.montani.exam.dto.BookDTO;
import com.montani.exam.helper.FileHelper;
import com.montani.exam.helper.Formatter;
import com.montani.exam.model.Author;
import com.montani.exam.model.Book;
import com.montani.exam.model.BookDtls;
import org.springframework.util.Assert;

import java.util.stream.Collectors;

public class BookConverter {

    public static BookDTO toBookDTO(BookDtls book) {
        BookDTO dto = new BookDTO();
        dto.setTitle(book.getBook().getTitle());
        dto.setIsbn10(book.getIsbn10());
        dto.setIsbn13(book.getIsbn13());

        String authors = book.getBook().getAuthors().stream().map(e-> Formatter.formatAuthorName(e.getAuthor())).collect(Collectors.joining(","));
        dto.setAuthors(authors);
        dto.setPublisher(book.getPublisher().getName());
        dto.setPrice(book.getPrice());
        dto.setEdition(book.getEdition());
        dto.setPublication(book.getPublicationYear());

        if(book.getImageUrl() != null)  {
         dto.setCover(FileHelper.getImageAsBase64(book.getBook().getId(), book.getImageUrl()));
        }
        return dto;

    }


}
