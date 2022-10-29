package com.example.employerapp.repository;

import com.example.employerapp.entities.Employer;
import com.example.employerapp.entities.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WorkerRepository extends JpaRepository<Worker, Integer> {
    List<Worker> findByLastName(String lastName);
}