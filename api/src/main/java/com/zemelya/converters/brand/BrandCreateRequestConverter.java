package com.zemelya.converters.brand;

import com.zemelya.controller.request.brand.BrandCreateRequest;
import com.zemelya.domain.hibernate.HibernateBrand;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class BrandCreateRequestConverter implements Converter<BrandCreateRequest, HibernateBrand> {

  @Override
  public HibernateBrand convert(BrandCreateRequest request) {

    HibernateBrand brand = new HibernateBrand();

    brand.setBrandName(request.getBrandName());
    brand.setIsAvailable(true);

    return brand;
  }
}
