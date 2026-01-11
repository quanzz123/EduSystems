package com.example.eduSystems.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.eduSystems.models.tblLessonContent;

public interface LessonContentRepository extends JpaRepository<tblLessonContent, Integer> {

}
