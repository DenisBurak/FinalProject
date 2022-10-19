package com.zemelya.converters;

import com.zemelya.controller.request.UserCreateRequest;
import com.zemelya.domain.Credentials;
import com.zemelya.domain.hibernate.HibernateUser;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;

@Component
public class UserCreateRequestConverter extends EntityConverter<UserCreateRequest, HibernateUser> {

    @Override
    public HibernateUser convert(UserCreateRequest request) {

        HibernateUser user = new HibernateUser();

        user.setCreationDate(new Timestamp(new Date().getTime()));

        Credentials credentials = new Credentials();
        credentials.setLogin(request.getLogin());
        credentials.setPassword(request.getPassword());

        user.setCredentials(credentials);

//        HibernateRole hibernateRole = new HibernateRole();
//        hibernateRole.setRoleName(SystemRoles.ROLE_USER);
//        hibernateRole.setUser(user);
//        user.setRole(hibernateRole);

        return doConvert(user, request);
    }
}
