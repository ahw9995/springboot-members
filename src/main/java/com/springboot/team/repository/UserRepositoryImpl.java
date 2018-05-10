package com.springboot.team.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.springboot.team.model.QUser;
import com.springboot.team.model.User;
import com.springboot.team.request.RequestUser;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;

@Repository
public class UserRepositoryImpl extends QuerydslRepositorySupport implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public UserRepositoryImpl() {
        super(User.class);
    }

    @Override
    public User findByUser(Long seq) {
        JPAQueryFactory f = new JPAQueryFactory(this.entityManager);
        QUser qUser = QUser.user;
        return f.selectFrom(qUser).where(qUser.seq.eq(seq)).fetchOne();
    }

    @Override
    public User addUser(RequestUser requestUser) {
        // spring security password encoder
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        User user = new User();
        user.setUserId(requestUser.getUserId());
        user.setPassword(passwordEncoder.encode(requestUser.getPassword()));
        user.setName(requestUser.getName());
        user.setEmail(requestUser.getEmail());
        user.setCreatedDate(new Date());
        user.setModifiedDate(new Date());
        this.entityManager.persist(user);
        return user;
    }
}
