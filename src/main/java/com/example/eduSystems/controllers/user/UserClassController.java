package com.example.eduSystems.controllers.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.eduSystems.models.tblUsers;
import com.example.eduSystems.services.ClassService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user/classes")
public class UserClassController {

    @Autowired
    ClassService classService;

    @GetMapping
    public String classes(Model model, HttpSession session) {

        tblUsers student = (tblUsers) session.getAttribute("user");

        if (student == null) {
            return "redirect:/login";
        }

        Integer studentId = student.getUserid(); // KHÔNG NULL

        model.addAttribute(
            "classes",
            classService.findClassesByStudentId(studentId)
        );

        // ht người dùng
        model.addAttribute("currentUser", student);

        return "user/classes/index";
    }
}
