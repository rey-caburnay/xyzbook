package com.montani.exam.repository;

import com.montani.exam.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {


}
