package com.zemelya.converters.bodyType;

import com.zemelya.controller.request.bodyType.BodyTypeChangeRequest;
import com.zemelya.domain.hibernate.HibernateBodyType;
import com.zemelya.service.bodyType.BodyTypeService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class BodyTypeEditRequestConverter
    implements Converter<BodyTypeChangeRequest, HibernateBodyType> {

  private final BodyTypeService service;

  public BodyTypeEditRequestConverter(BodyTypeService service) {
    this.service = service;
  }

  @Override
  public HibernateBodyType convert(BodyTypeChangeRequest request) {

    HibernateBodyType hibernateBodyType = service.findById(request.getId());

    hibernateBodyType.setBodyTypeName(request.getBodyTypeName());

    return hibernateBodyType;
  }
}
