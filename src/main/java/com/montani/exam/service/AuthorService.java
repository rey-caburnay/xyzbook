package com.montani.exam.service;

import com.montani.exam.model.Author;
import com.montani.exam.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    private AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    /**
     *
     * @return
     */
    public List<Author> getAuthors() {
        return this.authorRepository.findAll();
    }


}
