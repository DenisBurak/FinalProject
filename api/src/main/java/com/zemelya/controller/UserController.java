package com.zemelya.controller;

import com.zemelya.controller.request.UserChangeRequest;
import com.zemelya.controller.request.UserCreateRequest;
import com.zemelya.domain.hibernate.HibernateUser;
import com.zemelya.repository.role.RoleSpringDataRepository;
import com.zemelya.repository.user.UserSpringDataRepository;
import com.zemelya.security.util.PrincipalUtil;
import com.zemelya.service.user.UserServiceImpl;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest/data/users")
public class UserController {

  private final UserSpringDataRepository repository;

  private final UserServiceImpl service;

  public final ConversionService conversionService;

  @GetMapping("/findById{id}")
  @Parameter(in = ParameterIn.HEADER,
          name = "X-Auth-Token",
          required = true)
  @ResponseStatus(HttpStatus.OK)
  public HibernateUser findById(Principal principal, @PathVariable Long id) throws AuthenticationException {
    String username = PrincipalUtil.getUsername(principal);
    Optional<HibernateUser> result = repository.findByCredentialsLogin(username);

    if (result.isPresent()){
      return service.findById(id);
    }
    else{
      throw new AuthenticationException("User is not authenticate");
    }
  }

  @GetMapping("/profile")
  @Parameter(in = ParameterIn.HEADER,
          name = "X-Auth-Token",
          required = true)
  @ResponseStatus(HttpStatus.OK)
  public HibernateUser showProfile(Principal principal) throws AuthenticationException {
    String username = PrincipalUtil.getUsername(principal);
    Optional<HibernateUser> result = repository.findByCredentialsLogin(username);

    if (result.isPresent()){
      return service.findById(result.get().getId());
    }
    else{
      throw new AuthenticationException("User is not authenticate");
    }
  }

  @GetMapping("/findAll")
  @Parameter(in = ParameterIn.QUERY,
          description = "Sorting criteria in the format: property(,asc|desc). " +
                  "Default sort order is ascending. " +
                  "Multiple sort criteria are supported.",
          name = "sort",
          //required = true,
          array = @ArraySchema(schema = @Schema(type = "string")))
  public ResponseEntity<Object> findAll(@ParameterObject Pageable pageable) {

    return new ResponseEntity<>(service.findAll(pageable), HttpStatus.OK);

  }

  @PostMapping("/create")
  @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, timeout = 100, rollbackFor = Exception.class)
  @ResponseStatus(HttpStatus.CREATED)
  @RequestBody(
          description = "This method allows create a new user in DataBase.",
          required = true,
          content = @Content(schema = @Schema(implementation = UserCreateRequest.class))
  )
  public ResponseEntity<Object> createUser(@org.springframework.web.bind.annotation.RequestBody UserCreateRequest userCreateRequest) {

    HibernateUser hibernateUser = conversionService.convert(userCreateRequest, HibernateUser.class);

    hibernateUser = service.create(hibernateUser);

    Map<String, Object> model = new HashMap<>();
    model.put("user", service.findById(hibernateUser.getId()));

    return new ResponseEntity<>(model, HttpStatus.CREATED);
  }

  @PostMapping("/update")
  @Transactional
  @ResponseStatus(HttpStatus.OK)
  @RequestBody(
          description = "This method allows update a new user in DataBase.",
          required = true,
          content = @Content(schema = @Schema(implementation = UserCreateRequest.class),
                  mediaType = MediaType.APPLICATION_JSON_VALUE)
  )
  public HibernateUser updateUser(@org.springframework.web.bind.annotation.RequestBody UserChangeRequest userChangeRequest) {

    HibernateUser hibernateUser = conversionService.convert(userChangeRequest, HibernateUser.class);

    hibernateUser = service.update(hibernateUser);

    return hibernateUser;
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
