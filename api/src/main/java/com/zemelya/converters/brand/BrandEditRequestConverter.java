package com.zemelya.converters.brand;

import com.zemelya.controller.request.brand.BrandChangeRequest;
import com.zemelya.domain.hibernate.HibernateBrand;
import com.zemelya.service.brand.BrandService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class BrandEditRequestConverter implements Converter<BrandChangeRequest, HibernateBrand> {

  private final BrandService service;

  public BrandEditRequestConverter(BrandService service) {
    this.service = service;
  }

  @Override
  public HibernateBrand convert(BrandChangeRequest request) {

    HibernateBrand brand = service.findById(request.getId());

    brand.setBrandName(request.getBrandName());

    return brand;
  }
}
