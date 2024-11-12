package com.example.library.controller;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.library.config.AuthRequest;
import com.example.library.config.AuthResponse;
import com.example.library.config.JwtProvider;
import com.example.library.dto.RegistatrationRequest;
import com.example.library.service.ReaderService;
import com.example.library.entity.Reader;

import jakarta.validation.Valid;

@RestController
public class ReaderController {
    @Autowired
    private ReaderService readerService;

    @Autowired
    private JwtProvider jwtProvider;


    @PostMapping("/register")
    public String registerUser(@RequestBody @Valid RegistatrationRequest registartionRequest) {
            Reader reader = new Reader();
            reader.setPassword(registartionRequest.getPassword());
            reader.setLogin(registartionRequest.getLogin());
            reader.setFirstName(registartionRequest.getFirstName());
            reader.setLastName(registartionRequest.getLastName());
            readerService.saveUser(reader);
            return "Регистрация успешна";
    }


    @PostMapping("/auth")
    public AuthResponse auth(@RequestBody AuthRequest request) {
        Reader account = readerService.findByLoginAndPassword(request.getLogin(), request.getPassword());
        String token = jwtProvider.generateToken(account.getLogin());
        return new AuthResponse(token);
    }

}
