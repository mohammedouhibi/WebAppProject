package com.example.demo.repository;

import com.example.demo.model.Appusers;
import com.example.demo.model.VoitImport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface VoitImportRepository extends JpaRepository<VoitImport, Long> {

    Optional<VoitImport> findById(Long Id);


}
