package com.zemelya.controller;

import com.zemelya.service.car.CarService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.Collections;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest/data/cars")
@Tag(name = "Car controller")
public class CarController {

  private final CarService service;

  public final ConversionService conversionService;

  @GetMapping("/findAllPageable")
  @Parameter(
      in = ParameterIn.QUERY,
      description =
          "Sorting criteria in the format: property(,asc|desc). "
              + "Default sort order is ascending. "
              + "Multiple sort criteria are supported.",
      name = "sort",
      array = @ArraySchema(schema = @Schema(type = "string")))
  public ResponseEntity<Object> findAllPageable(@ParameterObject Pageable pageable) {

    return new ResponseEntity<>(service.findAll(pageable), HttpStatus.OK);
  }

  @GetMapping("/findById{id}")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<Object> findById(@PathVariable String id) {
    Long carId = 0L;
    try {
      carId = Long.parseLong(id);
    } catch (NumberFormatException e) {
      throw new NumberFormatException("Invalid car ID");
    }

    return new ResponseEntity<>(
        Collections.singletonMap("result", service.findById(carId)), HttpStatus.OK);
  }

  @GetMapping("/showAvailableCars{date}")
  @Parameter(in = ParameterIn.PATH, name = "date", required = true, example = "2022-10-24 00:00:00")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<Object> showAvailableCars(@PathVariable() String date) {

    Timestamp dateForQuery;
    try {
      dateForQuery = Timestamp.valueOf(date);
    } catch (NumberFormatException e) {
      throw new NumberFormatException("Invalid date.");
    }
    return new ResponseEntity<>(service.showAvailableCars(dateForQuery), HttpStatus.OK);
  }

  @GetMapping("/showTopPopularCars{selectedLimit}")
  @Parameter(in = ParameterIn.PATH, name = "selectedLimit", required = true, example = "1")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<Object> showTopPopularCars(@PathVariable String selectedLimit) {
    Integer selectedLimitInt;
    try{
      selectedLimitInt = Integer.parseInt(selectedLimit);
    }
    catch(NumberFormatException e) {
            throw new NumberFormatException("Incorrect limit");
    }
    return new ResponseEntity<>(service.showTopPopularCars(selectedLimitInt), HttpStatus.OK);
  }
}
