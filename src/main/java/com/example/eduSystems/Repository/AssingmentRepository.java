package com.example.eduSystems.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.eduSystems.dto.tblAssignmentDto;
import com.example.eduSystems.models.tblAssignments;

public interface AssingmentRepository extends JpaRepository<tblAssignments, Integer> {
    
    @Query("SELECT a FROM tblAssignments a WHERE a.clas.classid = :classid AND a.active = true")
    List<tblAssignments> findByClas_classid(@Param("classid") Integer classid);
}
