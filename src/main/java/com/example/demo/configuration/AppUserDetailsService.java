package com.example.demo.configuration;

import com.example.demo.model.Appusers;
import com.example.demo.model.AppUserDetails;
import com.example.demo.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    private AppUserRepository appUserRepository;

    @Override
    public AppUserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        List<Appusers> user = appUserRepository.findByUsername(username); //searching for users by username
        if(user.size()==0) // this value indicates whether the user exists or not
        {
            throw new UsernameNotFoundException("could not find user "+username+"."); //throwing exception if user does not exist
        }
        return new AppUserDetails(user.get(0)); //returning first user that matches the given username
    }


}
