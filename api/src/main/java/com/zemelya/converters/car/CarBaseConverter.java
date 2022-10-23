package com.zemelya.converters.car;

import com.zemelya.controller.request.car.CarCreateRequest;
import com.zemelya.domain.hibernate.HibernateCar;
import org.springframework.core.convert.converter.Converter;

import java.sql.Timestamp;
import java.util.Date;

public abstract class CarBaseConverter<S, T> implements Converter<S, T> {

  protected HibernateCar doConvert(HibernateCar hibernateCar, CarCreateRequest request) {

    hibernateCar.setVinNumber(request.getVinNumber());
    hibernateCar.setPrice(request.getPrice());
    hibernateCar.setVolume(request.getVolume());

    /*System fields filling*/
    hibernateCar.setModificationDate(new Timestamp(new Date().getTime()));
    hibernateCar.setIsAvailable(true);

    return hibernateCar;
  }
}
