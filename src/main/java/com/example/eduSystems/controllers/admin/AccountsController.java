package com.example.eduSystems.controllers.admin;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.example.eduSystems.models.tblRoles;
import com.example.eduSystems.models.tblUsers;
import com.example.eduSystems.dto.tblUsersDto;
import com.example.eduSystems.Repository.RolesRepository;
import com.example.eduSystems.Repository.UsersRepository;
import com.example.eduSystems.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin/accounts")
public class AccountsController {

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    UserService userservice;

    @Autowired
    RolesRepository rolesRepository;

    @GetMapping("")
    public String index(Model model) {
        List<tblUsersDto> users = userservice.getAll();
        model.addAttribute("users", users);
        System.out.println(users);
        return "admin/accounts/index";
    }

    @GetMapping("/create")
    public String showCreatePage(Model model) {
        model.addAttribute("tbluserDto", new tblUsersDto());
        model.addAttribute("roles", rolesRepository.findAll());
        return "/admin/accounts/create";
    }
    @PostMapping("/create")
    public String CreateAccount(@Valid @ModelAttribute("tbluserDto") tblUsersDto tbluserDto, Model model
                                , BindingResult result) throws Exception {
        // debug
        /*System.out.println(tbluserDto.getUsername());
        System.out.println(tbluserDto.getRoleid());
        System.out.println(tbluserDto.getFullname());
        System.out.println(tbluserDto.getEmail());
        System.out.println(tbluserDto.getPhone());
        System.out.println(tbluserDto.getAddress());
        System.out.println(tbluserDto.getPasswordhash());*/
        if(result.hasErrors()) {
            model.addAttribute("roles", rolesRepository.findAll());
            return "/admin/accounts/create";
        }
        userservice.create(tbluserDto);

        return "redirect:/admin/accounts";
    }
    @GetMapping("/edit")
    public  String editpage(Model model, String id) {
        tblUsersDto usersDto = userservice.getUserById(Integer.parseInt(id));
        model.addAttribute("usersDto", usersDto);
        model.addAttribute("roles", rolesRepository.findAll());
        return "/admin/accounts/edit";
    }

    @PostMapping("/edit")
    public String updateAcount(Model model, @RequestParam int id, tblUsersDto tblUsersDto,
                                BindingResult result) throws IOException {
        if(result.hasErrors()) {
            model.addAttribute("tblUsersDto", tblUsersDto);
            model.addAttribute("roles", rolesRepository.findAll());
            return "/admin/accounts/edit";
        }
        userservice.update(tblUsersDto);

        return "redirect:/admin/accounts";
    }
    @GetMapping("/delete")
    public String deleteAcount(@RequestParam int id) {

        if(id == 0) {
            return "redirect:/admin/accounts";
        }
        userservice.delete(id);
        return "redirect:/admin/accounts";
    }

}
