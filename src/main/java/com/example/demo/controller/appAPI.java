package com.example.demo.controller;

import com.example.demo.model.Appusers;
import com.example.demo.services.AppUserManagementService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class appAPI {

    private final AppUserManagementService appUserManagementService;

    public appAPI(AppUserManagementService appUserManagementService) {
        this.appUserManagementService = appUserManagementService;
    }

    @GetMapping("/profile/user")
    public String getUser()
    {
        return appUserManagementService.getCurrentUser().getUsername();
    }
}
