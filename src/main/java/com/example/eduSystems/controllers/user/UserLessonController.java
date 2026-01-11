package com.example.eduSystems.controllers.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.eduSystems.services.ClassService;
import com.example.eduSystems.services.LessonService;

@Controller
@RequestMapping("/user/classes")
public class UserLessonController {

    @Autowired
    LessonService lessonService;

    @Autowired
    ClassService classService;

    // ===============================
    // DANH SÁCH BÀI GIẢNG THEO LỚP
    // ===============================
    @GetMapping("/{classId}/lessons")
    public String lessons(
            @PathVariable int classId,
            Model model
    ) {

        model.addAttribute("classInfo",
                classService.getClassById(classId));

        model.addAttribute("lessons",
                lessonService.getLessonsByClassId(classId));

        return "user/lessons";
    }
}
