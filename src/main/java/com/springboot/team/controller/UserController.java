package com.springboot.team.controller;

import com.springboot.team.model.User;
import com.springboot.team.request.RequestUser;
import com.springboot.team.response.ResponseUser;
import com.springboot.team.service.UserService;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/find/{seq}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<ResponseUser> findUser(@PathVariable("seq") Long seq) {
        HttpStatus httpStatus = HttpStatus.ACCEPTED;
        ResponseUser responseUser = new ResponseUser();

        try {
            if (Objects.isNull(seq)) {
                responseUser = responseUser.setResponseUser(false, "An error occured while searching user.", null);
                httpStatus = HttpStatus.CONFLICT;
            } else {
                User user = userService.findUser(seq);
                if (Objects.isNull(user)) {
                    responseUser = responseUser.setResponseUser(true, "Success searching user", null);
                } else {
                    responseUser = responseUser.setResponseUser(true, "Success searching user", user);
                }
                httpStatus = HttpStatus.OK;
            }

        } catch (Exception e) {
            ExceptionUtils.getStackTrace(e);
        }

        return new ResponseEntity<>(responseUser, httpStatus);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<ResponseUser> addUser(@RequestBody RequestUser requestUser) {
        System.out.println(requestUser.toString());
        HttpStatus httpStatus = HttpStatus.ACCEPTED;
        ResponseUser responseUser = new ResponseUser();

        try {
            if (Objects.isNull(requestUser)) {
                responseUser = responseUser.setResponseUser(false, "An error occurred while add user.", null);
                httpStatus = HttpStatus.CONFLICT;
            } else {
                User user = userService.addUser(requestUser);
                responseUser = responseUser.setResponseUser(true, "Success add user.", user);
                httpStatus = HttpStatus.OK;
            }

        } catch (Exception e) {
            ExceptionUtils.getStackTrace(e);
            responseUser = responseUser.setResponseUser(false, "An error occurred while add user.", null);
            httpStatus = HttpStatus.CONFLICT;
        }

        return new ResponseEntity<>(responseUser, httpStatus);
    }
}
