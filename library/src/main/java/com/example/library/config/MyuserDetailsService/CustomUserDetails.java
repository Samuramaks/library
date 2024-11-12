package com.example.library.config.MyuserDetailsService;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.library.entity.Reader;

import java.util.Collection;
import java.util.Collections;

public class CustomUserDetails implements UserDetails{
    private String login;
    private String password;
    private Collection<? extends GrantedAuthority> grantedAuthorities;

    public static CustomUserDetails fromUserEntityToCustomUserDetails(Reader reader){
        CustomUserDetails c = new CustomUserDetails();
        c.login = reader.getLogin();
        c.password = reader.getPassword();
        c.grantedAuthorities = Collections.singletonList(new SimpleGrantedAuthority(reader.getRole().name()));

        return c;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
       return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

}
