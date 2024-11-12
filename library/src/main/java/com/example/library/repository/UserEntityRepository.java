package com.example.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.library.entity.Reader;

@Repository
public interface UserEntityRepository extends JpaRepository<Reader, Long>{
    Reader findByLogin(String login);
}
