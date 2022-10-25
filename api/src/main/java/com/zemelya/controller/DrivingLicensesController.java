package com.zemelya.controller;

import com.zemelya.controller.request.drivingLicenses.DrivingLicenseChangeRequest;
import com.zemelya.controller.request.drivingLicenses.DrivingLicenseCreateRequest;
import com.zemelya.domain.hibernate.HibernateDrivingLicense;
import com.zemelya.domain.hibernate.HibernateUser;
import com.zemelya.repository.drivingLicenses.DrivingLicensesSpringDataRepository;
import com.zemelya.repository.user.UserSpringDataRepository;
import com.zemelya.security.util.PrincipalUtil;
import com.zemelya.service.drivingLicense.DrivingLicenseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Tag(name = "Driving license controller")
@RequestMapping("/rest/data/drivingLicenses")
public class DrivingLicensesController {

  private final UserSpringDataRepository userRepository;

  private final DrivingLicenseService service;

  public final ConversionService conversionService;

  @GetMapping("/show")
  @Parameter(in = ParameterIn.HEADER, name = "X-Auth-Token", required = true)
  @ResponseStatus(HttpStatus.OK)
  public HibernateDrivingLicense showDrivingLicense(Principal principal) {
    String username = PrincipalUtil.getUsername(principal);
    Optional<HibernateUser> result = userRepository.findByCredentialsLogin(username);

    if (result.isPresent()) {
      return service.findByUserId(result.get().getId());
    } else {
      throw new AuthorizationServiceException("User is not authenticate");
    }
  }

  @PostMapping()
  @Parameter(in = ParameterIn.HEADER, name = "X-Auth-Token", required = true)
  @Transactional
  @ResponseStatus(HttpStatus.CREATED)
  @RequestBody(
      description = "This method allows create a new driving license in DataBase.",
      required = true,
      content = @Content(schema = @Schema(implementation = DrivingLicenseCreateRequest.class)))
  public ResponseEntity<Object> createDrivingLicense(
      @Valid @org.springframework.web.bind.annotation.RequestBody
          DrivingLicenseCreateRequest drivingLicenseCreateRequest,
      Principal principal) {

    String username = PrincipalUtil.getUsername(principal);
    Optional<HibernateUser> result = userRepository.findByCredentialsLogin(username);

    if (result.isPresent()) {

      Long userId = result.get().getId();
      if(service.findByUserId(userId) != null){
        throw new NumberFormatException("Driving license for current user is existed.");
      }

      drivingLicenseCreateRequest.setUserId(userId);

      HibernateDrivingLicense drivingLicense =
          conversionService.convert(drivingLicenseCreateRequest, HibernateDrivingLicense.class);

      drivingLicense = service.create(drivingLicense);

      Map<String, Object> model = new HashMap<>();
      model.put("drivingLicense", service.findById(drivingLicense.getId()));

      return new ResponseEntity<>(model, HttpStatus.CREATED);
    } else {
      throw new AuthorizationServiceException("User is not authenticate");
    }
  }

  @PostMapping("/update")
  @Parameter(in = ParameterIn.HEADER, name = "X-Auth-Token", required = true)
  @Transactional
  @ResponseStatus(HttpStatus.OK)
  @RequestBody(
      description = "This method allows update the user's driving license in DataBase.",
      required = true,
      content = @Content(schema = @Schema(implementation = DrivingLicenseChangeRequest.class)))
  public ResponseEntity<Object> updateDriverLicense(
      @Valid @org.springframework.web.bind.annotation.RequestBody
          DrivingLicenseChangeRequest drivingLicenseChangeRequest,
      Principal principal) {

    String username = PrincipalUtil.getUsername(principal);
    Optional<HibernateUser> result = userRepository.findByCredentialsLogin(username);

    if (result.isPresent()) {

      Long userId = result.get().getId();
      drivingLicenseChangeRequest.setUserId(userId);

      HibernateDrivingLicense drivingLicense = service.findByUserId(userId);

      drivingLicenseChangeRequest.setId(drivingLicense.getId());

      drivingLicense =
          conversionService.convert(drivingLicenseChangeRequest, HibernateDrivingLicense.class);

      drivingLicense = service.update(drivingLicense);

      Map<String, Object> model = new HashMap<>();
      model.put("drivingLicense", service.findById(drivingLicense.getId()));

      return new ResponseEntity<>(model, HttpStatus.OK);

    } else {
      throw new AuthorizationServiceException("User is not authenticate");
    }
  }

  @PostMapping("/delete")
  @Parameter(in = ParameterIn.HEADER, name = "X-Auth-Token", required = true)
  @Transactional
  @Operation(description = "This method allows deactivate the user's driving license in DataBase")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<Object> deleteDrivingLicense(Principal principal) {

    String username = PrincipalUtil.getUsername(principal);
    Optional<HibernateUser> result = userRepository.findByCredentialsLogin(username);

    if (result.isPresent()) {

      HibernateDrivingLicense drivingLicense = service.findByUserId(result.get().getId());

      drivingLicense = service.delete(drivingLicense.getId());

      Map<String, Object> model = new HashMap<>();
      model.put("drivingLicense", service.findById(drivingLicense.getId()));

      return new ResponseEntity<>(model, HttpStatus.OK);

    } else {
      throw new AuthorizationServiceException("User is not authenticate");
    }
  }
}
