package com.zemelya.converters.brand;

import com.zemelya.controller.request.brand.BrandChangeRequest;
import com.zemelya.domain.hibernate.HibernateBrand;
import com.zemelya.repository.brand.BrandSpringDataRepository;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;

@Component
public class BrandEditRequestConverter implements Converter<BrandChangeRequest, HibernateBrand> {

  private final BrandSpringDataRepository brandSpringDataRepository;

  public BrandEditRequestConverter(BrandSpringDataRepository brandSpringDataRepository) {
    this.brandSpringDataRepository = brandSpringDataRepository;
  }

  @Override
  public HibernateBrand convert(BrandChangeRequest request) {

    HibernateBrand brand =
        brandSpringDataRepository
            .findById(request.getId())
            .orElseThrow(EntityNotFoundException::new);

    brand.setBrandName(request.getBrandName());

    return brand;
  }
}
