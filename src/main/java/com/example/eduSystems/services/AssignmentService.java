package com.example.eduSystems.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.eduSystems.Repository.AssingmentRepository;
import com.example.eduSystems.Repository.ClassRepository;
import com.example.eduSystems.dto.tblAssignmentDto;
import com.example.eduSystems.models.tblAssignments;
import com.example.eduSystems.models.tblClasses;

@Service
public class AssignmentService {
    @Autowired
    AssingmentRepository assignRepo;

    @Autowired
    ClassService clsService;

    @Autowired
    ClassRepository classRepo;

    public List<tblClasses> FinClassByTeacher(){
        return classRepo.FindAllWithTeacher();
    }

    public List<tblAssignmentDto> getAssignmentsByclassid(Integer classid) {
        List<tblAssignments> assignments = assignRepo.findByClas_classid(classid);
        List<tblAssignmentDto> result = new ArrayList<>();

        for(tblAssignments a : assignments) {
            tblAssignmentDto dto = new tblAssignmentDto();

            dto.setAssignmentid(a.getAssignmentid());
            dto.setTitle(a.getTitle());
            dto.setDescription(a.getDescription());
            dto.setFileurl(a.getFileurl());
            dto.setDeadline(a.getDeadline());
            dto.setCreatedate(a.getCreatedate());
            dto.setModifieddate(a.getModifieddate());
            dto.setActive(a.isActive());
            if(a.getClas() != null) {
                dto.setClassid(a.getClas().getClassid());
            }

            result.add(dto);
        }

        return result;
    }

    public void SaveAssignment(tblAssignmentDto assignmentDto) {

        tblClasses classes = classRepo.findById(assignmentDto.getClassid()).get();

        tblAssignments assignment = new tblAssignments();
        assignment.setTitle(assignmentDto.getTitle());
        assignment.setDescription(assignmentDto.getDescription());
        assignment.setFileurl(assignmentDto.getFileurl());
        assignment.setDeadline(assignmentDto.getDeadline());
        assignment.setCreatedate(assignmentDto.getCreatedate());
        assignment.setActive(assignmentDto.getActive());
        assignment.setClas(classes);
        assignRepo.save(assignment);

    }

    public void delete(int id) {
        tblAssignments assignment = assignRepo.findById(id).orElse(null);
        assignment.setActive(false);
        assignRepo.save(assignment);
    }

    public tblAssignmentDto getAssignmentById(int id) {
        tblAssignments a = assignRepo.findById(id).orElse(null);
        tblAssignmentDto dto = new tblAssignmentDto();

        dto.setAssignmentid(a.getAssignmentid());
        dto.setTitle(a.getTitle());
        dto.setDescription(a.getDescription());
        dto.setFileurl(a.getFileurl());
        dto.setDeadline(a.getDeadline());
        dto.setCreatedate(a.getCreatedate());
        dto.setModifieddate(a.getModifieddate());
        dto.setActive(a.isActive());
        if(a.getClas() != null) {
            dto.setClassid(a.getClas().getClassid());
        }

        return dto;
    }

    public void updateAssignment(tblAssignmentDto assignmentDto) {
        tblAssignments assignment = assignRepo.findById(assignmentDto.getAssignmentid()).orElse(null);

        assignment.setTitle(assignmentDto.getTitle());
        assignment.setDescription(assignmentDto.getDescription());
        if(assignmentDto.getFileurl() != null) {
            assignment.setFileurl(assignmentDto.getFileurl());
        }
        assignment.setDeadline(assignmentDto.getDeadline());
        assignment.setModifieddate(new java.util.Date());
        assignment.setActive(assignmentDto.getActive());

        assignRepo.save(assignment);
    }
}
