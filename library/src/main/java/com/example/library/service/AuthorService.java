package com.example.library.service;

import com.example.library.entity.Author;
import com.example.library.repository.AuthorRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public Author save(Author author) {
        return authorRepository.save(author);
    }

    public Author findById(Long id) {
        return authorRepository.findById(id).orElse(null);
    }

    public List<Author> getAllAuthor(){
        return authorRepository.findAll();
    }
}
