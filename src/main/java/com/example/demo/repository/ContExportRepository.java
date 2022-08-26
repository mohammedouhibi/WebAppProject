package com.example.demo.repository;


import com.example.demo.model.ContExport;
import com.example.demo.model.ContImport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContExportRepository extends JpaRepository<ContExport,Long> {

    Optional<ContExport> findById(Long Id);
}
