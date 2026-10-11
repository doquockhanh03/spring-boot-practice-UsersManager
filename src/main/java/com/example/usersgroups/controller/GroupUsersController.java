package com.example.usersgroups.controller;

import com.example.usersgroups.dto.request.CreateGroupUserReq;
import com.example.usersgroups.dto.request.CreateUserReq;
import com.example.usersgroups.dto.request.UpdateGroupUserReq;
import com.example.usersgroups.dto.request.UpdateUserReq;
import com.example.usersgroups.entity.GroupUsers;
import com.example.usersgroups.entity.Users;
import com.example.usersgroups.service.GroupUsersService;
import com.example.usersgroups.service.Impl.GroupUsersServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/group-users")
@RequiredArgsConstructor
public class GroupUsersController {

    private final GroupUsersService groupUsersService;

    @GetMapping("/{id}")
    public ResponseEntity<GroupUsers> getUserById(@PathVariable Long id){
        return ResponseEntity.ok(groupUsersService.getGroupUser(id));
    }

    @PostMapping
    public ResponseEntity<GroupUsers> createUser(@RequestBody CreateGroupUserReq request){
        return ResponseEntity.ok(groupUsersService.createGroupUser(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GroupUsers> updateUser(@PathVariable Long id, @RequestBody UpdateGroupUserReq request){
        return ResponseEntity.ok(groupUsersService.updateGroupUser(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        groupUsersService.deleteGroupUser(id);
        return ResponseEntity.ok("Xoa thanh cong group users!");
    }
}
