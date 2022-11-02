package com.montani.exam.repository;

import com.montani.exam.ExamApplication;
import com.montani.exam.model.Author;
import com.montani.exam.model.Book;
import com.montani.exam.model.BookAuthor;
import com.montani.exam.model.BookDtls;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Assert.*;

import javax.transaction.Transactional;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ExamApplication.class)
public class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookAuthorRepository bookAuthorRepository;

    @Autowired
    private BookDtlsRepository bookDtlsRepository;

    @Test
    @Transactional
    public void validateDefaultDataSetTest() {

        String isbn13 = "978-1-891830-85-3";
        BookDtls foundEntity = bookDtlsRepository
                .findByIsbn13(isbn13);

        List<Author> authors = authorRepository.findAll();
        authors.forEach(e-> System.out.println(String.format("id: %d name:%s lastname: %s", e.getId(), e.getFirstname(), e.getLastname())));

        List<BookAuthor> bookAuthors = bookAuthorRepository.findAll();
        bookAuthors.forEach(e->System.out.println(String.format("book: %s author: %s", e.getBook().getTitle(), e.getAuthor().getFirstname())));

        Assert.assertNotNull(foundEntity);
        Assert.assertEquals(isbn13, foundEntity.getIsbn13());
        foundEntity.getBook().getPublishers().forEach(e->System.out.println(e.getPublisher().getName()));
        foundEntity.getBook().getAuthors().forEach(e->System.out.println(e.getAuthor().getFirstname()));

        System.out.println("ISBN:" + foundEntity.getIsbn13() + "publisher:"
                + foundEntity.getBook().getPublishers().size() +
                "Authors:" + foundEntity.getBook().getAuthors().size());

    }
}

