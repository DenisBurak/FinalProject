package com.zemelya.controller;

import com.zemelya.controller.request.UserCreateRequest;
import com.zemelya.domain.hibernate.HibernateUser;
import com.zemelya.repository.role.RoleSpringDataRepository;
import com.zemelya.repository.user.UserSpringDataRepository;
import com.zemelya.service.UserService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest/data/users")
public class UserController {

  private final UserSpringDataRepository repository;

  private final RoleSpringDataRepository roleRepository;

  private final UserService service;

  @GetMapping("/findById{id}")
  @ResponseStatus(HttpStatus.OK)
  public HibernateUser findById(@PathVariable Long id){
     Optional<HibernateUser> result = repository.findById(id);
     if (result.isPresent()){
       return result.get();
     }

    return null;

  }

  @GetMapping("/showAllUsers")
  @Parameter(in = ParameterIn.QUERY,
          description = "Sorting criteria in the format: property(,asc|desc). " +
                  "Default sort order is ascending. " +
                  "Multiple sort criteria are supported.",
          name = "sort",
          required = true,
          array = @ArraySchema(schema = @Schema(type = "string")))
  public ResponseEntity<Object> showAllUsers(@ParameterObject Pageable pageable) {

    System.out.println("SOMETHING");
    return new ResponseEntity<>(repository.findAll(pageable), HttpStatus.OK);

  }


  @PostMapping("/createUser")
  @Transactional
  @ResponseStatus(HttpStatus.CREATED)
  @RequestBody(
          description = "This method allows create a new user in DataBase.",
          required = true,
          content = @Content(schema = @Schema(implementation = UserCreateRequest.class),
                  mediaType = MediaType.APPLICATION_JSON_VALUE)
  )
  public HibernateUser savingUser(@org.springframework.web.bind.annotation.RequestBody UserCreateRequest userCreateRequest) {

    HibernateUser hibernateUser = service.createUser(userCreateRequest);

    return repository.save(hibernateUser);
  }

//  @PutMapping("/{id}")
//  @ResponseStatus(HttpStatus.OK)
//  public HibernateUser updateUser(
//      @PathVariable Long id, @RequestBody UserCreateRequest userCreateRequest) {
//
//    Optional<HibernateUser> searchResult = repository.findById(id);
//
//    if (searchResult.isPresent()) {
//      // converters
//      HibernateUser user = searchResult.get();
//      user.setUserName(userCreateRequest.getUserName());
//      user.setSurname(userCreateRequest.getSurname());
//      user.setBirth(userCreateRequest.getBirthDate());
//      user.setEmail(userCreateRequest.getEmail());
//      user.setModificationDate(new Timestamp(System.currentTimeMillis()));
//
//      user.setCredentials(
//          new Credentials(userCreateRequest.getLogin(), userCreateRequest.getPassword()));
//
//      // user.setRoles(Collections.singleton(new HibernateRole("ROLE_ADMIN", user)));
//      //        user.setRole(new HibernateRole(SystemRoles.ROLE_ADMIN, user));
//    }
//
//    return repository.update(user);
//  }
}
