package com.zemelya.controller;

import com.zemelya.controller.request.UserCreateRequest;
import com.zemelya.domain.Credentials;
import com.zemelya.domain.hibernate.HibernateUser;
import com.zemelya.repository.role.RoleSpringDataRepository;
import com.zemelya.repository.user.UserSpringDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest/data/users")
public class UserController {

  private final UserSpringDataRepository repository;

  private final RoleSpringDataRepository roleRepository;

  public final ConversionService conversionService;

  @GetMapping("/showAllUsers")
  public ResponseEntity<Object> showAllUsers() {

    return new ResponseEntity<>(
        Collections.singletonMap("result", repository.findAll(PageRequest.of(0, 10))),
        HttpStatus.OK);
  }

  @PostMapping
  @Transactional
  @ResponseStatus(HttpStatus.CREATED)
  public HibernateUser savingUser(@RequestBody UserCreateRequest userCreateRequest) {

    HibernateUser hibernateUser = conversionService.convert(userCreateRequest, HibernateUser.class);

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
