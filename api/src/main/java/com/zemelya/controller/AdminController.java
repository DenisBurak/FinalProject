package com.zemelya.controller;

import com.zemelya.controller.request.role.RoleChangeRequest;
import com.zemelya.controller.request.role.RoleCreateRequest;
import com.zemelya.controller.request.user.UserChangeRequest;
import com.zemelya.domain.hibernate.HibernateRole;
import com.zemelya.domain.hibernate.HibernateUser;
import com.zemelya.security.util.PrincipalUtil;
import com.zemelya.service.role.RoleService;
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
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
@Tag(name = "Administator/Moderator controller")
public class AdminController {

  private final UserService userService;

  private final RoleService roleService;

  public final ConversionService conversionService;

  @PostMapping("/update")
  @Parameter(in = ParameterIn.HEADER, name = "X-Auth-Token", required = true)
  @Transactional
  @ResponseStatus(HttpStatus.OK)
  @RequestBody(
      description = "This method allows update the user in DataBase.",
      required = true,
      content = @Content(schema = @Schema(implementation = UserChangeRequest.class)))
  public ResponseEntity<Object> updateUser(
      @org.springframework.web.bind.annotation.RequestBody UserChangeRequest userChangeRequest) {

    HibernateUser hibernateUser = conversionService.convert(userChangeRequest, HibernateUser.class);

    hibernateUser = userService.update(hibernateUser);

    Map<String, Object> model = new HashMap<>();
    model.put("user", userService.findById(hibernateUser.getId()));

    return new ResponseEntity<>(model, HttpStatus.OK);
  }

  @PostMapping("/delete{id}")
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
  public ResponseEntity<Object> findAll() {

    return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
  }

  @GetMapping("/users/findById{id}")
  @Parameter(in = ParameterIn.HEADER, name = "X-Auth-Token", required = true)
  @ResponseStatus(HttpStatus.OK)
  public HibernateUser findById(@PathVariable String id) {
    Long userId = 0l;
    try {
      userId = Long.parseLong(id);
    } catch (NumberFormatException e) {
      throw new NumberFormatException("Invalid user ID");
    }
    return userService.findById(userId);
  }

  @PostMapping("/roles/create")
  @Parameter(in = ParameterIn.HEADER, name = "X-Auth-Token", required = true)
  @Transactional
  @ResponseStatus(HttpStatus.CREATED)
  @RequestBody(
      description = "This method allows create a new role in DataBase.",
      required = true,
      content = @Content(schema = @Schema(implementation = RoleCreateRequest.class)))
  public ResponseEntity<Object> createRole(
      @org.springframework.web.bind.annotation.RequestBody RoleCreateRequest roleCreateRequest) {

    HibernateRole hibernateRole = conversionService.convert(roleCreateRequest, HibernateRole.class);

    hibernateRole = roleService.create(hibernateRole);

    Map<String, Object> model = new HashMap<>();
    model.put("role", roleService.findById(hibernateRole.getId()));

    return new ResponseEntity<>(model, HttpStatus.CREATED);
  }

  @PostMapping("/roles/update")
  @Parameter(in = ParameterIn.HEADER, name = "X-Auth-Token", required = true)
  @Transactional
  @ResponseStatus(HttpStatus.OK)
  @RequestBody(
      description = "This method allows update the role in DataBase.",
      required = true,
      content = @Content(schema = @Schema(implementation = RoleChangeRequest.class)))
  public ResponseEntity<Object> updateRole(
      @org.springframework.web.bind.annotation.RequestBody RoleChangeRequest roleChangeRequest) {

    HibernateRole hibernateRole = conversionService.convert(roleChangeRequest, HibernateRole.class);

    hibernateRole = roleService.update(hibernateRole);

    Map<String, Object> model = new HashMap<>();
    model.put("role", roleService.findById(hibernateRole.getId()));

    return new ResponseEntity<>(model, HttpStatus.OK);
  }

  @PostMapping("/roles/delete{id}")
  @Parameter(in = ParameterIn.HEADER, name = "X-Auth-Token", required = true)
  @Transactional
  @Operation(description = "This method allows deactivate the role in DataBase")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<Object> deleteRole(@PathVariable String id) {

    Integer roleId = 0;
    try {
      roleId = Integer.parseInt(id);
    } catch (NumberFormatException e) {
      throw new NumberFormatException("Invalid role ID");
    }
    HibernateRole hibernateRole = roleService.delete(roleId);

    Map<String, Object> model = new HashMap<>();
    model.put("role", roleService.findById(hibernateRole.getId()));

    return new ResponseEntity<>(model, HttpStatus.OK);
  }
}
