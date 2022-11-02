package com.montani.exam.service;

import com.montani.exam.dto.BookDTO;
import com.montani.exam.dto.CriteriaDTO;
import com.montani.exam.helper.FileHelper;
import com.montani.exam.model.*;
import com.montani.exam.repository.*;
import org.apache.commons.collections.ArrayStack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

//    private BookRepository bookRepository;

    private BookDtlsRepository bookDtlsRepository;
    private PublisherRepository publisherRepository;
    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private BookAuthorRepository bookAuthorRepository;

    @Autowired
    public BookService( BookDtlsRepository bookDtlsRepository,
                        PublisherRepository publisherRepository,
                        AuthorRepository authorRepository,
                        BookAuthorRepository bookAuthorRepository,
                        BookRepository bookRepository)
    {
        this.bookDtlsRepository = bookDtlsRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
        this.authorRepository = authorRepository;
        this.bookAuthorRepository = bookAuthorRepository;

    }

    /**
     * get book by isbn-13
     * @param isbn13
     * @return
     */
    public BookDtls getBookByISBN13(String isbn13) {
        return bookDtlsRepository.findByIsbn13(isbn13);
    }

    public List<BookDtls> getAllBooks(CriteriaDTO criteria) {
        //TODO implement search criteria
        return bookDtlsRepository.findAll();
    }


    /**
     *
     * @param dto
     * @return
     */
    @Transactional
    public BookDtls saveBook(BookDTO dto) {
        BookDtls dtls = new BookDtls();
        Book book = new Book();

        book.setTitle(dto.getTitle());
        long publisherId = Long.parseLong(dto.getPublisher());
        Publisher publisher = publisherRepository.findById(publisherId).get();
        List<Publisher> publishers = new ArrayList<>();
        publishers.add(publisher);
//        book.setPublishers();
        //authors
        String authors[] = dto.getAuthors().split(",");
        List<BookAuthor> authorList = new ArrayList<>();
        for(String a : authors) {
            BookAuthor bookAuthor  = new BookAuthor();
            long authorId = Long.parseLong(a);
            Author author = authorRepository.findById(authorId).get();
            bookAuthor.setAuthor(author);
            bookAuthor.setBook(book);
            authorList.add(bookAuthor);
        }

        book.setAuthors(authorList);
        dtls.setEdition(dto.getEdition());
        dtls.setIsbn10(dto.getIsbn10());
        dtls.setIsbn13(dto.getIsbn13());
        dtls.setPrice(dto.getPrice());
        dtls.setPublicationYear(dto.getPublication());
        dtls.setBook(book);
        dtls.setPublisher(publisher);
        if(dto.getImage() != null) {
            dtls.setImageUrl(dto.getImage().getOriginalFilename());

        }
        book = this.bookRepository.save(book);
        this.bookDtlsRepository.save(dtls);
        //save image
        try {
            if(dto.getImage() != null) {
                FileHelper.saveImage(book.getId(), dto.getImage());
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        return dtls;
    }

    /**
     * update book cover
     * @param isbn
     * @param file
     * @return
     */
    public BookDtls updateBookCover(String isbn, MultipartFile file) {
        BookDtls dtls = bookDtlsRepository.findByIsbn13OrIsbn10(isbn, isbn);
        if(dtls != null) {
            Book book = dtls.getBook();
            dtls.setImageUrl(file.getOriginalFilename());
            bookDtlsRepository.save(dtls);
            //save image
            try {
                FileHelper.saveImage(book.getId(), file);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return dtls;
    }

    /**
     * delete book by isbn
     * @param isbn
     */
    @Transactional
    public void deleteBook(String isbn) {
        BookDtls dtls = bookDtlsRepository.findByIsbn13OrIsbn10(isbn, isbn);
        if (dtls != null) {
            Book book = dtls.getBook();
            bookAuthorRepository.deleteAll(book.getAuthors());
            bookDtlsRepository.delete(dtls);
            bookRepository.delete(book);

        }
    }

}
