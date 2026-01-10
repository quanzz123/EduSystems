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
}
