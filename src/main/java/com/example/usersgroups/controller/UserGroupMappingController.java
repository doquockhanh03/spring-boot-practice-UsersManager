package com.example.usersgroups.controller;

import com.example.usersgroups.dto.request.AssignGroupUsersReq;
import com.example.usersgroups.entity.Users;
import com.example.usersgroups.repository.UserGroupMappingRepo;
import com.example.usersgroups.service.Impl.UserGroupMappingServiceImpl;
import com.example.usersgroups.service.UserGroupMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/user-group-mapping")
public class UserGroupMappingController {
    @Autowired
    UserGroupMappingService userGroupMappingService;

    @PostMapping
    public ResponseEntity<Users> assignProductDetail(@RequestBody AssignGroupUsersReq request){
        return ResponseEntity.ok(userGroupMappingService.assignGroupUsers(request));
    }
}
