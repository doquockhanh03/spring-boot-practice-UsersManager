package com.example.usersgroups.controller;

import com.example.usersgroups.dto.request.CreateUserReq;
import com.example.usersgroups.dto.request.UpdateUserReq;
import com.example.usersgroups.entity.Users;
import com.example.usersgroups.service.Impl.UsersServiceImpl;
import org.apache.coyote.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UsersController {

    @Autowired
    private UsersServiceImpl usersService;

    @GetMapping("/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable Long id){
        return ResponseEntity.ok(usersService.getUser(id));
    }

    @PostMapping
    public ResponseEntity<Users> createUser(@RequestBody CreateUserReq request){
        return ResponseEntity.ok(usersService.createUser(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Users> updateUser(@PathVariable Long id, @RequestBody UpdateUserReq request){
        return ResponseEntity.ok(usersService.updateUser(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        usersService.deleteUser(id);
        return ResponseEntity.ok("Xoa thanh cong user!");
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Users>> filter(@RequestParam(name = "name", required = false) String name,
                                                         @RequestParam(name = "minYearOfBirth", required = false) Long minYearOfBirth,
                                                         @RequestParam(name = "maxYearOfBirth", required = false) Long maxYearOfBirth,
                                                         @RequestParam(name = "gender", required = false) String gender){

        List<Users> users = usersService.filter(name, gender, minYearOfBirth, maxYearOfBirth);
        return ResponseEntity.ok(users);
    }

}
