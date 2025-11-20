package com.example.eduSystems.services;

import com.example.eduSystems.Repository.RolesRepository;
import com.example.eduSystems.Repository.UsersRepository;
import com.example.eduSystems.dto.tblUsersDto;
import com.example.eduSystems.models.tblRoles;
import com.example.eduSystems.models.tblUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UsersRepository userRepo;

    @Autowired
    private RolesRepository roleRepo;

    public List<tblUsersDto> getAll() {
        return userRepo.findAllByActiveTrue().stream().filter(tblUsers::isActive).map(this ::toDto).collect(Collectors.toList());
    }

    public tblUsersDto getUserById(int id) {
        tblUsers user = userRepo.findById(id).orElse(null);
        return toDto(user);
    }

    public void create(tblUsersDto tblUsersDto) throws IOException {
        tblUsers user = new tblUsers();
        user.setUsername(tblUsersDto.getUsername());
        user.setPasswordhash(tblUsersDto.getPasswordhash());
        tblRoles role = roleRepo.findById(tblUsersDto.getRoleid())
                .orElseThrow(() -> new RuntimeException("Role không tồn tại!"));
        user.setRole(role);
        user.setFullname(tblUsersDto.getFullname());
        user.setEmail(tblUsersDto.getEmail());
        user.setAddress(tblUsersDto.getAddress());
        user.setPhone(tblUsersDto.getPhone());
        user.setActive(true);

        mapDtoToEntity(tblUsersDto, user);
        userRepo.save(user);
    }

    public void update(tblUsersDto tblUsersDto) throws IOException {
        tblUsers user = userRepo.findById(tblUsersDto.getUserid())
                .orElse(null);

        mapDtoToEntity(tblUsersDto, user);

        userRepo.save(user);
    }

    public void delete(Integer id) {
        tblUsers user = userRepo.findById(id).orElse(null);
        user.setActive(false);
        userRepo.save(user);
    }

    private tblUsersDto toDto(tblUsers user) {
        tblUsersDto tblUserDto = new tblUsersDto();
        tblUserDto.setUserid(user.getUserid());
        tblUserDto.setUsername(user.getUsername());
        tblUserDto.setEmail(user.getEmail());
        tblUserDto.setFullname(user.getFullname());
        tblUserDto.setAddress(user.getAddress());
        tblUserDto.setPhone(user.getPhone());
        tblUserDto.setActive(user.isActive());
        tblUserDto.setPasswordhash(user.getPasswordhash());
        tblUserDto.setCreated(user.getCreated());

        if(user.getRole() != null) {
            tblUserDto.setRoleid(user.getRole().getRoleid());
            tblUserDto.setRolename(user.getRole().getRolename());
        }

        return tblUserDto;
    }

    private void mapDtoToEntity(tblUsersDto dto, tblUsers user) throws IOException {
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setFullname(dto.getFullname());
        user.setAddress(dto.getAddress());
        user.setPhone(dto.getPhone());
        user.setPasswordhash(dto.getPasswordhash());

        tblRoles role = roleRepo.findById(dto.getRoleid()).get();
        user.setRole(role);

        if(user.getUserid() ==0) {
            user.setCreated(new Date());
        }
    }

}
