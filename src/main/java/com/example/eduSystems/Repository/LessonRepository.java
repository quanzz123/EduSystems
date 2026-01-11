package com.example.eduSystems.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.eduSystems.models.tblAssignments;
import com.example.eduSystems.models.tblLessons;

public interface LessonRepository extends JpaRepository<tblLessons, Integer> {
    @Query("SELECT a FROM tblLessons a WHERE a.clas.classid = :classid AND a.active = true")
    List<tblLessons> findByClas_classid(@Param("classid") Integer classid);
}
