package com.example.eduSystems.controllers.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.eduSystems.models.tblUsers;
import com.example.eduSystems.services.AssignmentService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user/assignments")
public class UserAssignmentController {

    @Autowired
    private AssignmentService assignmentService;

    @GetMapping
    public String myAssignments(Model model, HttpSession session) {

        tblUsers user = (tblUsers) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }

        int userId = user.getUserid();

        model.addAttribute(
            "assignments",
            assignmentService.getAssignmentsByUserId(userId)
        );

        model.addAttribute("currentUser", user);
        
        return "user/assignments/index";
    }
}
