package com.example.usersgroups.service.Impl;

import com.example.usersgroups.common.Status;
import com.example.usersgroups.dto.request.CreateGroupUserReq;
import com.example.usersgroups.dto.request.UpdateGroupUserReq;
import com.example.usersgroups.entity.GroupUsers;
import com.example.usersgroups.entity.Users;
import com.example.usersgroups.repository.GroupUsersRepo;
import com.example.usersgroups.service.GroupUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GroupUsersServiceImpl implements GroupUsersService {

    @Autowired
    private GroupUsersRepo groupUsersRepo;

    @Override
    public GroupUsers getGroupUser(Long id) {
        Optional<GroupUsers> groupUsers = groupUsersRepo.findById(id);
        if(groupUsers.isEmpty()){
            throw new RuntimeException("data not found!");
        }
        return groupUsers.get();
    }

    @Override
    public GroupUsers createGroupUser(CreateGroupUserReq request) {
        if(request.getName().isEmpty() || request.getDescription().isEmpty() ){
            throw new RuntimeException("Khong co du lieu truyen vao!");
        }

        GroupUsers groupUsers = new GroupUsers();

        groupUsers.setName(request.getName());
        groupUsers.setDescription(request.getDescription());
        groupUsers.setStatus(Status.ACTIVE);

        return groupUsersRepo.save(groupUsers);
    }

    @Override
    public GroupUsers updateGroupUser(Long id, UpdateGroupUserReq request) {
        Optional<GroupUsers> groupUsersExist = groupUsersRepo.findById(id);
        if(groupUsersExist.isEmpty()){
            throw new RuntimeException("Khong thay id can sua!");
        }

        GroupUsers groupUsers = groupUsersExist.get();

        if(request.getName() != null){
            groupUsers.setName(request.getName());
        }

        if(request.getDescription() != null){
            groupUsers.setDescription(request.getDescription());
        }

        return groupUsersRepo.save(groupUsers);
    }

    @Override
    public void deleteGroupUser(Long id) {
        Optional<GroupUsers> groupUsersExist = groupUsersRepo.findById(id);
        if(groupUsersExist.isEmpty()){
            throw new RuntimeException("Khong tim thay id can xoa");
        }
        GroupUsers groupUsers = groupUsersExist.get();

        groupUsers.setStatus(Status.INACTIVE);

        groupUsersRepo.save(groupUsers);
    }
}
