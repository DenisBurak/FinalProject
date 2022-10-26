package com.zemelya.controller;

import com.zemelya.controller.request.bodyType.BodyTypeChangeRequest;
import com.zemelya.controller.request.bodyType.BodyTypeCreateRequest;
import com.zemelya.controller.request.brand.BrandChangeRequest;
import com.zemelya.controller.request.brand.BrandCreateRequest;
import com.zemelya.controller.request.car.CarChangeRequest;
import com.zemelya.controller.request.car.CarCreateRequest;
import com.zemelya.controller.request.drivingLicenses.DrivingLicenseChangeRequest;
import com.zemelya.controller.request.model.ModelChangeRequest;
import com.zemelya.controller.request.model.ModelCreateRequest;
import com.zemelya.controller.request.rentalAgreement.RentalAgreementChangeRequest;
import com.zemelya.controller.request.rentalAgreement.RentalAgreementCreateRequest;
import com.zemelya.controller.request.user.UserChangeRequest;
import com.zemelya.domain.hibernate.HibernateBodyType;
import com.zemelya.domain.hibernate.HibernateBrand;
import com.zemelya.domain.hibernate.HibernateCar;
import com.zemelya.domain.hibernate.HibernateDrivingLicense;
import com.zemelya.domain.hibernate.HibernateModel;
import com.zemelya.domain.hibernate.HibernateRentalAgreement;
import com.zemelya.domain.hibernate.HibernateUser;
import com.zemelya.service.bodyType.BodyTypeService;
import com.zemelya.service.brand.BrandService;
import com.zemelya.service.car.CarService;
import com.zemelya.service.drivingLicense.DrivingLicenseService;
import com.zemelya.service.model.ModelService;
import com.zemelya.service.rentalAgreement.RentalAgreementService;
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
import org.springframework.data.web.PageableDefault;
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
@Tag(name = "Administrator/Moderator controller")
public class AdminController {

  private final UserService userService;

  private final ConversionService conversionService;

  private final BodyTypeService bodyTypeService;

  private final BrandService brandService;

  private final DrivingLicenseService drivingLicenseService;

  private final ModelService modelService;

  private final CarService carService;

  private final RentalAgreementService rentalAgreementService;

  private Long userId;

  private Integer bodyTypeId;

  private Integer brandId;

  private Long drivingLicenseId;

  private Integer modelId;

  private Long carId;

  private Long rentalAgreementId;

  @PostMapping("/users/update{id}")
  @Parameter(in = ParameterIn.HEADER, name = "X-Auth-Token", required = true)
  @Transactional
  @ResponseStatus(HttpStatus.OK)
  @RequestBody(
      description = "This method allows update the user in DataBase.",
      required = true,
      content = @Content(schema = @Schema(implementation = UserChangeRequest.class)))
  public ResponseEntity<Object> updateUser(
      @PathVariable String id,
      @Valid @org.springframework.web.bind.annotation.RequestBody
          UserChangeRequest userChangeRequest) {

    try {
      userId = Long.parseLong(id);
    } catch (NumberFormatException e) {
      throw new NumberFormatException("Invalid user ID");
    }

    userChangeRequest.setId(userId);

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
  public ResponseEntity<Object> findAllPageable(
      @ParameterObject @PageableDefault(sort = "id", size = 10) Pageable pageable) {

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
  public ResponseEntity<Object> createBodyType(
      @Valid @org.springframework.web.bind.annotation.RequestBody
          BodyTypeCreateRequest bodyTypeCreateRequest) {

    HibernateBodyType hibernateBodyType =
        conversionService.convert(bodyTypeCreateRequest, HibernateBodyType.class);

    hibernateBodyType = bodyTypeService.create(hibernateBodyType);

    Map<String, Object> model = new HashMap<>();
    model.put("bodyType", bodyTypeService.findById(hibernateBodyType.getId()));

    return new ResponseEntity<>(model, HttpStatus.CREATED);
  }

  @PostMapping("/bodyTypes/update{id}")
  @Parameter(in = ParameterIn.HEADER, name = "X-Auth-Token", required = true)
  @Transactional
  @ResponseStatus(HttpStatus.OK)
  @RequestBody(
      description = "This method allows update the body type in DataBase.",
      required = true,
      content = @Content(schema = @Schema(implementation = BodyTypeChangeRequest.class)))
  public ResponseEntity<Object> updateBodyType(
      @PathVariable String id,
      @Valid @org.springframework.web.bind.annotation.RequestBody
          BodyTypeChangeRequest bodyTypeChangeRequest) {

    try {
      bodyTypeId = Integer.parseInt(id);
    } catch (NumberFormatException e) {
      throw new NumberFormatException("Invalid body type ID");
    }

    bodyTypeChangeRequest.setId(bodyTypeId);

    HibernateBodyType hibernateBodyType =
        conversionService.convert(bodyTypeChangeRequest, HibernateBodyType.class);

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
  public ResponseEntity<Object> createBrand(
      @Valid @org.springframework.web.bind.annotation.RequestBody
          BrandCreateRequest brandCreateRequest) {

    HibernateBrand hibernateBrand =
        conversionService.convert(brandCreateRequest, HibernateBrand.class);

    hibernateBrand = brandService.create(hibernateBrand);

    Map<String, Object> model = new HashMap<>();
    model.put("brand", brandService.findById(hibernateBrand.getId()));

    return new ResponseEntity<>(model, HttpStatus.CREATED);
  }

  @PostMapping("/brands/update{id}")
  @Parameter(in = ParameterIn.HEADER, name = "X-Auth-Token", required = true)
  @Transactional
  @ResponseStatus(HttpStatus.OK)
  @RequestBody(
      description = "This method allows update the body type in DataBase.",
      required = true,
      content = @Content(schema = @Schema(implementation = BrandChangeRequest.class)))
  public ResponseEntity<Object> updateBrand(
      @PathVariable String id,
      @Valid @org.springframework.web.bind.annotation.RequestBody
          BrandChangeRequest brandChangeRequest) {

    try {
      brandId = Integer.parseInt(id);
    } catch (NumberFormatException e) {
      throw new NumberFormatException("Invalid brand ID");
    }

    brandChangeRequest.setId(brandId);

    HibernateBrand hibernateBrand =
        conversionService.convert(brandChangeRequest, HibernateBrand.class);

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

  @PostMapping("/drivingLicenses/update{id}")
  @Parameter(in = ParameterIn.HEADER, name = "X-Auth-Token", required = true)
  @Transactional
  @ResponseStatus(HttpStatus.OK)
  @RequestBody(
      description = "This method allows update the user's driving license in DataBase.",
      required = true,
      content = @Content(schema = @Schema(implementation = DrivingLicenseChangeRequest.class)))
  public ResponseEntity<Object> updateDrivingLicense(
      @PathVariable String id,
      @Valid @org.springframework.web.bind.annotation.RequestBody
          DrivingLicenseChangeRequest drivingLicenseChangeRequest) {

    try {
      drivingLicenseId = Long.parseLong(id);
    } catch (NumberFormatException e) {
      throw new NumberFormatException("Invalid driving license ID");
    }

    drivingLicenseChangeRequest.setId(drivingLicenseId);

    HibernateDrivingLicense drivingLicense =
        conversionService.convert(drivingLicenseChangeRequest, HibernateDrivingLicense.class);

    drivingLicense = drivingLicenseService.update(drivingLicense);

    Map<String, Object> model = new HashMap<>();
    model.put("drivingLicense", drivingLicenseService.findById(drivingLicense.getId()));

    return new ResponseEntity<>(model, HttpStatus.OK);
  }

  @PostMapping("/drivingLicenses/delete{id}")
  @Parameter(in = ParameterIn.HEADER, name = "X-Auth-Token", required = true)
  @Transactional
  @Operation(
      description = "This method allows deactivate the user's driving license in DataBase by ID")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<Object> deleteDrivingLicense(@PathVariable String id) {

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
  public ResponseEntity<Object> findAllDrivingLicensesPageable(
      @ParameterObject @PageableDefault(sort = "id", size = 10) Pageable pageable) {

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

    try {
      drivingLicenseId = Long.parseLong(id);
    } catch (NumberFormatException e) {
      throw new NumberFormatException("Invalid driving license ID");
    }
    return new ResponseEntity<>(
        Collections.singletonMap("result", drivingLicenseService.findById(drivingLicenseId)),
        HttpStatus.OK);
  }

  @PostMapping("/models/create")
  @Parameter(in = ParameterIn.HEADER, name = "X-Auth-Token", required = true)
  @Transactional
  @ResponseStatus(HttpStatus.CREATED)
  @RequestBody(
      description = "This method allows create a new model in DataBase.",
      required = true,
      content = @Content(schema = @Schema(implementation = ModelCreateRequest.class)))
  public ResponseEntity<Object> createModel(
      @Valid @org.springframework.web.bind.annotation.RequestBody
          ModelCreateRequest modelCreateRequest) {

    HibernateModel hibernateModel =
        conversionService.convert(modelCreateRequest, HibernateModel.class);

    hibernateModel = modelService.create(hibernateModel);

    Map<String, Object> model = new HashMap<>();
    model.put("model", modelService.findById(hibernateModel.getId()));

    return new ResponseEntity<>(model, HttpStatus.CREATED);
  }

  @PostMapping("/models/update{id}")
  @Parameter(in = ParameterIn.HEADER, name = "X-Auth-Token", required = true)
  @Transactional
  @ResponseStatus(HttpStatus.OK)
  @RequestBody(
      description = "This method allows update the model in DataBase.",
      required = true,
      content = @Content(schema = @Schema(implementation = ModelChangeRequest.class)))
  public ResponseEntity<Object> updateModel(
      @PathVariable String id,
      @Valid @org.springframework.web.bind.annotation.RequestBody
          ModelChangeRequest modelChangeRequest) {

    try {
      modelId = Integer.parseInt(id);
    } catch (NumberFormatException e) {
      throw new NumberFormatException("Invalid model ID");
    }

    modelChangeRequest.setId(modelId);

    HibernateModel hibernateModel =
        conversionService.convert(modelChangeRequest, HibernateModel.class);

    hibernateModel = modelService.update(hibernateModel);

    Map<String, Object> model = new HashMap<>();
    model.put("model", modelService.findById(hibernateModel.getId()));

    return new ResponseEntity<>(model, HttpStatus.OK);
  }

  @PostMapping("/models/delete{id}")
  @Parameter(in = ParameterIn.HEADER, name = "X-Auth-Token", required = true)
  @Transactional
  @Operation(description = "This method allows deactivate the model in DataBase")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<Object> deleteModel(@PathVariable String id) {

    try {
      modelId = Integer.parseInt(id);
    } catch (NumberFormatException e) {
      throw new NumberFormatException("Invalid model ID");
    }

    HibernateModel hibernateModel = modelService.delete(modelId);

    Map<String, Object> model = new HashMap<>();
    model.put("model", modelService.findById(hibernateModel.getId()));

    return new ResponseEntity<>(model, HttpStatus.OK);
  }

  @GetMapping("/models/findAll")
  @Parameter(in = ParameterIn.HEADER, name = "X-Auth-Token", required = true)
  public ResponseEntity<Object> findAllModels() {

    return new ResponseEntity<>(modelService.findAll(), HttpStatus.OK);
  }

  @PostMapping("/cars/create")
  @Parameter(in = ParameterIn.HEADER, name = "X-Auth-Token", required = true)
  @Transactional
  @ResponseStatus(HttpStatus.CREATED)
  @RequestBody(
      description = "This method allows create a new car in DataBase.",
      required = true,
      content = @Content(schema = @Schema(implementation = CarCreateRequest.class)))
  public ResponseEntity<Object> createCar(
      @Valid @org.springframework.web.bind.annotation.RequestBody
          CarCreateRequest carCreateRequest) {

    HibernateCar hibernateCar = conversionService.convert(carCreateRequest, HibernateCar.class);

    hibernateCar = carService.create(hibernateCar);

    Map<String, Object> model = new HashMap<>();
    model.put("car", carService.findById(hibernateCar.getId()));

    return new ResponseEntity<>(model, HttpStatus.CREATED);
  }

  @PostMapping("/cars/update{id}")
  @Parameter(in = ParameterIn.HEADER, name = "X-Auth-Token", required = true)
  @Transactional
  @ResponseStatus(HttpStatus.OK)
  @RequestBody(
      description = "This method allows update the car in DataBase.",
      required = true,
      content = @Content(schema = @Schema(implementation = CarChangeRequest.class)))
  public ResponseEntity<Object> updateCar(
      @PathVariable String id,
      @Valid @org.springframework.web.bind.annotation.RequestBody
          CarChangeRequest carChangeRequest) {

    try {
      carId = Long.parseLong(id);
    } catch (NumberFormatException e) {
      throw new NumberFormatException("Invalid car ID");
    }

    carChangeRequest.setId(carId);

    HibernateCar hibernateCar = conversionService.convert(carChangeRequest, HibernateCar.class);

    hibernateCar = carService.update(hibernateCar);

    Map<String, Object> model = new HashMap<>();
    model.put("car", carService.findById(hibernateCar.getId()));

    return new ResponseEntity<>(model, HttpStatus.OK);
  }

  @PostMapping("/cars/delete{id}")
  @Parameter(in = ParameterIn.HEADER, name = "X-Auth-Token", required = true)
  @Transactional
  @Operation(description = "This method allows deactivate the car in DataBase")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<Object> deleteCar(@PathVariable String id) {

    try {
      carId = Long.parseLong(id);
    } catch (NumberFormatException e) {
      throw new NumberFormatException("Invalid car ID");
    }
    HibernateCar hibernateCar = carService.delete(carId);

    Map<String, Object> model = new HashMap<>();
    model.put("car", carService.findById(hibernateCar.getId()));

    return new ResponseEntity<>(model, HttpStatus.OK);
  }

  @GetMapping("/cars/findAll")
  @Parameter(in = ParameterIn.HEADER, name = "X-Auth-Token", required = true)
  public ResponseEntity<Object> findAllCars() {

    return new ResponseEntity<>(carService.findAll(), HttpStatus.OK);
  }

  @PostMapping("/rentalAgreements/create")
  @Parameter(in = ParameterIn.HEADER, name = "X-Auth-Token", required = true)
  @Transactional
  @ResponseStatus(HttpStatus.CREATED)
  @RequestBody(
      description = "This method allows create a new rental agreement in DataBase.",
      required = true,
      content = @Content(schema = @Schema(implementation = RentalAgreementCreateRequest.class)))
  public ResponseEntity<Object> createRentalAgreement(
      @Valid @org.springframework.web.bind.annotation.RequestBody
          RentalAgreementCreateRequest rentalAgreementCreateRequest) {

    HibernateRentalAgreement rentalAgreement =
        conversionService.convert(rentalAgreementCreateRequest, HibernateRentalAgreement.class);

    rentalAgreement = rentalAgreementService.create(rentalAgreement);

    Map<String, Object> model = new HashMap<>();
    model.put("rental agreement", rentalAgreementService.findById(rentalAgreement.getId()));

    return new ResponseEntity<>(model, HttpStatus.CREATED);
  }

  @PostMapping("/rentalAgreements/update{id}")
  @Parameter(in = ParameterIn.HEADER, name = "X-Auth-Token", required = true)
  @Transactional
  @ResponseStatus(HttpStatus.OK)
  @RequestBody(
      description = "This method allows update the rental agreement in DataBase.",
      required = true,
      content = @Content(schema = @Schema(implementation = RentalAgreementChangeRequest.class)))
  public ResponseEntity<Object> updateRentalAgreement(@PathVariable String id,
      @Valid @org.springframework.web.bind.annotation.RequestBody
          RentalAgreementChangeRequest rentalAgreementChangeRequest) {

    try {
      rentalAgreementId = Long.parseLong(id);
    } catch (NumberFormatException e) {
      throw new NumberFormatException("Invalid rental agreement ID");
    }

    rentalAgreementChangeRequest.setId(rentalAgreementId);

    HibernateRentalAgreement rentalAgreement =
        conversionService.convert(rentalAgreementChangeRequest, HibernateRentalAgreement.class);

    rentalAgreement = rentalAgreementService.update(rentalAgreement);

    Map<String, Object> model = new HashMap<>();
    model.put("rentalAgreement", rentalAgreementService.findById(rentalAgreement.getId()));

    return new ResponseEntity<>(model, HttpStatus.OK);
  }

  @GetMapping("/rentalAgreements/findAll")
  @Parameter(in = ParameterIn.HEADER, name = "X-Auth-Token", required = true)
  public ResponseEntity<Object> findAllRentalAgreements() {

    return new ResponseEntity<>(rentalAgreementService.findAll(), HttpStatus.OK);
  }

  @GetMapping("/rentalAgreements/findById{id}")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<Object> rentalAgreementServiceFindByIdRentalAgreement(
      @PathVariable String id) {

    try {
      rentalAgreementId = Long.parseLong(id);
    } catch (NumberFormatException e) {
      throw new NumberFormatException("Invalid rental agreement ID");
    }

    return new ResponseEntity<>(
        Collections.singletonMap("result", rentalAgreementService.findById(rentalAgreementId)),
        HttpStatus.OK);
  }

  @GetMapping("/rentalAgreements/findAllPageable")
  @Parameter(
      in = ParameterIn.QUERY,
      description =
          "Sorting criteria in the format: property(,asc|desc). "
              + "Default sort order is ascending. "
              + "Multiple sort criteria are supported.",
      name = "sort",
      array = @ArraySchema(schema = @Schema(type = "string")))
  public ResponseEntity<Object> rentalAgreementServiceFFindAllPageable(
      @ParameterObject @PageableDefault(sort = "id", size = 10) Pageable pageable) {

    return new ResponseEntity<>(rentalAgreementService.findAll(pageable), HttpStatus.OK);
  }
}
