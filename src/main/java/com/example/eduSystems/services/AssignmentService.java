package com.example.eduSystems.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.eduSystems.Repository.AssingmentRepository;
import com.example.eduSystems.Repository.ClassRepository;
import com.example.eduSystems.dto.tblAssignmentDto;
import com.example.eduSystems.models.tblAssignments;
import com.example.eduSystems.models.tblClasses;

@Service
public class AssignmentService {

    @Autowired
    AssingmentRepository assignRepo;

    @Autowired
    ClassService clsService;

    @Autowired
    ClassRepository classRepo;

    public List<tblClasses> FinClassByTeacher() {
        return classRepo.FindAllWithTeacher();
    }

    public List<tblAssignmentDto> getAssignmentsByclassid(Integer classid) {
        List<tblAssignments> assignments = assignRepo.findByClas_classid(classid);
        List<tblAssignmentDto> result = new ArrayList<>();

        for (tblAssignments a : assignments) {
            tblAssignmentDto dto = new tblAssignmentDto();

            dto.setAssignmentid(a.getAssignmentid());
            dto.setTitle(a.getTitle());
            dto.setDescription(a.getDescription());
            dto.setFileurl(a.getFileurl());
            dto.setDeadline(a.getDeadline());
            dto.setCreatedate(a.getCreatedate());
            dto.setModifieddate(a.getModifieddate());
            dto.setActive(a.isActive());
            if (a.getClas() != null) {
                dto.setClassid(a.getClas().getClassid());
            }

            result.add(dto);
        }

        return result;
    }

    public void SaveAssignment(tblAssignmentDto assignmentDto) {

        tblClasses classes = classRepo.findById(assignmentDto.getClassid()).get();

        tblAssignments assignment = new tblAssignments();
        assignment.setTitle(assignmentDto.getTitle());
        assignment.setDescription(assignmentDto.getDescription());
        assignment.setFileurl(assignmentDto.getFileurl());
        assignment.setDeadline(assignmentDto.getDeadline());
        assignment.setCreatedate(assignmentDto.getCreatedate());
        assignment.setActive(assignmentDto.getActive());
        assignment.setClas(classes);
        assignRepo.save(assignment);

    }

    public void delete(int id) {
        tblAssignments assignment = assignRepo.findById(id).orElse(null);
        assignment.setActive(false);
        assignRepo.save(assignment);
    }

    public tblAssignmentDto getAssignmentById(int id) {
        tblAssignments a = assignRepo.findById(id).orElse(null);
        tblAssignmentDto dto = new tblAssignmentDto();

        dto.setAssignmentid(a.getAssignmentid());
        dto.setTitle(a.getTitle());
        dto.setDescription(a.getDescription());
        dto.setFileurl(a.getFileurl());
        dto.setDeadline(a.getDeadline());
        dto.setCreatedate(a.getCreatedate());
        dto.setModifieddate(a.getModifieddate());
        dto.setActive(a.isActive());
        if (a.getClas() != null) {
            dto.setClassid(a.getClas().getClassid());
        }

        return dto;
    }

    public void updateAssignment(tblAssignmentDto assignmentDto) {
        tblAssignments assignment = assignRepo.findById(assignmentDto.getAssignmentid()).orElse(null);

        assignment.setTitle(assignmentDto.getTitle());
        assignment.setDescription(assignmentDto.getDescription());
        if (assignmentDto.getFileurl() != null) {
            assignment.setFileurl(assignmentDto.getFileurl());
        }
        assignment.setDeadline(assignmentDto.getDeadline());
        assignment.setModifieddate(new java.util.Date());
        assignment.setActive(assignmentDto.getActive());

        assignRepo.save(assignment);
    }

    /**
     * Đếm tổng số bài tập của sinh viên (qua các lớp đã tham gia)
     */
    public long countAssignmentsByUserId(int userId) {
        try {
            List<tblAssignments> assignments = assignRepo.findAssignmentsByUserId(userId);
            return assignments != null ? assignments.size() : 0;
        } catch (Exception e) {
            System.err.println("Error counting assignments: " + e.getMessage());
            return 0;
        }
    }

    /**
     * Lấy danh sách tất cả bài tập của sinh viên
     */
    public List<tblAssignments> getAssignmentsByUserId(int userId) {
        try {
            return assignRepo.findAssignmentsByUserId(userId);
        } catch (Exception e) {
            System.err.println("Error getting assignments: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * Lấy 5 bài tập gần nhất với thông tin chi tiết cho trang chủ
     */
    public List<Map<String, Object>> getRecentAssignmentsWithDetails(int userId) {
        List<Map<String, Object>> result = new ArrayList<>();

        try {
            // Lấy danh sách bài tập của user
            List<tblAssignments> assignments = assignRepo.findRecentAssignmentsByUserId(userId);

            // Giới hạn 5 bài gần nhất
            int count = 0;
            for (tblAssignments assignment : assignments) {
                if (count >= 5)
                    break;

                Map<String, Object> assignmentData = new HashMap<>();
                assignmentData.put("assignmentid", assignment.getAssignmentid());
                assignmentData.put("title", assignment.getTitle());
                assignmentData.put("description", assignment.getDescription());
                assignmentData.put("deadline", assignment.getDeadline());

                // Lấy thông tin lớp học
                if (assignment.getClas() != null) {
                    assignmentData.put("className", assignment.getClas().getClassname());
                } else {
                    assignmentData.put("className", "Không xác định");
                }

                // Xác định trạng thái bài tập
                String status = determineAssignmentStatus(assignment.getDeadline());
                assignmentData.put("status", status);

                result.add(assignmentData);
                count++;
            }
        } catch (Exception e) {
            System.err.println("Error getting recent assignments: " + e.getMessage());
            e.printStackTrace();
        }

        return result;
    }

    /**
     * Xác định trạng thái bài tập dựa vào deadline
     */
    private String determineAssignmentStatus(Date deadline) {
        if (deadline == null) {
            return "Chưa có hạn";
        }

        Date now = new Date();

        // Tính ngày 3 ngày sau
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(Calendar.DAY_OF_MONTH, 3);
        Date threeDaysLater = calendar.getTime();

        if (deadline.before(now)) {
            return "Quá hạn";
        } else if (deadline.before(threeDaysLater)) {
            return "Sắp đến hạn";
        } else {
            return "Đang mở";
        }
    }

    /**
     * Tìm assignment theo id
     */
    public tblAssignments findById(int id) {
        try {
            return assignRepo.findById(id).orElse(null);
        } catch (Exception e) {
            System.err.println("Error finding assignment: " + e.getMessage());
            return null;
        }
    }

    /**
     * Lưu assignment
     */
    public tblAssignments save(tblAssignments assignment) {
        try {
            return assignRepo.save(assignment);
        } catch (Exception e) {
            System.err.println("Error saving assignment: " + e.getMessage());
            return null;
        }
    }
}
