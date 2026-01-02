package com.example.eduSystems.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.eduSystems.models.tblSubmissions;

public interface SubmissionRepository extends JpaRepository<tblSubmissions, Integer> {

}
