package com.example.usersgroups.repository;

import com.example.usersgroups.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepo extends JpaRepository<Users, Long> {
}
