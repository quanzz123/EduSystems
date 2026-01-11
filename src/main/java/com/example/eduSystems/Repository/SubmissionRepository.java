package com.example.eduSystems.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.eduSystems.models.tblSubmissions;

public interface SubmissionRepository extends JpaRepository<tblSubmissions, Integer> {
    @Query("SELECT s FROM tblSubmissions s JOIN s.user u WHERE s.subm.assignmentid = :assignmentId")     
    List<tblSubmissions> findBySubm_assignmentid(@Param("assignmentId") Integer assignmentId);
}
