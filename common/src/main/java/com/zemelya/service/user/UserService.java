package com.zemelya.service.user;

import com.zemelya.domain.hibernate.HibernateUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    HibernateUser create(HibernateUser hibernateUser);

    HibernateUser delete(Long userId);

    HibernateUser update(HibernateUser hibernateUser);

    Page<HibernateUser> findAll(Pageable pageable);

    List<HibernateUser> findAll();

    HibernateUser findById(Long id);

}
