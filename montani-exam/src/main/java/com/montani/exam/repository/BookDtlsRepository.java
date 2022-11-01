package com.montani.exam.repository;

import com.montani.exam.model.BookDtls;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookDtlsRepository extends JpaRepository<BookDtls, Long> {

    BookDtls findByIsbn13(String isbn13);
    BookDtls findByIsbn13OrIsbn10(String isbn13, String isbn10);
}
