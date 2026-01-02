package com.example.eduSystems.Repository;

import com.example.eduSystems.models.tblClasses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClassRepository extends JpaRepository<tblClasses, Integer> {
    @Query("SELECT c from tblClasses c WHERE c.active = true ")
    List<tblClasses> findallActive();

    @Query("SELECT c FROM tblClasses c WHERE c.teacher.userid = 5 and c.active = true")
    List<tblClasses> FindAllWithTeacher();
}
