package com.zemelya.converters.model;

import com.zemelya.controller.request.model.ModelChangeRequest;
import com.zemelya.domain.hibernate.HibernateModel;
import com.zemelya.repository.model.ModelSpringDataRepository;
import com.zemelya.service.bodyType.BodyTypeService;
import com.zemelya.service.brand.BrandService;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;

@Component
public class ModelEditRequestConverter
    extends ModelBaseConverter<ModelChangeRequest, HibernateModel> {

  private final ModelSpringDataRepository repository;

  private final BrandService brandService;

  private final BodyTypeService bodyTypeService;

  public ModelEditRequestConverter(
      ModelSpringDataRepository repository,
      BrandService brandService,
      BodyTypeService bodyTypeService) {
    this.repository = repository;
    this.brandService = brandService;
    this.bodyTypeService = bodyTypeService;
  }

  @Override
  public HibernateModel convert(ModelChangeRequest request) {

    HibernateModel hibernateModel =
        repository.findById(request.getId()).orElseThrow(EntityNotFoundException::new);

    hibernateModel.setBrand(brandService.findById(request.getBrandId()));

    hibernateModel.setBodyType(bodyTypeService.findById(request.getBodyTypeId()));

    return doConvert(hibernateModel, request);
  }
}
