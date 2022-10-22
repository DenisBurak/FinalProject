package com.zemelya.converters.bodyType;

import com.zemelya.controller.request.bodyType.BodyTypeCreateRequest;
import com.zemelya.domain.hibernate.HibernateBodyType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class BodyTypeCreateRequestConverter
    implements Converter<BodyTypeCreateRequest, HibernateBodyType> {

  @Override
  public HibernateBodyType convert(BodyTypeCreateRequest request) {

    HibernateBodyType bodyType = new HibernateBodyType();

    bodyType.setBodyTypeName(request.getBodyTypeName());
    bodyType.setIsAvailable(true);

    return bodyType;
  }
}
