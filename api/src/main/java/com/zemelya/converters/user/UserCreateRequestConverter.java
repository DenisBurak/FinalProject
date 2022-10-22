package com.zemelya.converters.user;

import com.zemelya.controller.request.user.UserCreateRequest;
import com.zemelya.domain.Credentials;
import com.zemelya.domain.hibernate.HibernateUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class UserCreateRequestConverter extends UserBaseConverter<UserCreateRequest, HibernateUser> {

    private final PasswordEncoder passwordEncoder;

    @Override
    public HibernateUser convert(UserCreateRequest request) {

        HibernateUser user = new HibernateUser();

        user.setCreationDate(new Timestamp(new Date().getTime()));

        Credentials credentials = new Credentials(
                request.getLogin(),
                passwordEncoder.encode(request.getPassword())
        );

        user.setCredentials(credentials);

        return doConvert(user, request);
    }
}
