package com.montani.exam.repository;

import com.montani.exam.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Statement;

public interface BookRepository extends JpaRepository<Book, Long> {

    //worko around for h2 auto increment not working
    @Query(value="select max(id) from book", nativeQuery = true)
    public long getPkey();

}
