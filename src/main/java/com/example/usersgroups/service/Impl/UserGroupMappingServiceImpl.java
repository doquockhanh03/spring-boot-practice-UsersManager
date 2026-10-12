package com.example.usersgroups.service.Impl;

import com.example.usersgroups.dto.request.AssignGroupUsersReq;
import com.example.usersgroups.entity.GroupUsers;
import com.example.usersgroups.entity.UserGroupMapping;
import com.example.usersgroups.entity.Users;
import com.example.usersgroups.repository.GroupUsersRepo;
import com.example.usersgroups.repository.UserGroupMappingRepo;
import com.example.usersgroups.repository.UsersRepo;
import com.example.usersgroups.service.UserGroupMappingService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserGroupMappingServiceImpl implements UserGroupMappingService {

    private final UserGroupMappingRepo userGroupMappingRepo;

    private final UsersRepo usersRepo;

    private final GroupUsersRepo groupUsersRepo;

    @Transactional
    @Override
    public Users assignGroupUsers(AssignGroupUsersReq request) {
        if(request.getUserId() == null){
            throw new RuntimeException("user id is null");
        }
        if(request.getGroupUserIds() == null || request.getGroupUserIds().isEmpty()){
            throw new RuntimeException("groupUser id are null or empty");
        }

        //check duplicate input data
        Set<Long> groupUserIds = new LinkedHashSet<>(request.getGroupUserIds());

        Optional<Users> usersOptional = usersRepo.findById(request.getUserId());
        if(usersOptional.isEmpty()){
            throw new RuntimeException("user id not exist");
        }

        Users users = usersOptional.get();

        List<GroupUsers> groupUsers = groupUsersRepo.findAllById(groupUserIds);
        if(groupUsers.isEmpty()){
            throw new RuntimeException("groupUser id not exist");
        }

        List<UserGroupMapping> userGroupMappings = new ArrayList<>();
        List<UserGroupMapping> userGroupMappingsExist = userGroupMappingRepo.findByUsersId(request.getUserId());

        for(int i = 0; i < groupUsers.size(); i++){
            UserGroupMapping userGroupMapping = new UserGroupMapping();

            boolean checkExist = false;

            for(int j = 0; j < userGroupMappingsExist.size(); j++){
                UserGroupMapping existing = userGroupMappingsExist.get(j);

                if(existing.getGroupUsers().getId().equals(groupUsers.get(i).getId())){
                    checkExist = true;
                    break;
                }
            }
            if(!checkExist){
                userGroupMapping.setUsers(usersOptional.get());
                userGroupMapping.setGroupUsers(groupUsers.get(i));

                userGroupMappings.add(userGroupMapping);
            }
        }
        userGroupMappingRepo.saveAll(userGroupMappings);
        users.setUserGroupMapping(userGroupMappings);
        return users;
    }
}
