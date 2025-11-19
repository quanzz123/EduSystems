package com.example.eduSystems.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class HomeController {
    @GetMapping({"", "/", "/index"})
    public String index(Model model){
        model.addAttribute("dashboardMessage", "Welcome to Admin Dashboard!");
        return "admin/index";
    }
}
