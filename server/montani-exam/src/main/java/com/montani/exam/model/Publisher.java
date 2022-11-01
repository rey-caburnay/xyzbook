package com.montani.exam.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Table(name = "publisher")
@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name="name", nullable = false)
    private String name;

    @JoinColumn(name = "publisher_id", referencedColumnName = "id")
    @OneToMany
    List<BookDtls> books = new java.util.ArrayList<>();

}