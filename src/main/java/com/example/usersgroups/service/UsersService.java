package com.example.usersgroups.service;

import com.example.usersgroups.dto.request.CreateUserReq;
import com.example.usersgroups.dto.request.UpdateUserReq;
import com.example.usersgroups.entity.Users;
import org.apache.catalina.User;

import java.util.List;

public interface UsersService {
    Users getUser(Long id);

    Users createUser(CreateUserReq request);

    Users updateUser(Long id, UpdateUserReq request);

    void deleteUser(Long id);

    List<Users> filter(String name, String gender, Long minYearOfBirth, Long maxYearOfBirth);
}
