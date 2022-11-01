package com.montani.exam.service;

import com.montani.exam.model.Publisher;
import com.montani.exam.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherService {

    private PublisherRepository publisherRepository;

    @Autowired
    public PublisherService(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    /**
     *
     * @return
     */
    public List<Publisher> getAllPublihsers() {
        return publisherRepository.findAll();
    }
}
