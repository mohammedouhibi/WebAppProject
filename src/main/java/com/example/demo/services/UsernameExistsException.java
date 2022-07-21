package com.example.demo.services;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UsernameExistsException extends UsernameNotFoundException {

    public UsernameExistsException (String message)
    {
        super(message);
    }
}
