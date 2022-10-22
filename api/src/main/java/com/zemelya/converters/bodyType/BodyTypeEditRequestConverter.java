package com.zemelya.converters.bodyType;

import com.zemelya.controller.request.bodyType.BodyTypeChangeRequest;
import com.zemelya.controller.request.role.RoleChangeRequest;
import com.zemelya.domain.SystemRoles;
import com.zemelya.domain.hibernate.HibernateBodyType;
import com.zemelya.domain.hibernate.HibernateRole;
import com.zemelya.repository.bodyType.BodyTypeSpringDataRepository;
import com.zemelya.repository.role.RoleSpringDataRepository;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.sql.Timestamp;
import java.util.Date;

@Component
public class BodyTypeEditRequestConverter implements Converter<BodyTypeChangeRequest, HibernateBodyType> {

  private final BodyTypeSpringDataRepository bodyTypeSpringDataRepository;

  public BodyTypeEditRequestConverter(BodyTypeSpringDataRepository bodyTypeSpringDataRepository) {
    this.bodyTypeSpringDataRepository = bodyTypeSpringDataRepository;
  }

  @Override
  public HibernateBodyType convert(BodyTypeChangeRequest request) {

    HibernateBodyType hibernateBodyType =
            bodyTypeSpringDataRepository.findById(request.getId()).orElseThrow(EntityNotFoundException::new);

    hibernateBodyType.setBodyTypeName(request.getBodyTypeName());

    return hibernateBodyType;
  }
}
