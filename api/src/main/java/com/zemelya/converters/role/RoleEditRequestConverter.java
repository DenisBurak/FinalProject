package com.zemelya.converters.role;

import com.zemelya.controller.request.role.RoleChangeRequest;
import com.zemelya.domain.SystemRoles;
import com.zemelya.domain.hibernate.HibernateRole;
import com.zemelya.service.role.RoleService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;

@Component
public class RoleEditRequestConverter implements Converter<RoleChangeRequest, HibernateRole> {
  private final RoleService service;

  public RoleEditRequestConverter(RoleService service) {
    this.service = service;
  }

  @Override
  public HibernateRole convert(RoleChangeRequest request) {

    HibernateRole role = service.findById(request.getId());

    role.setRoleName(SystemRoles.valueOf(request.getRoleName()));

    role.setModificationDate(new Timestamp(new Date().getTime()));

    return role;
  }
}
