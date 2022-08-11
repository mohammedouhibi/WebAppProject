package com.example.demo.controller;

import com.example.demo.model.Appusers;
import com.example.demo.services.AppUserManagementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdminController {
    private final AppUserManagementService appUserManagementService;

    public AdminController(AppUserManagementService appUserManagementService) {
        this.appUserManagementService = appUserManagementService;
    }



    @PostMapping("/Admin/add")
    public ResponseEntity<Appusers> addUser(@RequestBody Appusers user)
    {
        Appusers newUser= appUserManagementService.addUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.OK);
    }

    @GetMapping("/Admin/all")
    public ResponseEntity<List<Appusers>> getAllUsers()
    {
        return new ResponseEntity<>(appUserManagementService.findAllUsers(),HttpStatus.OK);
    }

    @PutMapping("/Admin/update")
    public ResponseEntity<Appusers> updateUser(@RequestBody Appusers newUser)
    {
        return  new ResponseEntity<>(appUserManagementService.updateUser(newUser),HttpStatus.OK);
    }

    @DeleteMapping("/Admin/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id)
    {
    appUserManagementService.deleteUserById(id);
    return new ResponseEntity<>(HttpStatus.OK);
    }



}
