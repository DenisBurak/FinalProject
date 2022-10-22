package com.zemelya.converters.role;

import com.zemelya.controller.request.role.RoleChangeRequest;
import com.zemelya.domain.SystemRoles;
import com.zemelya.domain.hibernate.HibernateRole;
import com.zemelya.repository.role.RoleSpringDataRepository;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.sql.Timestamp;
import java.util.Date;

@Component
public class RoleEditRequestConverter implements Converter<RoleChangeRequest, HibernateRole> {
  private final RoleSpringDataRepository roleRepository;

  public RoleEditRequestConverter(RoleSpringDataRepository roleRepository) {
    this.roleRepository = roleRepository;
  }

  @Override
  public HibernateRole convert(RoleChangeRequest request) {

    HibernateRole role =
        roleRepository.findById(request.getId()).orElseThrow(EntityNotFoundException::new);

    role.setRoleName(SystemRoles.valueOf(request.getRoleName()));
    role.setModificationDate(new Timestamp(new Date().getTime()));

    return role;
  }
}
