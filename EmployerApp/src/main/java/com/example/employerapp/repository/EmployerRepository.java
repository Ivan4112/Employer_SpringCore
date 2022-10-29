package com.example.employerapp.repository;

import com.example.employerapp.entities.Employer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployerRepository extends JpaRepository<Employer, Integer> {
    List<Employer> findByLastName(String lastName);
}