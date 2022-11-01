package com.montani.exam.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "book")
@Setter
@Getter
@RequiredArgsConstructor
public class Book {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "title")
    private String title;



    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name="book_id", referencedColumnName = "id")
    List<BookAuthor> authors = new java.util.ArrayList<>();


    @JoinColumn(name="publisher_id")
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    List<BookDtls> publishers = new java.util.ArrayList<>();

}
