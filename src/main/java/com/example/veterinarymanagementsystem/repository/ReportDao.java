package com.example.veterinarymanagementsystem.repository;

import com.example.veterinarymanagementsystem.entities.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportDao extends JpaRepository<Report,Long> {
}
