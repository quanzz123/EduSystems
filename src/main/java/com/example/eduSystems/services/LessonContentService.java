package com.example.eduSystems.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.eduSystems.Repository.LessonContentRepository;
import com.example.eduSystems.dto.tblLessonContentDto;
import com.example.eduSystems.models.tblLessonContent;
import com.example.eduSystems.models.tblLessons;

@Service
public class LessonContentService {
    @Autowired
    LessonContentRepository lessonContentRepo;

    public List<tblLessonContentDto> getLessonContentsByLessonId(Integer lessonid) {
        List<tblLessonContent> contents = lessonContentRepo.findByLesson_lessonid(lessonid);

        List<tblLessonContentDto> result = new ArrayList<>();
        for (tblLessonContent content : contents) {
            tblLessonContentDto dto = new tblLessonContentDto();
            dto.setContentid(content.getContentid());
            dto.setTitle(content.getTitle());
            dto.setContenttype(content.getContenttype());
            dto.setContenturl(content.getContenturl());
            dto.setOrderidx(content.getOrderidx());
            dto.setDuration(content.getDuration());
            dto.setCreatedate(content.getCreatedate());
            dto.setLessonid(content.getLesson().getLessonid());
            result.add(dto);
        }
        return result;
    }

    public tblLessonContentDto getLessonContentById(Integer contentid) {
        tblLessonContent content = lessonContentRepo.findById(contentid).orElse(null);
        if (content == null) {
            return null;
        }
        tblLessonContentDto dto = new tblLessonContentDto();
        dto.setContentid(content.getContentid());
        dto.setTitle(content.getTitle());
        dto.setContenttype(content.getContenttype());
        dto.setContenturl(content.getContenturl());
        dto.setOrderidx(content.getOrderidx());
        dto.setDuration(content.getDuration());
        dto.setCreatedate(content.getCreatedate());
        dto.setLessonid(content.getLesson().getLessonid());
        return dto;
    }

    public void save(tblLessonContentDto lessonContentDto) {
        tblLessonContent content = new tblLessonContent();
        content.setTitle(lessonContentDto.getTitle());
        content.setContenttype(lessonContentDto.getContenttype());
        content.setContenturl(lessonContentDto.getContenturl());
        content.setOrderidx(lessonContentDto.getOrderidx());
        content.setDuration(lessonContentDto.getDuration());
        content.setCreatedate(lessonContentDto.getCreatedate());
       
        if(lessonContentDto.getLessonid() != null) {
            tblLessons lesson = new tblLessons();
            lesson.setLessonid(lessonContentDto.getLessonid());
            content.setLesson(lesson);
        }
        lessonContentRepo.save(content);
    }

    public void delete(Integer contentid) {
        lessonContentRepo.deleteById(contentid);
    }

    public void updateLessonContent(tblLessonContentDto lessonContentDto) {
        tblLessonContent content = lessonContentRepo.findById(lessonContentDto.getContentid()).orElse(null);
        if (content != null) {
            content.setTitle(lessonContentDto.getTitle());
            content.setContenttype(lessonContentDto.getContenttype());
            content.setContenturl(lessonContentDto.getContenturl());
            content.setOrderidx(lessonContentDto.getOrderidx());
            content.setDuration(lessonContentDto.getDuration());
            
            lessonContentRepo.save(content);
        }
    }
}
    