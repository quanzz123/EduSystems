package com.example.eduSystems.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.eduSystems.utilities.Functions;

@Controller
@RequestMapping("/admin")
public class HomeController {
    @GetMapping({"", "/", "/index"})
    public String index(Model model){
         if (!Functions.isLogin()) {
            return "redirect:/admin/auth/login";
        }
        if(!Functions.checkRole("ADMIN") && !Functions.checkRole("TEACHER")) {
            return "/admin/notfound/deny";
        }
        model.addAttribute("userName", Functions.get_UserName());
        model.addAttribute("dashboardMessage", "Welcome to Admin Dashboard!");
        return "admin/index";
    }
}
