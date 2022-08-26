package com.example.demo.controller;

import com.example.demo.configuration.AppUserDetailsService;
import com.example.demo.model.AppUserDetails;
import com.example.demo.model.Appusers;
import com.example.demo.model.AuthenticationRequest;
import com.example.demo.model.AuthenticationResponse;
import com.example.demo.repository.AppUserRepository;
import com.example.demo.services.AppUserManagementService;
import com.example.demo.services.JwtUtil;
import org.apache.catalina.Authenticator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
public class appAPI {

    private final AppUserManagementService appUserManagementService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private AppUserDetailsService userDetailsService;

    @Autowired
    private AppUserRepository repo;
    @Autowired
    private JwtUtil jwtTokenUtil;

    public appAPI(AppUserManagementService appUserManagementService) {
        this.appUserManagementService = appUserManagementService;
    }

    @GetMapping("/Profile/user")
    public String getUser()
    {
        return appUserManagementService.getCurrentUser().getUsername();
    }

    @PostMapping("/login")
    public ResponseEntity<?>  createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception
    {
        try {
            System.out.println(authenticationRequest.getUsername());
            System.out.println(authenticationRequest.getPassword());
            System.out.println(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
            System.out.println(authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            ));
        }
        catch (BadCredentialsException e) {
            System.out.println("Incorrect username or password");
            throw new Exception("Incorrect username or password", e);

        }
        final AppUserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        Appusers user=repo.findByUsername(userDetails.getUsername()).get(0);
        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt,user));

    }

    @PostMapping("/Profile/update")
    public ResponseEntity<?> updateUser(@RequestBody Appusers user)
    {
        Appusers currentUser=appUserManagementService.getCurrentUser();
         if((currentUser.getId()==user.getId())&&(currentUser.getPassword().equals(user.getPassword()))){ //to do: implement hashing
            currentUser.setUsername(user.getUsername());
            currentUser.setName(user.getName());
            currentUser.setLast_name(user.getLast_name());
            currentUser.setEmail(user.getEmail());
            System.out.println("role:" + currentUser.getRole());
            return  new ResponseEntity<>(appUserManagementService.updateUser(currentUser),HttpStatus.OK);
        }
        else{
            return  new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
    }

    @PostMapping("/Profile/password")
    public ResponseEntity<?> changePassword(@RequestBody Map<String, String> passwords){
        Appusers currentUser=appUserManagementService.getCurrentUser();
        System.out.println( "passwords: " + passwords.get("oldPassword")+"/*/"+passwords.get("newPassword"));
        if (passwords.get("oldPassword").equals(currentUser.getPassword())){ //to do: implement hashing
            currentUser.setPassword(passwords.get("newPassword"));  //to do: implement hashing
            appUserManagementService.updateUser(currentUser);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
}
