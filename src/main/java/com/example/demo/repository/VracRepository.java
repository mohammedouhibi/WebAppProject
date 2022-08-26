package com.example.demo.repository;

import com.example.demo.model.VoitImport;
import com.example.demo.model.Vrac;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VracRepository extends JpaRepository<Vrac, Long> {

    Optional<Vrac> findById(Long Id);
}
