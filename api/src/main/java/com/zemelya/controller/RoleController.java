package com.zemelya.controller;

import com.zemelya.controller.request.role.RoleChangeRequest;
import com.zemelya.controller.request.role.RoleCreateRequest;
import com.zemelya.domain.hibernate.HibernateRole;
import com.zemelya.service.role.RoleService;
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
@RequestMapping("/admin/roles")
@Tag(name = "Role controller")
public class RoleController {

  private final RoleService service;

  public final ConversionService conversionService;

  @PostMapping()
  @Parameter(in = ParameterIn.HEADER, name = "X-Auth-Token", required = true)
  @Transactional
  @ResponseStatus(HttpStatus.CREATED)
  @RequestBody(
      description = "This method allows create a new role in DataBase.",
      required = true,
      content = @Content(schema = @Schema(implementation = RoleCreateRequest.class)))
  public ResponseEntity<Object> createRole(
      @Valid @org.springframework.web.bind.annotation.RequestBody
          RoleCreateRequest roleCreateRequest) {

    HibernateRole hibernateRole = conversionService.convert(roleCreateRequest, HibernateRole.class);

    hibernateRole = service.create(hibernateRole);

    Map<String, Object> model = new HashMap<>();
    model.put("role", service.findById(hibernateRole.getId()));

    return new ResponseEntity<>(model, HttpStatus.CREATED);
  }

  @PostMapping("/update")
  @Parameter(in = ParameterIn.HEADER, name = "X-Auth-Token", required = true)
  @Transactional
  @ResponseStatus(HttpStatus.OK)
  @RequestBody(
      description = "This method allows update the role in DataBase.",
      required = true,
      content = @Content(schema = @Schema(implementation = RoleChangeRequest.class)))
  public ResponseEntity<Object> updateRole(
      @Valid @org.springframework.web.bind.annotation.RequestBody
          RoleChangeRequest roleChangeRequest) {

    HibernateRole hibernateRole = conversionService.convert(roleChangeRequest, HibernateRole.class);

    hibernateRole = service.update(hibernateRole);

    Map<String, Object> model = new HashMap<>();
    model.put("role", service.findById(hibernateRole.getId()));

    return new ResponseEntity<>(model, HttpStatus.OK);
  }

  @PostMapping("/delete{id}")
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
    HibernateRole hibernateRole = service.delete(roleId);

    Map<String, Object> model = new HashMap<>();
    model.put("role", service.findById(hibernateRole.getId()));

    return new ResponseEntity<>(model, HttpStatus.OK);
  }

  @GetMapping("/findAllPageable")
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

    return new ResponseEntity<>(service.findAll(pageable), HttpStatus.OK);
  }

  @GetMapping("/findAll")
  @Parameter(in = ParameterIn.HEADER, name = "X-Auth-Token", required = true)
  public ResponseEntity<Object> findAll() {

    return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
  }

  @GetMapping("/showRoles")
  @Operation(description = "Shows roles without users")
  @Parameter(in = ParameterIn.HEADER, name = "X-Auth-Token", required = true)
  public ResponseEntity<Object> findOnlyRoles() {

    return new ResponseEntity<>(service.findOnlyRoles(), HttpStatus.OK);
  }

  @GetMapping("/findById{id}")
  @Parameter(in = ParameterIn.HEADER, name = "X-Auth-Token", required = true)
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<Object> findById(@PathVariable String id) {
    Integer roleId = 0;
    try {
      roleId = Integer.parseInt(id);
    } catch (NumberFormatException e) {
      throw new NumberFormatException("Invalid role ID");
    }

    return new ResponseEntity<>(
        Collections.singletonMap("result", service.findById(roleId)), HttpStatus.OK);
  }
}
