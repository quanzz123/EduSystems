package com.example.eduSystems.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.eduSystems.Repository.ClassMembersRepository;
import com.example.eduSystems.Repository.ClassRepository;
import com.example.eduSystems.Repository.UsersRepository;
import com.example.eduSystems.dto.tblClassMembersDto;
import com.example.eduSystems.models.tblClassMembers;
import com.example.eduSystems.models.tblClasses;
import com.example.eduSystems.models.tblUsers;

@Service
public class ClassMemberService {
    @Autowired
    private ClassMembersRepository classMemberRepo;
    @Autowired
    private ClassRepository classRepo;

    @Autowired
    private UsersRepository userRepo;

    @Autowired
    private ClassService classService;



    public List<tblClassMembers> getAllMembersWitdClassid(int classid) {
        return classMemberRepo.findAllByClassId(classid);
    }

    public tblClassMembersDto getClassMembersById(int memberid) {
        tblClassMembersDto Dto = new tblClassMembersDto();

        tblClassMembers members = classMemberRepo.findById(memberid).orElseThrow(()
                -> new RuntimeException("Class member not found with id " + memberid));

        Dto.setMemberid(members.getMemberid());
        Dto.setFinalscore(members.getFinalscore());
        Dto.setJoindate(members.getJoindate());
        Dto.setNote(members.getNote());
        Dto.setProgress(members.getProgress());
        Dto.setStatus(members.getStatus());
        Dto.setUserid(members.getUser().getUserid());
        Dto.setClassid(members.getClas().getClassid());
        return Dto;
    }

    public tblClassMembers  Create(int classId, tblClassMembersDto memberDto) {
        tblClasses cls = classRepo.findById(classId).get();

        tblUsers user = userRepo.findById(memberDto.getUserid()).get();

        tblClassMembers member = new tblClassMembers();

        member.setClas(cls);
        member.setStatus(memberDto.getStatus());
        member.setJoindate(new Date());
        member.setNote(memberDto.getNote());
        member.setUser(user);
        return classMemberRepo.save(member);
    }

    public void update(tblClassMembersDto dto) {
        tblClasses classes = classRepo.findById(dto.getClassid()).get();
        //tblUsers user = userRepo.findById(dto.getUserid()).get();

        tblClassMembers member = classMemberRepo.findById(dto.getMemberid()).orElseThrow(
                () -> new RuntimeException("Class member not found with id " + dto.getMemberid())
        );

        member.setClas(classes);
        //member.setUser(user);
        member.setStatus(dto.getStatus());
        member.setProgress(dto.getProgress());
        member.setNote(dto.getNote());
        member.setFinalscore(dto.getFinalscore());

        classMemberRepo.save(member);
    }
        /**
     * Đếm số lớp học mà user đang tham gia
     */
    public long countClassesByUserId(int userId) {
        try {
            return classMemberRepo.countClassesByUserId(userId);
        } catch (Exception e) {
            System.err.println("Error counting classes for user " + userId + ": " + e.getMessage());
            return 0;
        }
    }

    /**
     * Lấy danh sách lớp học của user
     */
    public List<tblClassMembers> getClassMembersByUserId(int userId) {
        try {
            return classMemberRepo.findByUserId(userId);
        } catch (Exception e) {
            System.err.println("Error getting class members for user " + userId + ": " + e.getMessage());
            return new ArrayList<>();
        }
    }

}
