package com.zemelya.service.user;

import com.zemelya.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User findById(Integer id);

    Optional<User> findOne(Integer id);

    List<User> findAll();

    List<User> findAll(int limit, int offset);

    User create(User object);

    User update(User object);

    Integer delete(Integer id);
}
