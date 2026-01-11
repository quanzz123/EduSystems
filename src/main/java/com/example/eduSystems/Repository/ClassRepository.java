package com.example.eduSystems.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.eduSystems.models.tblClasses;

@Repository
public interface ClassRepository extends JpaRepository<tblClasses, Integer> {
    
    @Query("SELECT c FROM tblClasses c WHERE c.active = true")
    List<tblClasses> findallActive();
    @Query("SELECT c FROM tblClasses c WHERE c.teacher.userid = 5 AND c.active = true")
    List<tblClasses> FindAllWithTeacher();
    
    /**
     * Tìm lớp học theo userid (giáo viên)
     * Sử dụng naming convention của Spring Data JPA
     */
    @Query("SELECT c FROM tblClasses c WHERE c.teacher.userid = :userid AND c.active = true")
    List<tblClasses> findByUserid(@Param("userid") int userid);
    
    /**
     * Tìm lớp học theo userid (giáo viên) - Alias method
     * Method này giống findByUserid nhưng tên rõ ràng hơn
     */
    @Query("SELECT c FROM tblClasses c WHERE c.teacher.userid = :userid")
    List<tblClasses> findByUser_userid(@Param("userid") int userid);
    
    /**
     * Tìm lớp học đang active
     */
    List<tblClasses> findByActiveTrue();
    
    /**
     * Tìm lớp học của sinh viên (qua class_members)
     */
    @Query("SELECT c FROM tblClasses c " +
           "INNER JOIN tblClassMembers cm ON c.classid = cm.clas.classid " +
           "WHERE cm.user.userid = :userId")
    List<tblClasses> findClassesByStudentId(@Param("userId") int userId);
}