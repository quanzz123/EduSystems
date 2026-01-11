package com.example.eduSystems.controllers.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.eduSystems.dto.tblLessonDto;
import com.example.eduSystems.models.tblClasses;
import com.example.eduSystems.services.ClassService;
import com.example.eduSystems.services.LessonService;

import org.springframework.web.bind.annotation.RequestParam;




@Controller
@RequestMapping("/admin/lessons")
public class LessonController {
    
    @Autowired
    ClassService classservice;

    @Autowired
    LessonService lessonService;

    @GetMapping("")
    public String index(Model model) {
        List<tblClasses> classes = classservice.FinClassByTeacher();
        model.addAttribute("classes", classes);
        for (tblClasses cls : classes) {
            System.out.println("Class Name: " + cls.getClassname());
        }
        return "admin/lessons/index";
    }

    @GetMapping("/list")
    public String getLessonsByClassId(@RequestParam("classid") String classid, Model model) {
        
        List<tblLessonDto> lessons = lessonService.getLessonsByClassId(Integer.parseInt(classid));
        model.addAttribute("lessons", lessons);
        return "admin/lessons/list";
    }
    

}
