package com.zemelya.converters.car;

import com.zemelya.controller.request.car.CarChangeRequest;
import com.zemelya.domain.hibernate.HibernateCar;
import com.zemelya.repository.car.CarSpringDataRepository;
import com.zemelya.repository.model.ModelSpringDataRepository;
import com.zemelya.service.model.ModelService;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;

@Component
public class CarEditRequestConverter extends CarBaseConverter<CarChangeRequest, HibernateCar> {

  private final CarSpringDataRepository repository;

  private final ModelService modelService;

  public CarEditRequestConverter(CarSpringDataRepository repository, ModelService modelService) {
    this.repository = repository;
    this.modelService = modelService;
  }

  @Override
  public HibernateCar convert(CarChangeRequest request) {

    HibernateCar hibernateCar =
        repository.findById(request.getId()).orElseThrow(EntityNotFoundException::new);

    hibernateCar.setModel(modelService.findById(request.getModelId()));

    return doConvert(hibernateCar, request);
  }
}
