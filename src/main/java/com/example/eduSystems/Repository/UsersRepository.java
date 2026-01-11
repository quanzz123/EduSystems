package com.example.eduSystems.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.eduSystems.models.tblUsers;
public interface UsersRepository extends JpaRepository<tblUsers, Integer>  {
    @Query("SELECT u FROM tblUsers u JOIN FETCH u.role where u.active =true ORDER BY u.userid asc ")
    List<tblUsers> findAllWithrole();

    List<tblUsers> findAllByActiveTrue();

    @Query("SELECT t FROM tblUsers t WHERE t.role.roleid = 5 and t.active=true ")
    List<tblUsers> findTeacher();

    @Query("SELECT t FROM tblUsers t WHERE t.role.roleid = 4 and t.active=true ")
    List<tblUsers> FindAllUsers();
    // Tìm user theo username
    Optional<tblUsers> findByUsername(String username);
    
    // Tìm user theo email
    Optional<tblUsers> findByEmail(String email);
    
    // Kiểm tra username có tồn tại không
    boolean existsByUsername(String username);
    
    // Kiểm tra email có tồn tại không
    boolean existsByEmail(String email);

    tblUsers  findUserByUsernameAndPasswordhash(String username, String passwordhash);
}
    