package com.zemelya.service.user;

import com.zemelya.domain.hibernate.HibernateUser;

public interface UserService {

    HibernateUser create(HibernateUser hibernateUser);

    Long delete(Long userId);

    HibernateUser update(HibernateUser hibernateUser);
}
