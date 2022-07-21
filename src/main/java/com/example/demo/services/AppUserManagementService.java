package com.example.demo.services;


import com.example.demo.model.Appusers;
import com.example.demo.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

public class AppUserManagementService {

    @Autowired
    private AppUserRepository appUserRepository;

    public void deleteUserByUsername(String username) throws UsernameNotFoundException
    {
        List<Appusers> user = appUserRepository.findByUsername(username); //searching for users by username
        if(user.size()==0) // this value indicates whether the user exists or not
        {
            throw new UsernameNotFoundException("could not find user "+username+"."); //throwing exception if user does not exist
        }
        else {
            appUserRepository.deleteById((long) user.get(0).getId());
        }
    }

    public void addUser(Appusers user) throws UsernameExistsException
    {
        String username = user.getUsername();
        List<Appusers> userComparator = appUserRepository.findByUsername(username); //searching for users by username
        if(userComparator.size()!=0) // this value indicates whether the user exists or not. username must be unique.
        {
            throw new UsernameExistsException("username: "+username+" already exists."); //throwing exception if username exists already
        }
        else {
            appUserRepository.save(user);
        }
    }

    public void updateUser(Appusers user) throws UsernameNotFoundException
    {
        int id = user.getId();
        Optional<Appusers> userComparator = appUserRepository.findById((long) id); //searching for users by id
        if(!(userComparator.isPresent())) // this value indicates whether the user exists or not
        {
            throw new UsernameNotFoundException("id "+id+" not found."); //throwing exception if user does not exist
        }
        else {
            userComparator.get().setUsername(user.getUsername()); //inserting new values in variable extracted by "findById()" method
            userComparator.get().setEmail(user.getEmail());       //in order to have the ability to update the values in the database
            userComparator.get().setName(user.getName());         //instead of adding a new one.
            userComparator.get().setLast_name(user.getLast_name());
            userComparator.get().setEnabled(user.getEnabled());
            userComparator.get().setPort(user.getPort());
            userComparator.get().setImgurl(user.getImgurl());
            userComparator.get().setRole(user.getRole());
            appUserRepository.save(userComparator.get());
        }
    }


}
