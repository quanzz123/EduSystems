package com.example.eduSystems.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.eduSystems.Repository.LessonContentRepository;
import com.example.eduSystems.dto.tblLessonContentDto;
import com.example.eduSystems.models.tblLessonContent;

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
}
    