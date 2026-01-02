package com.example.eduSystems.controllers.admin;

import com.example.eduSystems.Repository.ClassRepository;
import com.example.eduSystems.Repository.UsersRepository;
import com.example.eduSystems.dto.tblClassMembersDto;
import com.example.eduSystems.dto.tblClassesDto;
import com.example.eduSystems.dto.tblUsersDto;
import com.example.eduSystems.models.tblClassMembers;
import com.example.eduSystems.models.tblClasses;
import com.example.eduSystems.services.ClassMemberService;
import com.example.eduSystems.services.ClassService;
import com.example.eduSystems.services.UserService;

import jakarta.persistence.PrePersist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/admin/classmembers")
public class ClassMembersController {
    @Autowired
    ClassService classservice;

    @Autowired
    ClassMemberService memberService;

    @Autowired
    UsersRepository userRepo;

    @Autowired
    UserService  userService;


    @GetMapping("")
    public String index(Model model) {
        List<tblClasses> classes = classservice.FinClassByTeacher();
        model.addAttribute("classes", classes);
        return "admin/classmembers/index";
    }

    @GetMapping("/details")
    public String ShowMembersPage(@RequestParam("id") int classId, Model model) {
        List<tblClassMembers> members = memberService.getAllMembersWitdClassid(classId);
        model.addAttribute("members", members);
        model.addAttribute("classId", classId);
        //System.out.println("userid" + members.get(0).getUser().getUserid());
        return "admin/classmembers/details";
    }
    @GetMapping("/create")
    public String ShowCreatePage(@RequestParam("classId") int ClassId, Model model) {
        tblClassMembersDto members = new tblClassMembersDto();
        List<tblUsersDto> userDto = userService.getAll();
        model.addAttribute("members", members);
        model.addAttribute("classId", ClassId);
        model.addAttribute("Users", userDto);
        System.out.println("class: " + ClassId);
        System.out.println("userid: " + userDto.get(0).getUserid());

        return "admin/classmembers/create";
    }
    @PostMapping("/create")
    public String CreateMember(@ModelAttribute("members") tblClassMembersDto member, @RequestParam("classId") int classId, Model model, BindingResult result) throws Exception {
        if(result.hasErrors()) {
            model.addAttribute("Users", userRepo.FindAllUsers());
            return "admin/classmembers/create";
        }
        memberService.Create(classId, member);
        return "redirect:/admin/classmembers/details?id="+classId;
    }
    @GetMapping("/edit")
    public String EditMembersPage(Model model, String id) {
        int memberId = Integer.parseInt(id);
        tblClassMembersDto MemberDto = memberService.getClassMembersById(memberId);
        List<tblClasses> classes = classservice.FinClassByTeacher();
        model.addAttribute("members", MemberDto);
        model.addAttribute("classes", classes);
        model.addAttribute("classId", MemberDto.getClassid());
        return "admin/classmembers/edit";
    }

    @PostMapping("/edit")
    public String UpdateMember(@ModelAttribute("members") tblClassMembersDto MemberDto, Model model, BindingResult result) throws Exception {
        if(result.hasErrors()) {
            List<tblClasses> classes = classservice.FinClassByTeacher();
            model.addAttribute("classes", classes);
        }
        memberService.update(MemberDto);
        return "redirect:/admin/classmembers/details?id="+MemberDto.getClassid();
    }
    
    
    
}
