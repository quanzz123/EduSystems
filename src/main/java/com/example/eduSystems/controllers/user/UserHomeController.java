package com.example.eduSystems.controllers.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.eduSystems.models.tblUsers;
import com.example.eduSystems.services.AssignmentService;
import com.example.eduSystems.services.ClassMemberService;
import com.example.eduSystems.services.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserHomeController {

    @Autowired
    private ClassMemberService classMemberService;
    
    @Autowired
    private AssignmentService assignmentService;
    
    @Autowired
    private UserService userService;

    @GetMapping({"", "/"})
    public String index(Model model, HttpSession session) {
        try {
            // Lấy username từ session
            String username = (String) session.getAttribute("username");
            
            // Kiểm tra đăng nhập
            if (username == null || username.trim().isEmpty()) {
                return "redirect:/login";
            }
            
            // Lấy user từ database
            tblUsers currentUser = userService.findByUsername(username);
            
            if (currentUser == null) {
                // User không tồn tại, xóa session và redirect về login
                session.invalidate();
                return "redirect:/login";
            }
            
            int userId = currentUser.getUserid();
            
            // Lấy dữ liệu thống kê
            long totalClasses = getTotalClasses(userId);
            long totalAssignments = getTotalAssignments(userId);
            double progress = calculateAverageProgress(userId);
            List<Map<String, Object>> recentAssignments = getRecentAssignments(userId);
            
            // Đưa dữ liệu vào model
            model.addAttribute("totalClasses", totalClasses);
            model.addAttribute("totalAssignments", totalAssignments);
            model.addAttribute("progress", Math.round(progress));
            model.addAttribute("assignments", recentAssignments);
            model.addAttribute("currentUser", currentUser);
            
        } catch (Exception e) {
            System.err.println("Error in UserHomeController.index: " + e.getMessage());
            e.printStackTrace();
            
            // Set default values khi có lỗi
            model.addAttribute("totalClasses", 0);
            model.addAttribute("totalAssignments", 0);
            model.addAttribute("progress", 0);
            model.addAttribute("assignments", new ArrayList<>());
        }
        
        return "user/index";
    }
    
    /**
     * Đếm tổng số lớp học đang tham gia
     */
    private long getTotalClasses(int userId) {
        try {
            return classMemberService.countClassesByUserId(userId);
        } catch (Exception e) {
            System.err.println("Error counting classes: " + e.getMessage());
            return 0;
        }
    }
    
    /**
     * Đếm tổng số bài tập
     */
    private long getTotalAssignments(int userId) {
        try {
            return assignmentService.countAssignmentsByUserId(userId);
        } catch (Exception e) {
            System.err.println("Error counting assignments: " + e.getMessage());
            return 0;
        }
    }
    
    /**
     * Lấy danh sách bài tập gần đây (tối đa 5 bài)
     */
    private List<Map<String, Object>> getRecentAssignments(int userId) {
        try {
            return assignmentService.getRecentAssignmentsWithDetails(userId);
        } catch (Exception e) {
            System.err.println("Error getting recent assignments: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    
    /**
     * Tính tiến độ trung bình của sinh viên
     * Logic: số bài đã nộp / tổng số bài tập * 100
     */
    private double calculateAverageProgress(int userId) {
        try {
            // TODO: Cần implement SubmissionService để đếm số bài đã nộp
            // Tạm thời trả về 0
            
            /* Logic khi có SubmissionService:
            long submittedCount = submissionService.countSubmittedByUserId(userId);
            long totalAssignments = assignmentService.countAssignmentsByUserId(userId);
            
            if (totalAssignments > 0) {
                return (submittedCount * 100.0) / totalAssignments;
            }
            */
            
            return 0.0;
        } catch (Exception e) {
            System.err.println("Error calculating progress: " + e.getMessage());
            return 0.0;
        }
    }
}
