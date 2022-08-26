package com.example.demo.services;


import com.example.demo.model.Appusers;
import com.example.demo.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.Security;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class AppUserManagementService {


    private final AppUserRepository appUserRepository;

    @Autowired
    public AppUserManagementService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Transactional
    public void deleteUserById(Long Id) throws UsernameNotFoundException
    {
        appUserRepository.deleteById(Id);
        appUserRepository.flush();

    }
    @Transactional
    public Appusers addUser(Appusers user) throws UsernameExistsException
    {
        String username = user.getUsername();
        List<Appusers> userComparator = appUserRepository.findByUsername(username); //searching for users by username
        if(userComparator.size()!=0) // this value indicates whether the user exists or not. username must be unique.
        {
            throw new UsernameExistsException("username: "+username+" already exists."); //throwing exception if username exists already
        }
        else {
            return appUserRepository.saveAndFlush(user);
        }
    }

    @Transactional
    public Appusers updateUser(Appusers newUser) throws UsernameNotFoundException
    {
        Long id = newUser.getId();
        Optional<Appusers> user = appUserRepository.findById((long) id); //searching for users by id
        String username = newUser.getUsername();
        List<Appusers> userComparator = appUserRepository.findByUsername(username); //searching for users having same username

        if (user.isPresent()&&(((userComparator.size()==1)&&(userComparator.get(0).getId()==id))||(userComparator.size()==0)))
        {
            user.get().setUsername(newUser.getUsername()); //inserting new values in variable extracted by "findById()" method
            user.get().setEmail(newUser.getEmail());       //in order to have the ability to update the values in the database
            user.get().setName(newUser.getName());        //instead of adding a new one.
            user.get().setLast_name(newUser.getLast_name());
            user.get().setEnabled(newUser.getEnabled());
            user.get().setPort(newUser.getPort());
            user.get().setImgurl(newUser.getImgurl());
            user.get().setRole(newUser.getRole());


            return appUserRepository.saveAndFlush(user.get());
        }
        else {
            throw new UsernameExistsException("username: "+username+" already exists.");
        }




    }

    public List<Appusers> findAllUsers()
    {
        Iterable<Appusers> users = appUserRepository.findAll();
        List<Appusers> usersList = new ArrayList<Appusers>();
        users.forEach(usersList::add);
        usersList.forEach(user-> user.setPassword(null)); //setting returned password to null for security
        return usersList;
    }

    public Appusers getCurrentUser()
    {
        Appusers currentUser = appUserRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).get(0);
     return currentUser;
    }

}
