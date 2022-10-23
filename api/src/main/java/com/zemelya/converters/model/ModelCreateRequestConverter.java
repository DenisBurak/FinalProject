package com.zemelya.converters.model;

import com.zemelya.controller.request.drivingLicenses.DrivingLicenseCreateRequest;
import com.zemelya.controller.request.model.ModelCreateRequest;
import com.zemelya.converters.drivingLicense.DrivingLicenseBaseConverter;
import com.zemelya.domain.hibernate.HibernateDrivingLicense;
import com.zemelya.domain.hibernate.HibernateModel;
import com.zemelya.service.bodyType.BodyTypeService;
import com.zemelya.service.brand.BrandService;
import com.zemelya.service.model.ModelService;
import com.zemelya.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class ModelCreateRequestConverter
    extends ModelBaseConverter<ModelCreateRequest, HibernateModel> {

  private final BrandService brandService;

  private final BodyTypeService bodyTypeService;

  @Override
  public HibernateModel convert(ModelCreateRequest request) {

    HibernateModel hibernateModel = new HibernateModel();

    hibernateModel.setBrand(brandService.findById(request.getBrandId()));

    hibernateModel.setBodyType(bodyTypeService.findById(request.getBodyTypeId()));

    hibernateModel.setCreationDate(new Timestamp(new Date().getTime()));

    return doConvert(hibernateModel, request);
  }
}
