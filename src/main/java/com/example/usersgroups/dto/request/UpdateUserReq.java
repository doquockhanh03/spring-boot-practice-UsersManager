package com.example.usersgroups.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UpdateUserReq {
    private String name;

    private String gender;

    private Long yearOfBirth;

    private String address;
}
