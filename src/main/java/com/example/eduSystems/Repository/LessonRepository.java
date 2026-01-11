package com.example.eduSystems.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.eduSystems.models.tblLessons;

public interface LessonRepository extends JpaRepository<tblLessons, Integer> {

}
