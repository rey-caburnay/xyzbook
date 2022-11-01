package com.montani.exam.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.swing.*;
import java.io.Serializable;

@Table(name = "book_author")
@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class BookAuthor implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="book_id", referencedColumnName = "id")
    private Book book;

    @OneToOne
    @JoinColumn(name="author_id", referencedColumnName = "id")
    private Author author;

}