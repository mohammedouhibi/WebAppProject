package com.example.demo.repository;


import com.example.demo.model.ContExport;
import com.example.demo.model.ContImport;
import com.example.demo.model.VoitExport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContImportRepository extends JpaRepository<ContImport,Long> {

    Optional<ContImport> findById(Long Id);
}
