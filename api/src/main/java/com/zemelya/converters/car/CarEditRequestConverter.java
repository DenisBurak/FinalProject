package com.zemelya.converters.car;

import com.zemelya.controller.request.car.CarChangeRequest;
import com.zemelya.domain.hibernate.HibernateCar;
import com.zemelya.service.car.CarService;
import com.zemelya.service.model.ModelService;
import org.springframework.stereotype.Component;

@Component
public class CarEditRequestConverter extends CarBaseConverter<CarChangeRequest, HibernateCar> {

  private final CarService service;

  private final ModelService modelService;

  public CarEditRequestConverter(CarService service, ModelService modelService) {
    this.service = service;
    this.modelService = modelService;
  }

  @Override
  public HibernateCar convert(CarChangeRequest request) {

    HibernateCar hibernateCar = service.findById(request.getId());

    hibernateCar.setModel(modelService.findById(request.getModelId()));

    return doConvert(hibernateCar, request);
  }
}
