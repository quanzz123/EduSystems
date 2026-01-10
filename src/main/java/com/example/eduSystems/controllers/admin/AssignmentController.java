package com.example.eduSystems.controllers.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.eduSystems.dto.tblAssignmentDto;
import com.example.eduSystems.models.tblClasses;
import com.example.eduSystems.services.AssignmentService;
import com.example.eduSystems.services.ClassService;

@Controller
@RequestMapping("/admin/assignments")
public class AssignmentController {
    @Autowired
    AssignmentService aService;

    @Autowired
    ClassService classservice; 

    @GetMapping("")
    public String list(Model model) {
       
        List<tblClasses> classes = classservice.FinClassByTeacher();
        model.addAttribute("classes", classes);
        for (tblClasses cls : classes) {
            System.out.println("Class Name: " + cls.getClassname());
        }
        
        return "admin/assignments/list";
    }

    @GetMapping("/index")
    public String index(@RequestParam("classid") Integer classid, Model model) {
        List<tblAssignmentDto> assignments = aService.getAssignmentsByclassid(classid);
        model.addAttribute("assignments", assignments);
        model.addAttribute("classid", classid);
        return "/admin/assignments/index";
    }

    @GetMapping("/create")
    public String create(@RequestParam("classid") Integer classid, Model model) {
        tblAssignmentDto assignmentDto = new tblAssignmentDto();
        assignmentDto.setClassid(classid);
        model.addAttribute("assignment", assignmentDto);
        return "/admin/assignments/create";
    }
    
}
     