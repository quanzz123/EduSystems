package com.example.eduSystems.Repository;

import com.example.eduSystems.models.tblClassMembers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClassMembersRepository extends JpaRepository<tblClassMembers, Integer> {

    @Query("SELECT m From tblClassMembers m  WHERE m.clas.classid = :classId")
    List<tblClassMembers> findAllByClassId(@Param("classId") Integer classId);
}
 