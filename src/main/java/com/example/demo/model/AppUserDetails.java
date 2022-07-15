package com.example.demo.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AppUserDetails implements UserDetails { //overriding default UserDetails methods

    private final Appusers user;
    public AppUserDetails(Appusers user)
    {
        this.user = user;
    } //locking user variable to current user

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {  //overriding getAuthorities method to work with authority scheme on the Data Base
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (user.getRole() == 0) {  //role value can not be null in the database or we have problems.
            authorities.add(new SimpleGrantedAuthority("user"));  //role is set to normal user if role value = 0
        } else
        {
            authorities.add(new SimpleGrantedAuthority("admin")); //role is set to admin if role value = 1
        }
        return authorities;
    }


    // overriding other default UserDetails method to work with current data usage scheme
    @Override
    public String getPassword()
    {
        return user.getPassword();
    }

    @Override
    public String getUsername()
    {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired(){return true;} //not implemented

    @Override
    public boolean isAccountNonLocked(){return true;}  //not implemented

    @Override
    public boolean isCredentialsNonExpired() {return true;}  //not implemented

    @Override
    public boolean isEnabled(){ //option to enable/disable users required
        return user.getEnabled()!=0;//transforming from INT value to boolean
    }
}
