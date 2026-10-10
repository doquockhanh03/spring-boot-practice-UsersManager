package com.example.usersgroups.repository;

import com.example.usersgroups.entity.UserGroupMapping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserGroupMappingRepo extends JpaRepository<UserGroupMapping, Long> {
    List<UserGroupMapping> findByUsersId(Long id);
}
