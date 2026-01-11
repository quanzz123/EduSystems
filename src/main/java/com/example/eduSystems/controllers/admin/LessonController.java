package com.example.eduSystems.controllers.admin;

import java.io.File;
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

import com.example.eduSystems.dto.tblAssignmentDto;
import com.example.eduSystems.dto.tblLessonContentDto;
import com.example.eduSystems.dto.tblLessonDto;
import com.example.eduSystems.models.tblClasses;
import com.example.eduSystems.services.ClassService;
import com.example.eduSystems.services.LessonContentService;
import com.example.eduSystems.services.LessonService;

import groovy.lang.Binding;
import jakarta.persistence.criteria.CriteriaBuilder.In;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;




@Controller
@RequestMapping("/admin/lessons")
public class LessonController {
    
    @Autowired
    ClassService classservice;

    @Autowired
    LessonService lessonService;

    @Autowired
    LessonContentService lessonContentService;

    @GetMapping("")
    public String index(Model model) {
        List<tblClasses> classes = classservice.FinClassByTeacher();
        model.addAttribute("classes", classes);
        for (tblClasses cls : classes) {
            System.out.println("Class Name: " + cls.getClassname());
        }
        return "admin/lessons/index";
    }

    @GetMapping("/list")
    public String getLessonsByClassId(@RequestParam("classid") String classid, Model model) {
        Integer cid = Integer.parseInt(classid);
        
        List<tblLessonDto> lessons = lessonService.getLessonsByClassId(cid);
        model.addAttribute("lessons", lessons);
        model.addAttribute("classid", classid);
        return "admin/lessons/list";
    }
    @GetMapping("/create")
    public String create(@RequestParam("classid") String classid, Model model) {
        tblLessonDto lessonDto = new tblLessonDto();
        model.addAttribute("lessonDto", lessonDto);
        model.addAttribute("classid", classid);
        return "admin/lessons/create";
    }
    @PostMapping("/create")
    public String SaveLesson(@Valid @ModelAttribute("lessonDto") tblLessonDto lessonDto, Model model, BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("classid", lessonDto.getClassid());
            return "admin/lessons/create";
        }
        
        lessonService.SaveLesson(lessonDto);
        return "redirect:/admin/lessons/list?classid=" + lessonDto.getClassid();
    }
    @GetMapping("/edit")
    public String update(@RequestParam("id") String lessonid, Model model) {
        Integer lid = Integer.parseInt(lessonid);
        tblLessonDto lessonDto = lessonService.getLessonById(lid);
        model.addAttribute("lessonDto", lessonDto);
        model.addAttribute("classid", lessonDto.getClassid());
        return "admin/lessons/edit";
    }

    @PostMapping("/edit")
    public String UpdateLesson(@Valid @ModelAttribute("lessonDto") tblLessonDto lessonDto, Model model, BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("classid", lessonDto.getClassid());
            return "admin/lessons/edit";
        }

        lessonService.UpdateLesson(lessonDto);
        return "redirect:/admin/lessons/list?classid=" + lessonDto.getClassid();
    }
    @GetMapping("/detail")
    public String details(@RequestParam("id") String lessonid, Model model) {
        Integer lid = Integer.parseInt(lessonid);
        List<tblLessonContentDto> lessonDto = lessonContentService.getLessonContentsByLessonId(lid);
        model.addAttribute("lessonDto", lessonDto);
        model.addAttribute("lessonid", lessonid);
        System.out.println("tiêu đề:"+lessonDto.get(0).getTitle());
        
        return "admin/lessons/detail";
    }
    @GetMapping("/createcontent")
    public String CreateContent(@RequestParam("id") String lessonid, Model model) {
        // System.out.println("Lesson ID in CreateContent GET:"+lessonid);
        tblLessonContentDto lessonContentDto = new tblLessonContentDto();
        Integer lid = Integer.parseInt(lessonid);
        lessonContentDto.setLessonid(lid);
        model.addAttribute("lessonContentDto", lessonContentDto);
        model.addAttribute("lessonid", lessonid);
        return "admin/lessons/createcontent";
    }
    
    @PostMapping("/createcontent")
    public String SaveContent(@Valid @ModelAttribute("lessonContentDto") tblLessonContentDto lessonContentDto,
            BindingResult result, @RequestParam("file") MultipartFile file) throws Exception {
        if (result.hasErrors()) {
            return "admin/lessons/createcontent";
        }

        if (file != null && !file.isEmpty()) {

            String uploadDir = System.getProperty("user.dir") + "/uploads/lessons";

            File dir = new File(uploadDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            File saveFile = new File(uploadDir, fileName);

            file.transferTo(saveFile);
            lessonContentDto.setContenturl("/files/lessons/" + fileName);
            System.out.println("UPLOAD DIR = " + uploadDir);

        }
        lessonContentDto.setCreatedate(new Date());


        System.out.println("Lesson ID:"+lessonContentDto.getLessonid()); //test thử xem đã có lessonid  chua
        
        lessonContentService.save(lessonContentDto);
        
        return "redirect:/admin/lessons/detail?id=" + lessonContentDto.getLessonid();
    }
    @GetMapping("/deletecontent")
    public String deleteContent(@RequestParam("id") String id) {
        Integer lid = Integer.parseInt(id);
        tblLessonContentDto lessonContentDto = lessonContentService.getLessonContentById(lid);
        if (lid == 0) {
            return "redirect:/admin/lessons";
        }
        lessonContentService.delete(lid);
        return "redirect:/admin/lessons/detail?id=" + lessonContentDto.getLessonid();
    }
    @GetMapping("/editcontent")
    public String updatelessoncontent(@RequestParam("id") String contentid, Model model) {
        Integer cid = Integer.parseInt(contentid);
        tblLessonContentDto lessonContentDto = lessonContentService.getLessonContentById(cid);
        model.addAttribute("lessonContentDto", lessonContentDto);
        model.addAttribute("contentid", contentid);
        return "admin/lessons/editcontent";
    }

    @PostMapping("/editcontent")
    public String updateLessonContent(
            @Valid @ModelAttribute("lessonContentDto") tblLessonContentDto lessonContentDto,
            BindingResult result,
            @RequestParam("file") MultipartFile file) throws Exception {

        if (result.hasErrors()) {
            return "admin/lessons/editcontent";
        }


        // Nếu có file mới
        if (file != null && !file.isEmpty()) {

            String uploadDir = System.getProperty("user.dir") + "/uploads/lessons";
            File dir = new File(uploadDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            File saveFile = new File(dir, fileName);
            file.transferTo(saveFile);

            lessonContentDto.setContenturl("/files/lessons/" + fileName);
        }

        lessonContentService.updateLessonContent(lessonContentDto);

        return "redirect:/admin/lessons/detail?id=" + lessonContentDto.getLessonid();
    }
}
