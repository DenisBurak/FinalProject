package com.zemelya.converters.user;

import com.zemelya.controller.request.user.UserChangeRequest;
import com.zemelya.domain.hibernate.HibernateUser;
import com.zemelya.service.user.UserService;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;

@Component
public class UserEditRequestConverter extends UserBaseConverter<UserChangeRequest, HibernateUser> {

  private final UserService service;

  public UserEditRequestConverter(UserService service) {
    this.service = service;
  }

  @Override
  public HibernateUser convert(UserChangeRequest request) {

    HibernateUser hibernateUser = service.findById(request.getId());

    return doConvert(hibernateUser, request);

  }
}
