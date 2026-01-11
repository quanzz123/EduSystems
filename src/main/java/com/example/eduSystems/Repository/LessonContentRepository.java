package com.example.eduSystems.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.eduSystems.models.tblLessonContent;
import com.example.eduSystems.models.tblLessons;

public interface LessonContentRepository extends JpaRepository<tblLessonContent, Integer> {
    @Query("SELECT a FROM tblLessonContent a WHERE a.lesson.lessonid = :lessonid ")
    List<tblLessonContent> findByLesson_lessonid(@Param("lessonid") Integer lessonid);
}
