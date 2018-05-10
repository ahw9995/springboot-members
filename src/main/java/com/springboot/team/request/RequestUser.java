package com.springboot.team.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestUser {
    private String userId;
    private String name;
    private String password;
    private String email;
}
