package com.springboot.team.response;

import com.springboot.team.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseUser {
    private boolean result = false;
    private String message;
    private User user;

    public ResponseUser setResponseUser(boolean result, String message, User user) {
        ResponseUser responseUser = new ResponseUser();
        responseUser.result = result;
        responseUser.message = message;
        responseUser.user = user;
        return responseUser;
    }
}
