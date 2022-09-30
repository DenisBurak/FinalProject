package com.zemelya.controller;

import com.zemelya.repository.drivingLicenses.DrivingLicensesSpringDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest/data/drivingLicenses")
public class DrivingLicensesController {

    private final DrivingLicensesSpringDataRepository repository;

    @GetMapping("/showAll")
    public ResponseEntity<Object> findAll() {

        return new ResponseEntity<>(
                Collections.singletonMap("result", repository.findAll()),
                HttpStatus.OK
        );
    }

}
