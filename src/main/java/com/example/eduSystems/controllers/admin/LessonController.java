package com.example.eduSystems.controllers.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.eduSystems.dto.tblLessonDto;
import com.example.eduSystems.models.tblClasses;
import com.example.eduSystems.services.ClassService;
import com.example.eduSystems.services.LessonService;

import groovy.lang.Binding;
import jakarta.validation.Valid;

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
        Integer cid = Integer.parseInt(classid);
        
        List<tblLessonDto> lessons = lessonService.getLessonsByClassId(cid);
        model.addAttribute("lessons", lessons);
        model.addAttribute("classid", classid);
        return "admin/lessons/list";
    }
    @GetMapping("/create")
    public String create(@RequestParam("classid") String classid, Model model) {
        tblLessonDto lessonDto = new tblLessonDto();
        model.addAttribute("lessonDto", lessonDto);
        model.addAttribute("classid", classid);
        return "admin/lessons/create";
    }
    @PostMapping("/create")
    public String SaveLesson(@Valid @ModelAttribute("lessonDto") tblLessonDto lessonDto, Model model, BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("classid", lessonDto.getClassid());
            return "admin/lessons/create";
        }
        
        lessonService.SaveLesson(lessonDto);
        return "redirect:/admin/lessons/list?classid=" + lessonDto.getClassid();
    }
    @GetMapping("/edit")
    public String update(@RequestParam("id") String lessonid, Model model) {
        Integer lid = Integer.parseInt(lessonid);
        tblLessonDto lessonDto = lessonService.getLessonById(lid);
        model.addAttribute("lessonDto", lessonDto);
        model.addAttribute("classid", lessonDto.getClassid());
        return "admin/lessons/edit";
    }

    @PostMapping("/edit")
    public String UpdateLesson(@Valid @ModelAttribute("lessonDto") tblLessonDto lessonDto, Model model, BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("classid", lessonDto.getClassid());
            return "admin/lessons/edit";
        }

        lessonService.UpdateLesson(lessonDto);
        return "redirect:/admin/lessons/list?classid=" + lessonDto.getClassid();
    }

}
