package com.example.eduSystems.controllers.admin;

import com.example.eduSystems.models.tblRoles;
import com.example.eduSystems.models.tblUsers;
import com.example.eduSystems.models.tblUsersDto;
import com.example.eduSystems.services.RolesRepository;
import com.example.eduSystems.services.UsersRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin/accounts")
public class AccountsController {

    @Autowired
    UsersRepository usersRepository;
    @Autowired
    RolesRepository rolesRepository;

    @GetMapping("")
    public String index(Model model) {
        List<tblUsers> users = usersRepository.findAllWithrole();
        model.addAttribute("users", users);
        System.out.println(users);
        return "/admin/accounts/index";
    }

    @GetMapping("/create")
    public String showCreatePage(Model model) {
        model.addAttribute("tbluserDto", new tblUsersDto());
        model.addAttribute("roles", rolesRepository.findAll());
        return "/admin/accounts/create";
    }
    @PostMapping("/create")
    public String CreateAccount(@Valid @ModelAttribute("tbluserDto") tblUsersDto tbluserDto, Model model
                                , BindingResult result) {
        Date date = new Date();
        if(result.hasErrors()) {

            return "/admin/accounts/create";
        }

        try
        {
            tblRoles role = rolesRepository.findById(tbluserDto.getRoleid()).get();
            tblUsers user = new tblUsers();
            user.setUsername(tbluserDto.getUsername());
            user.setEmail(tbluserDto.getEmail());
            user.setPasswordhash(tbluserDto.getPasswordhash());
            user.setAddress(tbluserDto.getAddress());
            user.setPhone(tbluserDto.getPhone());
            user.setFullname(tbluserDto.getFullname());
            user.setRole(role);
            user.setActive(true);
            user.setCreated(date);
            usersRepository.save(user);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return "/admin/accounts/create";
        }
        return "redirect:/admin/accounts";
    }
    @GetMapping("/edit")
    public  String editpage(Model model, String id) {
        try
        {
            tblUsers users = usersRepository.findById(Integer.parseInt(id)).get();
            model.addAttribute("users", users);
            tblUsersDto usersDto = new tblUsersDto();
            usersDto.setUsername(users.getUsername());
            usersDto.setEmail(users.getEmail());
            usersDto.setAddress(users.getAddress());
            usersDto.setPhone(users.getPhone());
            usersDto.setFullname(users.getFullname());
            usersDto.setPasswordhash(users.getPasswordhash());
            usersDto.setRoleid(users.getRole().getRoleid());

            model.addAttribute("usersDto", usersDto);
            model.addAttribute("roles", rolesRepository.findAll());

        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return "/admin/accounts/edit";
        }
        return "/admin/accounts/edit";
    }
    @PostMapping("/edit")
    public String updateAcount(Model model, @RequestParam int id, tblUsersDto tblUsersDto,
                                BindingResult result) {
        if(result.hasErrors()) {
            model.addAttribute("tblUsersDto", tblUsersDto);
            return "/admin/accounts/edit";
        }
        try
        {
            tblUsers user = usersRepository.findById(id).get();
            if(user == null) {
                return "redirect:/admin/accounts";
            }
            tblRoles role = rolesRepository.findById(tblUsersDto.getRoleid()).get();

            user.setUsername(tblUsersDto.getUsername());
            user.setEmail(tblUsersDto.getEmail());
            user.setPasswordhash(tblUsersDto.getPasswordhash());
            user.setAddress(tblUsersDto.getAddress());
            user.setPhone(tblUsersDto.getPhone());
            user.setFullname(tblUsersDto.getFullname());
            user.setRole(role);
            user.setActive(true);

            usersRepository.save(user);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return "/admin/accounts/edit";
        }
        return "redirect:/admin/accounts";
    }
    @GetMapping("/delete")
    public String deleteAcount(@RequestParam int id) {
        tblUsers user = usersRepository.findById(id).get();
        if(user == null) {
            return "redirect:/admin/accounts";
        }
        user.setActive(false);
        usersRepository.save(user);
        return "redirect:/admin/accounts";
    }

}
