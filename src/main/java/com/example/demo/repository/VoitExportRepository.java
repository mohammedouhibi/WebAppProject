package com.example.demo.repository;

import com.example.demo.model.VoitExport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoitExportRepository extends JpaRepository<VoitExport,Long> {

    Optional<VoitExport> findById(Long Id);
}
