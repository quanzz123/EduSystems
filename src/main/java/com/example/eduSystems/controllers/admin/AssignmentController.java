package com.example.eduSystems.controllers.admin;

import java.io.File;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.eduSystems.dto.tblAssignmentDto;
import com.example.eduSystems.dto.tblSubmissionDto;
// import com.example.eduSystems.models.tblAssignments;
import com.example.eduSystems.models.tblClasses;
import com.example.eduSystems.services.AssignmentService;
import com.example.eduSystems.services.ClassService;
import com.example.eduSystems.services.SubmissionsService;

// import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin/assignments")
public class AssignmentController {
    @Autowired
    AssignmentService aService;

    @Autowired
    ClassService classservice;

    @Autowired
    SubmissionsService submissionsService;

    @GetMapping("")
    public String list(Model model) {

        List<tblClasses> classes = classservice.FinClassByTeacher();
        model.addAttribute("classes", classes);
        for (tblClasses cls : classes) {
            System.out.println("Class Name: " + cls.getClassname());
        }

        return "admin/assignments/list";
    }

    @GetMapping("/index")
    public String index(@RequestParam("classid") Integer classid, Model model) {
        List<tblAssignmentDto> assignments = aService.getAssignmentsByclassid(classid);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        for (tblAssignmentDto a : assignments) {
            if (a.getDeadline() != null) {
            String formattedDeadline = formatter.format(a.getDeadline().toInstant()
                    .atZone(java.time.ZoneId.systemDefault())
                    .toLocalDate());
            a.setFormattedDeadline(formattedDeadline);
        }
        
        // Format createdate
        if (a.getCreatedate() != null) {
            String formattedCreatedate = formatter.format(a.getCreatedate().toInstant()
                    .atZone(java.time.ZoneId.systemDefault())
                    .toLocalDate());
            a.setFormattedCreatedate(formattedCreatedate);
        }
        }
        model.addAttribute("assignments", assignments);
        model.addAttribute("classid", classid);
        return "/admin/assignments/index";
    }

    @GetMapping("/create")
    public String create(@RequestParam("classid") Integer classid, Model model) {
        tblAssignmentDto assignmentDto = new tblAssignmentDto();
        assignmentDto.setClassid(classid);
        model.addAttribute("assignment", assignmentDto);
        return "/admin/assignments/create";
    }

    @PostMapping("/create")
    public String SaveAssignment(@Valid @ModelAttribute("assignment") tblAssignmentDto assignmentDto,
            BindingResult result, @RequestParam("file") MultipartFile file) throws Exception {
        if (result.hasErrors()) {
            return "admin/assignments/create";
        }

        if (file != null && !file.isEmpty()) {

            String uploadDir = System.getProperty("user.dir") + "/uploads/assignments";

            File dir = new File(uploadDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            File saveFile = new File(uploadDir, fileName);

            file.transferTo(saveFile);

            assignmentDto.setFileurl("/files/assignments/" + fileName);
            System.out.println("UPLOAD DIR = " + uploadDir);

        }
        assignmentDto.setCreatedate(new Date());
        aService.SaveAssignment(assignmentDto);
        return "redirect:/admin/assignments/index?classid=" + assignmentDto.getClassid();
    }

    @GetMapping("/delete")
    public String deleteAssignment(@RequestParam int id) {
        tblAssignmentDto Assgdto = aService.getAssignmentById(id);
        if (id == 0) {
            return "redirect:/admin/assignments";
        }
        aService.delete(id);
        return "redirect:/admin/assignments/index?classid=" + Assgdto.getClassid();
    }

    @GetMapping("/edit")
    public String editAssignment(@RequestParam int id, Model model) {
        tblAssignmentDto assignmentDto = aService.getAssignmentById(id);
        if (assignmentDto == null) {
            return "redirect:/admin/assignments/index?classid=" + assignmentDto.getClassid();

        }
        model.addAttribute("assignment", assignmentDto);
        model.addAttribute("classid", assignmentDto.getClassid());
        return "/admin/assignments/edit";
    }

    @PostMapping("/edit")
    public String updateAssignment(
            @Valid @ModelAttribute("assignment") tblAssignmentDto dto,
            BindingResult result,
            @RequestParam("file") MultipartFile file) throws Exception {

        if (result.hasErrors()) {
            return "admin/assignments/edit";
        }

        
        dto.setTitle(dto.getTitle());
        dto.setDescription(dto.getDescription());
        dto.setModifieddate(new Date());
        dto.setActive(dto.getActive());

        // Nếu có file mới
        if (file != null && !file.isEmpty()) {

            String uploadDir = System.getProperty("user.dir") + "/uploads/assignments";
            File dir = new File(uploadDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            File saveFile = new File(dir, fileName);
            file.transferTo(saveFile);

            dto.setFileurl("/files/assignments/" + fileName);
        }

        aService.updateAssignment(dto);

        return "redirect:/admin/assignments/index?classid=" + dto.getClassid();
    }
    @GetMapping("/submissions")
    public String ListSubmissions(@RequestParam("id") String assignmentid, Model model) {
        Integer assignmentId = Integer.parseInt(assignmentid);
        List<tblSubmissionDto> submissions = submissionsService.getAllSubmissionsWithAssignmentID(assignmentId);
        tblAssignmentDto assignmentDto = aService.getAssignmentById(assignmentId);
        if (assignmentDto != null) {
            model.addAttribute("classid", assignmentDto.getClassid());

        }
        model.addAttribute("submissions", submissions);
        return "admin/assignments/submissions";
    }

    @GetMapping("/feedback")
    public String feedbackSubmission(@RequestParam("id") String submissionid, Model model) {
        if(submissionid == null || submissionid.isEmpty()) {
            return "admin/notfound/index";
        }
        Integer submissionId = Integer.parseInt(submissionid);
        tblSubmissionDto submission = submissionsService.getSubmissionById(submissionId);
        model.addAttribute("submission", submission);
        model.addAttribute("submissionId", submissionId);
        model.addAttribute("assignmentid", submission.getAssignmentid());
        System.out.println("Mã bài tập đã giao: " + submission.getAssignmentid());
        return "admin/assignments/feedback";
    }
    @PostMapping("/feedback")
    public String saveFeedbackSubmission(
            @Valid @ModelAttribute("submission") tblSubmissionDto dto,
            BindingResult result
            ) {
        if (result.hasErrors()) {
            return "admin/assignments/feedback";
        }

        submissionsService.updateSubmission(dto);

        return "redirect:/admin/assignments/submissions?id=" + dto.getAssignmentid();
    }

}
