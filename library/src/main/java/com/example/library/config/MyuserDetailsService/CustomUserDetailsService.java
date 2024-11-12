package com.example.library.config.MyuserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.library.entity.Reader;
import com.example.library.service.ReaderService;


@Component
public class CustomUserDetailsService implements UserDetailsService{

    @Autowired
    private ReaderService readerService;

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Reader reader = readerService.findByLogin(username);

       return CustomUserDetails.fromUserEntityToCustomUserDetails(reader);
    }

   
    
}
