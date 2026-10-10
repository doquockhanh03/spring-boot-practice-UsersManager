package com.example.usersgroups.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AssignGroupUsersReq implements Serializable {
    private Long userId;
    private List<Long> groupUserIds;
}
