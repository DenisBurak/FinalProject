package com.zemelya.controller;

import com.zemelya.controller.request.bodyType.BodyTypeChangeRequest;
import com.zemelya.controller.request.bodyType.BodyTypeCreateRequest;
import com.zemelya.controller.request.brand.BrandChangeRequest;
import com.zemelya.controller.request.brand.BrandCreateRequest;
import com.zemelya.controller.request.drivingLicenses.DrivingLicenseChangeRequest;
import com.zemelya.controller.request.user.UserChangeRequest;
import com.zemelya.domain.hibernate.HibernateBodyType;
import com.zemelya.domain.hibernate.HibernateBrand;
import com.zemelya.domain.hibernate.HibernateDrivingLicense;
import com.zemelya.domain.hibernate.HibernateUser;
import com.zemelya.service.bodyType.BodyTypeService;
import com.zemelya.service.brand.BrandService;
import com.zemelya.service.drivingLicense.DrivingLicenseService;
import com.zemelya.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
@Tag(name = "Administator/Moderator controller")
public class AdminController {

  private final UserService userService;

  public final ConversionService conversionService;

  public final BodyTypeService bodyTypeService;

  public final BrandService brandService;

  public final DrivingLicenseService drivingLicenseService;

  @PostMapping("/users/update")
  @Parameter(in = ParameterIn.HEADER, name = "X-Auth-Token", required = true)
  @Transactional
  @ResponseStatus(HttpStatus.OK)
  @RequestBody(
      description = "This method allows update the user in DataBase.",
      required = true,
      content = @Content(schema = @Schema(implementation = UserChangeRequest.class)))
  public ResponseEntity<Object> updateUser(@Valid
      @org.springframework.web.bind.annotation.RequestBody UserChangeRequest userChangeRequest) {

    HibernateUser hibernateUser = conversionService.convert(userChangeRequest, HibernateUser.class);

    hibernateUser = userService.update(hibernateUser);

    Map<String, Object> model = new HashMap<>();
    model.put("user", userService.findById(hibernateUser.getId()));

    return new ResponseEntity<>(model, HttpStatus.OK);
  }

  @PostMapping("/users/delete{id}")
  @Parameter(in = ParameterIn.HEADER, name = "X-Auth-Token", required = true)
  @Transactional
  @Operation(description = "This method allows deactivate the user in DataBase by ID")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<Object> deleteUser(@PathVariable String id) {

    Long userId = 0l;
    try {
      userId = Long.parseLong(id);
    } catch (NumberFormatException e) {
      throw new NumberFormatException("Invalid user ID");
    }

    HibernateUser hibernateUser = userService.delete(userId);

    Map<String, Object> model = new HashMap<>();
    model.put("user", userService.findById(hibernateUser.getId()));

    return new ResponseEntity<>(model, HttpStatus.OK);
  }

  @GetMapping("/users/findAllPageable")
  @Parameter(in = ParameterIn.HEADER, name = "X-Auth-Token", required = true)
  @Parameter(
      in = ParameterIn.QUERY,
      description =
          "Sorting criteria in the format: property(,asc|desc). "
              + "Default sort order is ascending. "
              + "Multiple sort criteria are supported.",
      name = "sort",
      array = @ArraySchema(schema = @Schema(type = "string")))
  public ResponseEntity<Object> findAllPageable(@ParameterObject Pageable pageable) {

    return new ResponseEntity<>(userService.findAll(pageable), HttpStatus.OK);
  }

  @GetMapping("/users/findAll")
  @Parameter(in = ParameterIn.HEADER, name = "X-Auth-Token", required = true)
  public ResponseEntity<Object> findAllUsers() {

    return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
  }

  @GetMapping("/users/findById{id}")
  @Parameter(in = ParameterIn.HEADER, name = "X-Auth-Token", required = true)
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<Object> findById(@PathVariable String id) {
    Long userId = 0l;
    try {
      userId = Long.parseLong(id);
    } catch (NumberFormatException e) {
      throw new NumberFormatException("Invalid user ID");
    }
    return new ResponseEntity<>(
            Collections.singletonMap("result", userService.findById(userId)), HttpStatus.OK);
  }

  @PostMapping("/bodyTypes/create")
  @Parameter(in = ParameterIn.HEADER, name = "X-Auth-Token", required = true)
  @Transactional
  @ResponseStatus(HttpStatus.CREATED)
  @RequestBody(
          description = "This method allows create a new body type in DataBase.",
          required = true,
          content = @Content(schema = @Schema(implementation = BodyTypeCreateRequest.class)))
  public ResponseEntity<Object> createRole(@Valid
                                           @org.springframework.web.bind.annotation.RequestBody BodyTypeCreateRequest bodyTypeCreateRequest) {

    HibernateBodyType hibernateBodyType = conversionService.convert(bodyTypeCreateRequest, HibernateBodyType.class);

    hibernateBodyType = bodyTypeService.create(hibernateBodyType);

    Map<String, Object> model = new HashMap<>();
    model.put("bodyType", bodyTypeService.findById(hibernateBodyType.getId()));

    return new ResponseEntity<>(model, HttpStatus.CREATED);
  }

  @PostMapping("/bodyTypes/update")
  @Parameter(in = ParameterIn.HEADER, name = "X-Auth-Token", required = true)
  @Transactional
  @ResponseStatus(HttpStatus.OK)
  @RequestBody(
          description = "This method allows update the body type in DataBase.",
          required = true,
          content = @Content(schema = @Schema(implementation = BodyTypeChangeRequest.class)))
  public ResponseEntity<Object> updateBodyType(@Valid
                                           @org.springframework.web.bind.annotation.RequestBody BodyTypeChangeRequest bodyTypeChangeRequest) {

    HibernateBodyType hibernateBodyType = conversionService.convert(bodyTypeChangeRequest, HibernateBodyType.class);

    hibernateBodyType = bodyTypeService.update(hibernateBodyType);

    Map<String, Object> model = new HashMap<>();
    model.put("bodyType", bodyTypeService.findById(hibernateBodyType.getId()));

    return new ResponseEntity<>(model, HttpStatus.OK);
  }

  @PostMapping("/bodyTypes/delete{id}")
  @Parameter(in = ParameterIn.HEADER, name = "X-Auth-Token", required = true)
  @Transactional
  @Operation(description = "This method allows deactivate the body type in DataBase")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<Object> deleteBodyType(@PathVariable String id) {

    Integer bodyTypeId = 0;
    try {
      bodyTypeId = Integer.parseInt(id);
    } catch (NumberFormatException e) {
      throw new NumberFormatException("Invalid body type ID");
    }
    HibernateBodyType hibernateBodyType = bodyTypeService.delete(bodyTypeId);

    Map<String, Object> model = new HashMap<>();
    model.put("bodyType", bodyTypeService.findById(hibernateBodyType.getId()));

    return new ResponseEntity<>(model, HttpStatus.OK);
  }

  @GetMapping("/bodyTypes/findAll")
  @Parameter(in = ParameterIn.HEADER, name = "X-Auth-Token", required = true)
  public ResponseEntity<Object> findAllBodyTypes() {

    return new ResponseEntity<>(bodyTypeService.findAll(), HttpStatus.OK);
  }

  @PostMapping("/brands/create")
  @Parameter(in = ParameterIn.HEADER, name = "X-Auth-Token", required = true)
  @Transactional
  @ResponseStatus(HttpStatus.CREATED)
  @RequestBody(
          description = "This method allows create a brand in DataBase.",
          required = true,
          content = @Content(schema = @Schema(implementation = BrandCreateRequest.class)))
  public ResponseEntity<Object> createBrand(@Valid
                                           @org.springframework.web.bind.annotation.RequestBody BrandCreateRequest brandCreateRequest) {

    HibernateBrand hibernateBrand = conversionService.convert(brandCreateRequest, HibernateBrand.class);

    hibernateBrand = brandService.create(hibernateBrand);

    Map<String, Object> model = new HashMap<>();
    model.put("brand", brandService.findById(hibernateBrand.getId()));

    return new ResponseEntity<>(model, HttpStatus.CREATED);
  }

  @PostMapping("/brands/update")
  @Parameter(in = ParameterIn.HEADER, name = "X-Auth-Token", required = true)
  @Transactional
  @ResponseStatus(HttpStatus.OK)
  @RequestBody(
          description = "This method allows update the body type in DataBase.",
          required = true,
          content = @Content(schema = @Schema(implementation = BrandChangeRequest.class)))
  public ResponseEntity<Object> updateBrand(@Valid
                                           @org.springframework.web.bind.annotation.RequestBody BrandChangeRequest brandChangeRequest) {

    HibernateBrand hibernateBrand = conversionService.convert(brandChangeRequest, HibernateBrand.class);

    hibernateBrand = brandService.update(hibernateBrand);

    Map<String, Object> model = new HashMap<>();
    model.put("brand", brandService.findById(hibernateBrand.getId()));

    return new ResponseEntity<>(model, HttpStatus.OK);
  }

  @PostMapping("/brands/delete{id}")
  @Parameter(in = ParameterIn.HEADER, name = "X-Auth-Token", required = true)
  @Transactional
  @Operation(description = "This method allows deactivate the brand type in DataBase")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<Object> deleteBrand(@PathVariable String id) {

    Integer brandId = 0;
    try {
      brandId = Integer.parseInt(id);
    } catch (NumberFormatException e) {
      throw new NumberFormatException("Invalid brand ID");
    }
    HibernateBrand hibernateBrand = brandService.delete(brandId);

    Map<String, Object> model = new HashMap<>();
    model.put("brand", brandService.findById(hibernateBrand.getId()));

    return new ResponseEntity<>(model, HttpStatus.OK);
  }

  @PostMapping("/drivingLicenses/update")
  @Parameter(in = ParameterIn.HEADER, name = "X-Auth-Token", required = true)
  @Transactional
  @ResponseStatus(HttpStatus.OK)
  @RequestBody(
          description = "This method allows update the user's driving license in DataBase.",
          required = true,
          content = @Content(schema = @Schema(implementation = DrivingLicenseChangeRequest.class)))
  public ResponseEntity<Object> updateDrivingLicense(@Valid
                                           @org.springframework.web.bind.annotation.RequestBody DrivingLicenseChangeRequest drivingLicenseChangeRequest) {

    HibernateDrivingLicense drivingLicense = conversionService.convert(drivingLicenseChangeRequest, HibernateDrivingLicense.class);

    drivingLicense = drivingLicenseService.update(drivingLicense);

    Map<String, Object> model = new HashMap<>();
    model.put("drivingLicense", drivingLicenseService.findById(drivingLicense.getId()));

    return new ResponseEntity<>(model, HttpStatus.OK);
  }

  @PostMapping("/drivingLicenses/delete{id}")
  @Parameter(in = ParameterIn.HEADER, name = "X-Auth-Token", required = true)
  @Transactional
  @Operation(description = "This method allows deactivate the user's driving license in DataBase by ID")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<Object> deleteDrivingLicense(@PathVariable String id) {

    Long drivingLicenseId = 0l;
    try {
      drivingLicenseId = Long.parseLong(id);
    } catch (NumberFormatException e) {
      throw new NumberFormatException("Invalid driving license ID");
    }

    HibernateDrivingLicense drivingLicense = drivingLicenseService.delete(drivingLicenseId);

    Map<String, Object> model = new HashMap<>();
    model.put("drivingLicense", drivingLicenseService.findById(drivingLicense.getId()));

    return new ResponseEntity<>(model, HttpStatus.OK);
  }

  @GetMapping("/drivingLicenses/findAllPageable")
  @Parameter(in = ParameterIn.HEADER, name = "X-Auth-Token", required = true)
  @Parameter(
          in = ParameterIn.QUERY,
          description =
                  "Sorting criteria in the format: property(,asc|desc). "
                          + "Default sort order is ascending. "
                          + "Multiple sort criteria are supported.",
          name = "sort",
          array = @ArraySchema(schema = @Schema(type = "string")))
  public ResponseEntity<Object> findAllDrivingLicensesPageable(@ParameterObject Pageable pageable) {

    return new ResponseEntity<>(drivingLicenseService.findAll(pageable), HttpStatus.OK);
  }

  @GetMapping("/drivingLicenses/findAll")
  @Parameter(in = ParameterIn.HEADER, name = "X-Auth-Token", required = true)
  public ResponseEntity<Object> findAllDrivingLicenses() {

    return new ResponseEntity<>(drivingLicenseService.findAll(), HttpStatus.OK);
  }

  @GetMapping("/drivingLicenses/findById{id}")
  @Parameter(in = ParameterIn.HEADER, name = "X-Auth-Token", required = true)
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<Object> findByDrivingLicenseId(@PathVariable String id) {
    Long drivingLicenseId = 0l;
    try {
      drivingLicenseId = Long.parseLong(id);
    } catch (NumberFormatException e) {
      throw new NumberFormatException("Invalid driving license ID");
    }
    return new ResponseEntity<>(
            Collections.singletonMap("result", drivingLicenseService.findById(drivingLicenseId)), HttpStatus.OK);
  }

}
