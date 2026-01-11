package com.example.eduSystems.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.eduSystems.Repository.SubmissionRepository;
import com.example.eduSystems.dto.tblSubmissionDto;
import com.example.eduSystems.models.tblSubmissions;

@Service
public class SubmissionsService {
    @Autowired
    private SubmissionRepository submissionRepo;

    public List<tblSubmissionDto> getAllSubmissionsWithAssignmentID(Integer assignmentId) {
        
        List<tblSubmissions> submissions = submissionRepo.findBySubm_assignmentid(assignmentId);
        List<tblSubmissionDto> result = new ArrayList<>();
        for(tblSubmissions s : submissions) {
            tblSubmissionDto dto = new tblSubmissionDto();

            dto.setSubmissionid(s.getSubmissionid());
            dto.setFileurl(s.getFileurl());
            dto.setSubmitdate(s.getSubmitdate());
            dto.setScore(s.getScore());
            dto.setFeedback(s.getFeedback());
            dto.setStatus(s.getStatus());
            if(s.getSubm() != null) {
                dto.setAssignmentid(s.getSubm().getAssignmentid());
            }
            if(s.getUser() != null) {
                dto.setUserid(s.getUser().getUserid());
                dto.setFullname(s.getUser().getFullname());
            }

            result.add(dto);
        }
        return result;
    }

    public tblSubmissionDto getSubmissionById(Integer submissionId) {
        tblSubmissions s = submissionRepo.findById(submissionId).orElse(null);
        if (s == null) {
            return null;
        }

        tblSubmissionDto dto = new tblSubmissionDto();
        dto.setSubmissionid(s.getSubmissionid());
        dto.setFileurl(s.getFileurl());
        dto.setSubmitdate(s.getSubmitdate());
        dto.setScore(s.getScore());
        dto.setFeedback(s.getFeedback());
        dto.setStatus(s.getStatus());
        if (s.getSubm() != null) {
            dto.setAssignmentid(s.getSubm().getAssignmentid());
        }
        if (s.getUser() != null) {
            dto.setUserid(s.getUser().getUserid());
            dto.setFullname(s.getUser().getFullname());
        }

        return dto;
    }

    public void updateSubmission(tblSubmissionDto dto) {
        tblSubmissions s = submissionRepo.findById(dto.getSubmissionid()).orElse(null);
        if (s != null) {
            s.setScore(dto.getScore());
            s.setFeedback(dto.getFeedback());
            s.setStatus(dto.getStatus());
            submissionRepo.save(s);
        }
    }
 
}
