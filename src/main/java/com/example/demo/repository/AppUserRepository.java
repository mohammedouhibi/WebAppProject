package com.example.demo.repository;

import com.example.demo.model.Appusers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppUserRepository extends JpaRepository<Appusers, Long> { //repository for connecting Appusers class to appusers table on database

    List<Appusers> findByUsername(String username); //required method

    @Modifying
    @Query("delete from Appusers t where t.id = ?1")
    void deleteById(Long entityId);

}
