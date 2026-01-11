package com.example.eduSystems.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.eduSystems.models.tblClassMembers;

//@Repository
public interface ClassMembersRepository extends JpaRepository<tblClassMembers, Integer> {

    @Query("SELECT m From tblClassMembers m  WHERE m.clas.classid = :classId")
    List<tblClassMembers> findAllByClassId(@Param("classId") Integer classId);
    /**
     * Đếm số lớp học của user
     */
    @Query("SELECT COUNT(cm) FROM tblClassMembers cm WHERE cm.user.userid = :userid")
    long countClassesByUserId(@Param("userid") int userid);
    
    /**
     * Tìm tất cả class members của một user
     */
    @Query("SELECT cm FROM tblClassMembers cm WHERE cm.user.userid = :userid")
    List<tblClassMembers> findByUserId(@Param("userid") int userid);
    
    /**
     * Tìm theo user và class
     */
    @Query("SELECT cm FROM tblClassMembers cm WHERE cm.user.userid = :userid AND cm.clas.classid = :classid")
    tblClassMembers findByUserIdAndClassId(@Param("userid") int userid, @Param("classid") int classid);
    
    /**
     * Kiểm tra user đã tham gia class chưa
     */
    @Query("SELECT COUNT(cm) > 0 FROM tblClassMembers cm WHERE cm.user.userid = :userid AND cm.clas.classid = :classid")
    boolean existsByUserIdAndClassId(@Param("userid") int userid, @Param("classid") int classid);
}

 