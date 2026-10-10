package com.example.usersgroups.dto.request;

import com.example.usersgroups.common.Status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CreateUserReq {
    private String name;

    private String gender;

    private Long yearOfBirth;

    private String address;

    private Status status;
}
