package com.example.eduSystems.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.eduSystems.dto.tblUsersDto;
import com.example.eduSystems.services.UserService;
import com.example.eduSystems.utilities.Functions;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin/auth")
public class AuthController {

    @Autowired
    UserService userService;

    @GetMapping("/login")
    public String login() {
        return "admin/auth/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                       @RequestParam("password") String password,
                       Model model,HttpSession session) {
        
       
        if (username == null || username.trim().isEmpty()) {
            model.addAttribute("error", "Vui lòng nhập tên đăng nhập");
            return "/admin/auth/login";
        }
        
        if (password == null || password.trim().isEmpty()) {
            model.addAttribute("error", "Vui lòng nhập mật khẩu");
            return "/admin/auth/login";
        }
        
        
        tblUsersDto user = userService.authenticate(username, password);
        
        if (user != null) {
            
            Functions.setUserInfo(
                user.getUserid(),
                user.getUsername(),
                user.getEmail(),
                user.getFullname(),
                user.getRoleid(),
                user.getRolename()
            );
            session.setAttribute("FULLNAME", Functions.get_FullName());
            
            // Redirect theo role
            // if (Functions.checkRole(1)) { // Admin
            //     return "redirect:/admin/dashboard";
            // } else if (Functions.checkRole(2)) { // Teacher
            //     return "redirect:/teacher/dashboard";
            // } else { // Student
            //     return "redirect:/student/dashboard";
            // }
            return "redirect:/admin";
        }
        
        // Đăng nhập thất bại
        model.addAttribute("error", "Tên đăng nhập hoặc mật khẩu không đúng");
        model.addAttribute("username", username);
        return "/admin/auth/login";
    }

    @GetMapping("/logout")
    public String logout() {
        Functions.clear();
        return "redirect:/admin/auth/login?logout=true";
    }

}
