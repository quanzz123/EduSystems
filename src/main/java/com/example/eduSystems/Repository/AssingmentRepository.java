package com.example.eduSystems.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.eduSystems.models.tblAssignments;

public interface AssingmentRepository extends JpaRepository<tblAssignments, Integer> {
    
    @Query("SELECT a FROM tblAssignments a WHERE a.clas.classid = :classid AND a.active = true")
    List<tblAssignments> findByClas_classid(@Param("classid") Integer classid);
    
    // Tìm bài tập của user (thông qua class_members)
    // Sử dụng relationship path thay vì INNER JOIN thủ công
    @Query("SELECT a FROM tblAssignments a " +
           "WHERE a.clas.classid IN " +
           "(SELECT cm.clas.classid FROM tblClassMembers cm WHERE cm.user.userid = :userId) " +
           "ORDER BY a.deadline DESC")
    List<tblAssignments> findAssignmentsByUserId(@Param("userId") int userId);
    
    // Đếm số bài tập của user
    @Query("SELECT COUNT(a) FROM tblAssignments a " +
           "WHERE a.clas.classid IN " +
           "(SELECT cm.clas.classid FROM tblClassMembers cm WHERE cm.user.userid = :userId)")
    long countAssignmentsByUserId(@Param("userId") int userId);
    
    // Lấy bài tập gần nhất theo thời gian tạo
    @Query("SELECT a FROM tblAssignments a " +
           "WHERE a.clas.classid IN " +
           "(SELECT cm.clas.classid FROM tblClassMembers cm WHERE cm.user.userid = :userId) " +
           "ORDER BY a.createdate DESC")
    List<tblAssignments> findRecentAssignmentsByUserId(@Param("userId") int userId);
}