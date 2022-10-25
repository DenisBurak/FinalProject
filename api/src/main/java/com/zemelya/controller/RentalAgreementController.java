package com.zemelya.controller;

import com.zemelya.controller.request.rentalAgreement.RentalAgreementCreateRequest;
import com.zemelya.domain.hibernate.HibernateDrivingLicense;
import com.zemelya.domain.hibernate.HibernateRentalAgreement;
import com.zemelya.domain.hibernate.HibernateUser;
import com.zemelya.repository.user.UserSpringDataRepository;
import com.zemelya.security.util.PrincipalUtil;
import com.zemelya.service.rentalAgreement.RentalAgreementService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest/data/rentalAgreements")
@Tag(name = "Rental agreement controller")
public class RentalAgreementController {

  private final RentalAgreementService service;

  private final ConversionService conversionService;

  private final UserSpringDataRepository userRepository;

  @PostMapping()
  @Parameter(in = ParameterIn.HEADER, name = "X-Auth-Token", required = true)
  @Transactional
  @ResponseStatus(HttpStatus.CREATED)
  @RequestBody(
      description = "This method allows create a new rental agreement in DataBase.",
      required = true,
      content = @Content(schema = @Schema(implementation = RentalAgreementCreateRequest.class)))
  public ResponseEntity<Object> createRentalAgreement(
      @Valid @org.springframework.web.bind.annotation.RequestBody
          RentalAgreementCreateRequest rentalAgreementCreateRequest,
      Principal principal) {

    String username = PrincipalUtil.getUsername(principal);
    Optional<HibernateUser> result = userRepository.findByCredentialsLogin(username);

    if (result.isPresent()) {

      rentalAgreementCreateRequest.setUserId(result.get().getId());

      HibernateRentalAgreement rentalAgreement =
          conversionService.convert(rentalAgreementCreateRequest, HibernateRentalAgreement.class);

      rentalAgreement = service.create(rentalAgreement);

      Map<String, Object> model = new HashMap<>();
      model.put("rental agreement", service.findById(rentalAgreement.getId()));

      return new ResponseEntity<>(model, HttpStatus.CREATED);
    } else {
      throw new AuthorizationServiceException("User is not authenticate");
    }
  }

  @GetMapping("/show")
  @Parameter(in = ParameterIn.HEADER, name = "X-Auth-Token", required = true)
  @ResponseStatus(HttpStatus.OK)
  public HibernateRentalAgreement showRentalAgreements(Principal principal) {

    String username = PrincipalUtil.getUsername(principal);
    Optional<HibernateUser> result = userRepository.findByCredentialsLogin(username);

    if (result.isPresent()) {
      return service.findByUserId(result.get().getId());
    } else {
      throw new AuthorizationServiceException("User is not authenticate");
    }
  }
}
