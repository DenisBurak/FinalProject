package com.zemelya.converter;

import com.zemelya.controller.request.UserCreateRequest;
import com.zemelya.domain.SystemRoles;
import com.zemelya.domain.hibernate.HibernateRole;
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
        user.setModificationDate(new Timestamp(new Date().getTime()));

//        HibernateRole hibernateRole = new HibernateRole();
//        hibernateRole.setRoleName(SystemRoles.ROLE_USER);
//        hibernateRole.setUser(user);
//        user.setRole(hibernateRole);

        return doConvert(user, request);
    }
}
