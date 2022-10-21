package com.zemelya.controller;

import com.zemelya.domain.hibernate.HibernateUser;
import com.zemelya.repository.user.UserSpringDataRepository;
import com.zemelya.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.OperationNotSupportedException;
import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
@Tag(name = "Administator/Moderator controller")
public class AdminController {

  private final UserService userService;
  private final UserSpringDataRepository userRepository;

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
  public ResponseEntity<Object> findAll() {

    return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
  }

  @GetMapping("/users/findById{id}")
  @Parameter(in = ParameterIn.HEADER, name = "X-Auth-Token", required = true)
  @ResponseStatus(HttpStatus.OK)
  public HibernateUser findById(@PathVariable String id) {
    Long userId = 0l;
    try{
      userId = Long.parseLong(id);
    }
    catch (NumberFormatException e){
      throw new NumberFormatException("Invalid user ID");
    }
    return userService.findById(userId);
  }
}
