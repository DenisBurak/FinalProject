package com.zemelya.controller;

import com.zemelya.repository.rentalAgreement.RentalAgreementSpringDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest/data/rentalAgreements")
public class RentalAgreementController {

    private final RentalAgreementSpringDataRepository repository;

    @GetMapping("/showAll")
    public ResponseEntity<Object> findAll() {

        return new ResponseEntity<>(
                Collections.singletonMap("result", repository.findAll()),
                HttpStatus.OK
        );
    }

    @GetMapping("/showFullInfo")
    public ResponseEntity<Object> findFullInfo() {

        return new ResponseEntity<>(
                Collections.singletonMap("result", repository.findAllInfo()),
                HttpStatus.OK
        );
    }

}
