package com.example.employerapp.repository;

import com.example.employerapp.entities.Employer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployerRepository extends JpaRepository<Employer, Integer> {
    Optional<Employer> findByLastName(String lastName);
}