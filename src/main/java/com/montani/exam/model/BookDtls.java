package com.montani.exam.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "book_dtls")
@Getter
@Setter
public class BookDtls {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name="price", nullable = false)
    private double price;

    @Column(name="publication_year")
    private int publicationYear;

    @Column(name="image_url")
    private String imageUrl;

    @Column(name="edition")
    private String edition;

    @Column(name="isbn_13")
    private String isbn13;

    @Column(name="isbn_10")
    private String isbn10;

    @ManyToOne
    private Book book;

    @ManyToOne
    private Publisher publisher;



}
