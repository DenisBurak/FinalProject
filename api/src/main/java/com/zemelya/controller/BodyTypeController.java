package com.zemelya.controller;

import com.zemelya.repository.bodyType.BodyTypeSpringDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest/data/bodyTypes")
public class BodyTypeController {

  private final BodyTypeSpringDataRepository repository;

  @GetMapping("/AllBodyTypes")
  public ResponseEntity<Object> findAll() {

    return new ResponseEntity<>(
        Collections.singletonMap("result", repository.findAllOnlyBrands()), HttpStatus.OK);
  }

  @GetMapping("/showFullInfo")
  public ResponseEntity<Object> findAllBodyTypesInfo() {

    return new ResponseEntity<>(
        Collections.singletonMap("result", repository.findAll()), HttpStatus.OK);
  }
}
