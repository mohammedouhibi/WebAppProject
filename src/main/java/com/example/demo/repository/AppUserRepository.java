package com.example.demo.repository;

import com.example.demo.model.Appusers;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppUserRepository extends CrudRepository<Appusers, Long> { //repository for connecting Appusers class to appusers table on database

    List<Appusers> findByUsername(String username); //required method
}
