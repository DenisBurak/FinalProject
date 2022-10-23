package com.zemelya.converters.car;

import com.zemelya.controller.request.car.CarCreateRequest;
import com.zemelya.domain.hibernate.HibernateCar;
import com.zemelya.service.model.ModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class CarCreateRequestConverter extends CarBaseConverter<CarCreateRequest, HibernateCar> {

  private final ModelService service;

  @Override
  public HibernateCar convert(CarCreateRequest request) {

    HibernateCar hibernateCar = new HibernateCar();

    hibernateCar.setModel(service.findById(request.getModelId()));

    hibernateCar.setCreationDate(new Timestamp(new Date().getTime()));

    return doConvert(hibernateCar, request);
  }
}
