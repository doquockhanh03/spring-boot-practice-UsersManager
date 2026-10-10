package com.example.usersgroups.service;

import com.example.usersgroups.dto.request.CreateGroupUserReq;
import com.example.usersgroups.dto.request.CreateUserReq;
import com.example.usersgroups.dto.request.UpdateGroupUserReq;
import com.example.usersgroups.dto.request.UpdateUserReq;
import com.example.usersgroups.entity.GroupUsers;
import com.example.usersgroups.entity.Users;

public interface GroupUsersService {
    GroupUsers getGroupUser(Long id);
    GroupUsers createGroupUser(CreateGroupUserReq request);
    GroupUsers updateGroupUser(Long id, UpdateGroupUserReq request);
    void deleteGroupUser(Long id);
}
