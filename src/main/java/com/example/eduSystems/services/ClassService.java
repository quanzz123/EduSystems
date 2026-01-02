package com.example.eduSystems.services;

import com.example.eduSystems.Repository.ClassRepository;
import com.example.eduSystems.Repository.UsersRepository;
import com.example.eduSystems.dto.tblClassesDto;
import com.example.eduSystems.dto.tblUsersDto;
import com.example.eduSystems.models.tblClasses;
import com.example.eduSystems.models.tblUsers;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
public class ClassService {
    @Autowired
    ClassRepository classRepo;
    @Autowired
    UsersRepository userRepo;

    public List<tblClassesDto> getAllClasses() {

        return classRepo.findallActive().stream().map(this::toDto).toList() ;
    }
    public tblClassesDto getClassById(int id) {
        tblClasses classes = classRepo.findById(id).orElse(null);
        return toDto(classes);
    }

    public List<tblClasses> FinClassByTeacher(){
        return classRepo.FindAllWithTeacher();
    }

    public void delete(Integer id) {
        tblClasses classes = classRepo.findById(id).orElse(null);
        classes.setActive(false);
        classRepo.save(classes);
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

    private tblClassesDto toDto (tblClasses cls) {
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
}
