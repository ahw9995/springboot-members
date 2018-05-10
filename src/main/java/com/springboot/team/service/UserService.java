package com.springboot.team.service;

import com.springboot.team.model.User;
import com.springboot.team.repository.UserRepository;
import com.springboot.team.request.RequestUser;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;

@Transactional
@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * User 정보를 조회
     * @param seq this is id of table
     * @return User
     */
    public User findUser(Long seq) {
        try {
            if (Objects.isNull(seq)) {
                throw new Exception("User key is null or empty.");
            }
            return userRepository.findByUser(seq);

        } catch (Exception e) {
            ExceptionUtils.getStackTrace(e);
        }
        return null;
    }

    /**
     * User 정보 추가
     * @param requestUser this is Object of User information
     * @return User
     */
    public User addUser(RequestUser requestUser) {
        try {
            if (Objects.isNull(requestUser)) {
                throw new Exception("Request info is null or empty.");
            }
            return userRepository.addUser(requestUser);

        } catch (Exception e) {
            ExceptionUtils.getStackTrace(e);
        }
        return null;
    }
}
