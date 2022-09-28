package com.zemelya.controller;

import com.zemelya.repository.brand.BrandSpringDataRepository;
import com.zemelya.repository.role.RoleSpringDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest/data/brands")
public class BrandController {

    private final BrandSpringDataRepository repository;

    @GetMapping
    public ResponseEntity<Object> findAllRolesWithCache() {

        return new ResponseEntity<>(
                Collections.singletonMap("result", repository.findAll()),
                HttpStatus.OK
        );
    }
}
