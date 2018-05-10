package com.springboot.team.repository;

import com.springboot.team.model.User;
import com.springboot.team.request.RequestUser;

public interface UserRepository {
    User findByUser(Long seq);
    User addUser(RequestUser requestUser);
}
