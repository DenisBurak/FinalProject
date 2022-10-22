package com.zemelya.converters.user;

import com.zemelya.controller.request.user.UserCreateRequest;
import com.zemelya.domain.hibernate.HibernateUser;
import org.springframework.core.convert.converter.Converter;

import java.sql.Timestamp;
import java.util.Date;

public abstract class UserBaseConverter<S, T> implements Converter<S, T> {
    protected HibernateUser doConvert(HibernateUser user, UserCreateRequest request) {

        user.setUserName(request.getUserName());
        user.setSurname(request.getSurname());
        user.setBirth(request.getBirthDate());
        user.setEmail(request.getEmail());

        /*System fields filling*/
        user.setModificationDate(new Timestamp(new Date().getTime()));
        user.setIsDeleted(false);

        return user;
    }
}
