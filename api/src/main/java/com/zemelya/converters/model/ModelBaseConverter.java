package com.zemelya.converters.model;

import com.zemelya.controller.request.model.ModelCreateRequest;
import com.zemelya.domain.hibernate.HibernateModel;
import org.springframework.core.convert.converter.Converter;

import java.sql.Timestamp;
import java.util.Date;

public abstract class ModelBaseConverter<S, T> implements Converter<S, T> {

  protected HibernateModel doConvert(HibernateModel hibernateModel, ModelCreateRequest request) {

    hibernateModel.setModelName(request.getModelName());

    /*System fields filling*/
    hibernateModel.setModificationDate(new Timestamp(new Date().getTime()));
    hibernateModel.setIsAvailable(true);

    return hibernateModel;
  }
}
