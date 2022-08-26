package com.example.demo.model;

import com.example.demo.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

public class AuthenticationResponse  implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String jwt;
    private Appusers user;

    public AuthenticationResponse(String jwt,Appusers user) {
        this.jwt = jwt;
        this.user= user;
    }

    public String getJwt() {
        return jwt;
    }

    /**
     * @return the userId
     */
    public Appusers getUser() {
        return user;
    }




}
