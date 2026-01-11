package com.example.eduSystems.controllers.admin;

import com.example.eduSystems.Repository.UsersRepository;
import com.example.eduSystems.dto.tblClassesDto;
import com.example.eduSystems.dto.tblUsersDto;
import com.example.eduSystems.models.tblUsers;
import com.example.eduSystems.services.ClassService;
import com.example.eduSystems.services.UserService;
import com.example.eduSystems.utilities.Functions;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/class")
public class ClassController {

    @Autowired
    ClassService classservice;
    @Autowired
    UsersRepository userRepo;

    @GetMapping("")
    public String index(Model model) {
        if (!Functions.isLogin()) {
            return "redirect:/admin/auth/login";
        }
        if (!Functions.checkRole("ADMIN")) {
            return "/admin/notfound/deny";
        }
        List<tblClassesDto> classesDtos = classservice.getAllClasses();
        model.addAttribute("classesDtos", classesDtos);
        return "admin/class/index";
    }

    @GetMapping("/create")
    public String showCreatePage(Model model) {
        if (!Functions.isLogin()) {
            return "redirect:/admin/auth/login";
        }
        if (!Functions.checkRole("ADMIN")) {
            return "/admin/notfound/deny";
        }
        model.addAttribute("classDto", new tblClassesDto());
        List<tblUsers> teachers = userRepo.findTeacher();
        model.addAttribute("teacher", teachers);

        return "/admin/class/create";
    }

    @PostMapping("/create")
    public String CreateClass(@Valid @ModelAttribute("classDto") tblClassesDto Dto, Model model, BindingResult result)
            throws Exception {
        if (!Functions.isLogin()) {
            return "redirect:/admin/auth/login";
        }
        if (!Functions.checkRole("ADMIN")) {
            return "/admin/notfound/deny";
        }
        if (result.hasErrors()) {
            model.addAttribute("teacher", userRepo.findTeacher());
            return "/admin/class/create";
        }
        classservice.create(Dto);

        return "redirect:/admin/class";
    }

    @GetMapping("/edit")
    public String EditPage(Model model, String id) {
        int classId;
        classId = Integer.parseInt(id);
        tblClassesDto dto = classservice.getClassById(classId);
        model.addAttribute("classDto", dto);

        List<tblUsers> teachers = userRepo.findTeacher();
        model.addAttribute("teacher", teachers);
        System.out.println("ngay tao lop : " + dto.getStartdate());
        System.out.println("id lop : " + dto.getClassid());
        System.out.println("id giao viên : " + dto.getTeacherid());
        teachers.forEach(t -> System.out.println("Teacher loaded: ID=" + t.getUserid() + ", Name=" + t.getFullname()));

        return "/admin/class/edit";
    }

    @PostMapping("/edit")
    public String UpdateClass(Model model, @RequestParam int id, tblClassesDto clsDto, BindingResult result)
            throws Exception {
        if (result.hasErrors()) {
            model.addAttribute("teacher", userRepo.findTeacher());
            return "/admin/class/edit";
        }
        classservice.update(clsDto);

        return "redirect:/admin/class";
    }

    @GetMapping("/delete")
    public String deleteClass(@RequestParam int id) {

        if (id == 0) {
            return "redirect:/admin/class";
        }
        classservice.delete(id);
        return "redirect:/admin/class";
    }
}
