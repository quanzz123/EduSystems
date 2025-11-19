package com.example.eduSystems.services;

import com.example.eduSystems.models.tblUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsersRepository extends JpaRepository<tblUsers, Integer>  {
    @Query("SELECT u FROM tblUsers u JOIN FETCH u.role where u.active =true ORDER BY u.userid asc ")
    List<tblUsers> findAllWithrole();

}
