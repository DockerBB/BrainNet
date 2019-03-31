package com.brainsci.security.repository;

import com.brainsci.security.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, String>, JpaSpecificationExecutor<UserEntity> {

    List<UserEntity> findByUsername(String username);

    @Query(value = "SELECT TRUE FROM user_role WHERE username = ?1 AND rid =1", nativeQuery = true)
    Integer userIsStudent(String username);

    @Modifying
    @Query(value = "INSERT INTO user_role(username, rid) value(?1, 1) ", nativeQuery = true)
    void insertUserRoleStudent(String username);

    @Modifying
    @Query(value = "INSERT INTO user_role(username, rid) value(?1, 2) ", nativeQuery = true)
    void insertUserRoleMonitor(String username);

    @Modifying
    @Query(value = "INSERT INTO user_role(username, rid) value(?1, 3) ", nativeQuery = true)
    void insertUserRoleInstructor(String username);

    @Query(value = "select username from users", nativeQuery = true)
    List<String> getAllUserId();

    @Query(value = "select password(?1)", nativeQuery = true)
    String getBcryt(String password);

    @Modifying
    @Transactional
    @Query(value = "delete from UserEntity s where s.username = ?1")
    int deleteAllByStuId(String stuId);
}
