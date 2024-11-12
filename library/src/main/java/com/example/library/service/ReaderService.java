package com.example.library.service;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.library.entity.Reader;
import com.example.library.entity.RoleUser;
import com.example.library.repository.UserEntityRepository;

import lombok.extern.log4j.Log4j2;


@Log4j2
@Service
public class ReaderService {
    @Autowired
    private UserEntityRepository userEntityRepository;


    @Autowired
    private PasswordEncoder passwordEncoder;


    public Reader saveUser(Reader reader){
        reader.setRole(reader.getLogin().equals("admin") 
        ? RoleUser.LIBRARIAN : RoleUser.USER);
        reader.setPassword(passwordEncoder.encode((reader.getPassword())));
        return userEntityRepository.save(reader);
    }


    public Reader findByLogin(String login){
        return userEntityRepository.findByLogin(login);
    }

    public Reader findByLoginAndPassword(String login, String password){
        Reader reader = findByLogin(login);
        if(reader != null){
            if(passwordEncoder.matches(password, reader.getPassword()));{
                return reader;
            }
        }
        return null;
    }


    public List<Reader> findAll(){
        return userEntityRepository.findAll();
    }

    public Optional<Reader> findById(Long id){
        return userEntityRepository.findById(id);
    }
}
