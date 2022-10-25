package com.zemelya.converters.model;

import com.zemelya.controller.request.model.ModelChangeRequest;
import com.zemelya.domain.hibernate.HibernateModel;
import com.zemelya.service.bodyType.BodyTypeService;
import com.zemelya.service.brand.BrandService;
import com.zemelya.service.model.ModelService;
import org.springframework.stereotype.Component;

@Component
public class ModelEditRequestConverter
    extends ModelBaseConverter<ModelChangeRequest, HibernateModel> {

  private final ModelService service;

  private final BrandService brandService;

  private final BodyTypeService bodyTypeService;

  public ModelEditRequestConverter(
      ModelService service, BrandService brandService, BodyTypeService bodyTypeService) {
    this.service = service;
    this.brandService = brandService;
    this.bodyTypeService = bodyTypeService;
  }

  @Override
  public HibernateModel convert(ModelChangeRequest request) {

    HibernateModel hibernateModel = service.findById(request.getId());

    hibernateModel.setBrand(brandService.findById(request.getBrandId()));

    hibernateModel.setBodyType(bodyTypeService.findById(request.getBodyTypeId()));

    return doConvert(hibernateModel, request);
  }
}
