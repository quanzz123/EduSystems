package com.example.eduSystems.Repository;

import com.example.eduSystems.models.tblUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsersRepository extends JpaRepository<tblUsers, Integer>  {
    @Query("SELECT u FROM tblUsers u JOIN FETCH u.role where u.active =true ORDER BY u.userid asc ")
    List<tblUsers> findAllWithrole();

    List<tblUsers> findAllByActiveTrue();

    @Query("SELECT t FROM tblUsers t WHERE t.role.roleid = 5 and t.active=true ")
    List<tblUsers> findTeacher();

    @Query("SELECT t FROM tblUsers t WHERE t.role.roleid = 4 and t.active=true ")
    List<tblUsers> FindAllUsers();
}
