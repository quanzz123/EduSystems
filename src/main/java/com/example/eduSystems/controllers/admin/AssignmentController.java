package com.example.eduSystems.controllers.admin;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.eduSystems.dto.tblAssignmentDto;
import com.example.eduSystems.models.tblClasses;
import com.example.eduSystems.services.AssignmentService;
import com.example.eduSystems.services.ClassService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

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

    @PostMapping("/create")
    public String SaveAssignment(@Valid @ModelAttribute("assignment") tblAssignmentDto assignmentDto,
            BindingResult result, @RequestParam("file") MultipartFile file
            ) throws Exception {
        if (result.hasErrors()) {
            return "admin/assignments/create";
        }

        if (file != null && !file.isEmpty()) {

            String uploadDir = System.getProperty("user.dir") + "/uploads/assignments";

            File dir = new File(uploadDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            File saveFile = new File(uploadDir, fileName);

            file.transferTo(saveFile);

            assignmentDto.setFileurl("/files/assignments/" + fileName);
            System.out.println("UPLOAD DIR = " + uploadDir);

        }
        assignmentDto.setCreatedate(new Date());
        aService.SaveAssignment(assignmentDto);
        return "redirect:/admin/assignments/index?classid=" + assignmentDto.getClassid();
    }

    

}
