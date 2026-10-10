package com.example.usersgroups.repository;

import com.example.usersgroups.entity.GroupUsers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupUsersRepo extends JpaRepository<GroupUsers, Long> {
}
