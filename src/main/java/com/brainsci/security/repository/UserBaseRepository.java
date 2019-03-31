package com.brainsci.security.repository;

import com.brainsci.security.entity.UserBaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserBaseRepository extends JpaRepository<UserBaseEntity, String>{
}
