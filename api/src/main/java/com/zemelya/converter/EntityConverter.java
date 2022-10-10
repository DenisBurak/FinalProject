package com.zemelya.converter;

import com.zemelya.controller.request.UserCreateRequest;
import com.zemelya.domain.Credentials;
import com.zemelya.domain.hibernate.HibernateUser;
import org.springframework.core.convert.converter.Converter;

import java.sql.Timestamp;
import java.util.Date;

public abstract class EntityConverter<S, T> implements Converter<S, T> {
    protected HibernateUser doConvert(HibernateUser user, UserCreateRequest request) {

        user.setUserName(request.getUserName());
        user.setSurname(request.getSurname());
        user.setBirth(request.getBirthDate());
        user.setEmail(request.getEmail());

        Credentials credentials = new Credentials();
        credentials.setLogin(request.getLogin());
        credentials.setPassword(request.getPassword());

        user.setCredentials(credentials);

        user.setModificationDate(new Timestamp(new Date().getTime()));



        return user;
    }
}
