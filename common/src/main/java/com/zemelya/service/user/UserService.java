package com.zemelya.service.user;

import com.zemelya.domain.hibernate.HibernateCar;
import com.zemelya.domain.hibernate.HibernateUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserService {

    HibernateUser create(HibernateUser hibernateUser);

    HibernateUser delete(Long userId);

    HibernateUser update(HibernateUser hibernateUser);

    Page<HibernateUser> findAll(Pageable pageable);

    List<HibernateUser> findAll();

    HibernateUser findById(Long id);

    Optional<HibernateUser> findByCredentialsLogin(String login);

}
