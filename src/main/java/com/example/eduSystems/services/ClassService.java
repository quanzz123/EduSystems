package com.example.eduSystems.services;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.eduSystems.Repository.ClassRepository;
import com.example.eduSystems.Repository.UsersRepository;
import com.example.eduSystems.dto.tblClassesDto;
import com.example.eduSystems.models.tblClasses;
import com.example.eduSystems.models.tblUsers;

import jakarta.persistence.criteria.CriteriaBuilder.In;

@Service
public class ClassService {
    @Autowired
    ClassRepository classRepo;
    @Autowired
    UsersRepository userRepo;

    public List<tblClassesDto> getAllClasses() {
        return classRepo.findallActive().stream().map(this::toDto).toList();
    }
    public tblClassesDto getClassById(int id) {
        tblClasses classes = classRepo.findById(id).orElse(null);
        return toDto(classes);
    }

    public List<tblClasses> FinClassByTeacher(Integer userid) {
        return classRepo.findByUserid(userid);
    }

    public void delete(Integer id) {
        tblClasses classes = classRepo.findById(id).orElse(null);
        if (classes != null) {
            classes.setActive(false);
            classRepo.save(classes);
        }
    }

    public void update(tblClassesDto clsDto) throws IOException {
        tblClasses classes = classRepo.findById(clsDto.getClassid())
                .orElse(null);

        mapDtoToEntity(clsDto, classes);
        classRepo.save(classes);
    }
    public void create(tblClassesDto dto) throws IOException {
        tblClasses cls = new tblClasses();
        cls.setClassname(dto.getClassname());
        cls.setDescription(dto.getDescription());
        cls.setCreatedate(new Date());
        cls.setStartdate(dto.getStartdate());
        cls.setEnddate(dto.getEnddate());
        cls.setSubject(dto.getSubject());
        cls.setSchedule(dto.getSchedule());
        cls.setActive(dto.getActive());

        tblUsers user = userRepo.findById(dto.getTeacherid()).get();
        cls.setTeacher(user);
        classRepo.save(cls);
    }

    private tblClassesDto toDto(tblClasses cls) {
        tblClassesDto clsDto = new tblClassesDto();
        clsDto.setClassid(cls.getClassid());
        clsDto.setClassname(cls.getClassname());
        clsDto.setSubject(cls.getSubject());
        clsDto.setStartdate(cls.getStartdate());
        clsDto.setEnddate(cls.getEnddate());
        clsDto.setCreatedate(cls.getCreatedate());
        clsDto.setDescription(cls.getDescription());
        clsDto.setSchedule(cls.getSchedule());
        clsDto.setActive(cls.isActive());

        if (cls.getTeacher() != null) {
            clsDto.setTeacherid(cls.getTeacher().getUserid());
            clsDto.setTeachername(cls.getTeacher().getUsername());
        }

        return clsDto;
    }

    private void mapDtoToEntity(tblClassesDto dto, tblClasses classes) throws IOException {
        classes.setClassname(dto.getClassname());
        classes.setSubject(dto.getSubject());
        classes.setStartdate(dto.getStartdate());
        classes.setEnddate(dto.getEnddate());
        classes.setDescription(dto.getDescription());
        classes.setSchedule(dto.getSchedule());
        classes.setActive(dto.getActive());

        if(classes.getClassid() == 0) {
            classes.setCreatedate(dto.getCreatedate());
        } else {
            classes.setModifieddate(new Date());
        }

        tblUsers user = userRepo.findById(dto.getTeacherid()).get();
        classes.setTeacher(user);
    }
    
    /**
     * Tìm class theo id (trả về entity)
     */
    public tblClasses findById(int id) {
        try {
            return classRepo.findById(id).orElse(null);
        } catch (Exception e) {
            System.err.println("Error finding class: " + e.getMessage());
            return null;
        }
    }

    /**
     * Lấy tất cả lớp học (trả về entity)
     */
    public List<tblClasses> findAll() {
        try {
            return classRepo.findAll();
        } catch (Exception e) {
            System.err.println("Error finding all classes: " + e.getMessage());
            return new java.util.ArrayList<>();
        }
    }

    /**
     * Lấy lớp học theo giáo viên (trả về entity)
     */
    public List<tblClasses> findByTeacherId(int teacherId) {
        try {
            return classRepo.findByUser_userid(teacherId);
        } catch (Exception e) {
            System.err.println("Error finding classes by teacher: " + e.getMessage());
            return new java.util.ArrayList<>();
        }
    }

    /**
     * Lấy lớp học đang active (trả về entity)
     */
    public List<tblClasses> findActiveClasses() {
        try {
            return classRepo.findByActiveTrue();
        } catch (Exception e) {
            System.err.println("Error finding active classes: " + e.getMessage());
            return new java.util.ArrayList<>();
        }
    }

    /**
     * Lưu class entity
     */
    public tblClasses save(tblClasses classEntity) {
        try {
            return classRepo.save(classEntity);
        } catch (Exception e) {
            System.err.println("Error saving class: " + e.getMessage());
            return null;
        }
    }

    /**
     * Xóa class theo id
     */
    public void deleteById(int id) {
        try {
            classRepo.deleteById(id);
        } catch (Exception e) {
            System.err.println("Error deleting class: " + e.getMessage());
        }
    }

    /**
     * Lấy danh sách lớp học của sinh viên (qua class_members)
     */
    public List<tblClasses> findClassesByStudentId(int studentId) {
        try {
            return classRepo.findClassesByStudentId(studentId);
        } catch (Exception e) {
            System.err.println("Error finding classes by student: " + e.getMessage());
            return new java.util.ArrayList<>();
        }
    }
}