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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        List<Appusers> user = appUserRepository.findByUsername(username);
        if(user.size()==0)
        {
            throw new UsernameNotFoundException("could not find user "+username+".");
        }
        return new AppUserDetails(user.get(0));
    }
}
