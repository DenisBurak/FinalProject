package com.zemelya.service;

import com.zemelya.controller.request.UserCreateRequest;

import com.zemelya.domain.hibernate.HibernateUser;
import com.zemelya.repository.user.UserSpringDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserSpringDataRepository userSpringDataRepository;

    public final ConversionService conversionService;

    public final AdminService adminService;

    public HibernateUser createUser(UserCreateRequest userCreateRequest){

        HibernateUser hibernateUser = conversionService.convert(userCreateRequest, HibernateUser.class);

        hibernateUser = adminService.setRoles(hibernateUser);

        return hibernateUser;
    }
}
