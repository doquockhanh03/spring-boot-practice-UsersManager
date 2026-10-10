package com.example.usersgroups.service;

import com.example.usersgroups.dto.request.AssignGroupUsersReq;
import com.example.usersgroups.entity.Users;

public interface UserGroupMappingService {
    Users assignGroupUsers(AssignGroupUsersReq request);
}
