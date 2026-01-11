package com.example.eduSystems.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.eduSystems.Repository.ClassRepository;
import com.example.eduSystems.Repository.LessonRepository;
import com.example.eduSystems.dto.tblLessonDto;
import com.example.eduSystems.models.tblClasses;
import com.example.eduSystems.models.tblLessons;

@Service
public class LessonService {
    @Autowired
    LessonRepository lessonRepo;

    @Autowired
    ClassRepository classRepo;

    public List<tblLessonDto> getLessonsByClassId(Integer classid) {
        List<tblLessons> lessons = lessonRepo.findByClas_classid(classid);
        List<tblLessonDto> result = new ArrayList<>();

        for(tblLessons l : lessons) {
            tblLessonDto dto = new tblLessonDto();

            dto.setLessonid(l.getLessonid());
            dto.setTitle(l.getTitle());
            dto.setDescription(l.getDescription());
            dto.setOrderidx(l.getOrderidx());
            dto.setCreatedate(l.getCreatedate());
            dto.setActive(l.isActive());
            if(l.getClas() != null) {
                dto.setClassid(l.getClas().getClassid());
            }
            if(l.getCreateby() != null) {
                dto.setCreatebyid(l.getCreateby().getUserid());
            }

            result.add(dto);
        }

        return result;
    }

    public void SaveLesson(tblLessonDto lessonDto) {
        tblClasses classes = classRepo.findById(lessonDto.getClassid()).get();
        tblLessons lesson = new tblLessons();
        lesson.setTitle(lessonDto.getTitle());
        lesson.setDescription(lessonDto.getDescription());
        lesson.setOrderidx(lessonDto.getOrderidx());
        lesson.setCreatedate(new Date());
        lesson.setActive(lessonDto.isActive());
        lesson.setClas(classes);
        lesson.setCreateby(classes.getTeacher());
        lessonRepo.save(lesson);
    }
}
