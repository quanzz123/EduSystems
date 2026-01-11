package com.example.eduSystems.controllers.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.eduSystems.models.tblUsers;
import com.example.eduSystems.services.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserLoginController {

    @Autowired
    private UserService userService;

    /**
     * Hiển thị trang login
     */
    @GetMapping("/login")
    public String showLoginPage() {
        return "user/login"; // templates/login.html
    }

    /**
     * Xử lý đăng nhập
     */
    @PostMapping("/login")
    public String login(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            HttpSession session,
            Model model) {
        
        try {
            // Tìm user theo username
            tblUsers user = userService.findByUsername(username);
            
            if (user == null) {
                model.addAttribute("error", "Tên đăng nhập không tồn tại!");
                return "user/login";
            }
            
            // Kiểm tra password (giả sử password đã được mã hóa)
            if (!user.getPasswordhash().equals(password)) {
                model.addAttribute("error", "Mật khẩu không đúng!");
                return "user/login";
            }
            
            // Kiểm tra trạng thái active
            if (!user.isActive()) {
                model.addAttribute("error", "Tài khoản đã bị vô hiệu hóa!");
                return "user/login";
            }
            
            // Lưu thông tin vào session
            session.setAttribute("username", username);
            session.setAttribute("userid", user.getUserid());
            session.setAttribute("roleid", user.getRole().getRoleid());
            // Lưu object user vào session (QUAN TRỌNG)
            session.setAttribute("user", user);

            // Có thể giữ lại nếu cần
            session.setAttribute("userid", user.getUserid());
            session.setAttribute("roleid", user.getRole().getRoleid());

            // Redirect theo role
            int roleId = user.getRole().getRoleid();
            
            if (roleId == 1) {
                // Admin
                return "redirect:/admin";
            } else if (roleId == 2) {
                // Teacher
                return "redirect:/teacher";
            } else if (roleId == 3) {
                // Student
                return "redirect:/user";
            } else {
                model.addAttribute("error", "Role không hợp lệ!");
                return "user/login";
            }
            
        } catch (Exception e) {
            System.err.println("Login error: " + e.getMessage());
            e.printStackTrace();
            model.addAttribute("error", "Có lỗi xảy ra, vui lòng thử lại!");
            return "user/login";
        }
    }

    /**
     * Đăng xuất
     */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
    
    /**
     * Trang chủ - redirect về login
     */
    @GetMapping("/")
    public String home(HttpSession session) {

        tblUsers user = (tblUsers) session.getAttribute("user");

        if (user != null) {
            int roleId = user.getRole().getRoleid();

            if (roleId == 1) return "redirect:/admin";
            if (roleId == 2) return "redirect:/teacher";
            if (roleId == 3) return "redirect:/user";
        }

        return "redirect:/login";
    }

}