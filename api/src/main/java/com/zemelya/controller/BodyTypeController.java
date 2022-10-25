package com.zemelya.controller;

import com.zemelya.service.bodyType.BodyTypeService;
import io.swagger.v3.oas.annotations.Operation;
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

import java.util.Collections;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest/data/bodyTypes")
@Tag(name = "Body type controller")
public class BodyTypeController {

  private final BodyTypeService service;

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
    Integer bodyTypeId;
    try {
      bodyTypeId = Integer.parseInt(id);
    } catch (NumberFormatException e) {
      throw new NumberFormatException("Invalid body type ID");
    }

    return new ResponseEntity<>(
        Collections.singletonMap("result", service.findById(bodyTypeId)), HttpStatus.OK);
  }

  @GetMapping("/showBodyTypes")
  @Operation(description = "Shows body types without extra information")
  public ResponseEntity<Object> findOnlyBodyTypes() {

    return new ResponseEntity<>(service.findOnlyBodyTypes(), HttpStatus.OK);
  }
}
