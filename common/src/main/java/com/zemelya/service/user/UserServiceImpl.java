package com.zemelya.service.user;

import com.zemelya.domain.hibernate.HibernateRole;
import com.zemelya.domain.hibernate.HibernateUser;
import com.zemelya.repository.user.UserSpringDataRepository;
import com.zemelya.service.AdminService;
import com.zemelya.service.MailSenderCustom;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserSpringDataRepository repository;

  private final AdminService adminService;

  private final MailSenderCustom mailSender;

  @Override
  public HibernateUser create(HibernateUser hibernateUser) {

    final Integer DEFAULT_USER_ROLE_ID = 2;

    if (findByCredentialsLogin(hibernateUser.getCredentials().getLogin()).isPresent()) {
      throw new EntityExistsException(
          "User with login: " + hibernateUser.getCredentials().getLogin() + " is exists.");
    }

    hibernateUser = adminService.setRoles(hibernateUser, DEFAULT_USER_ROLE_ID);

    hibernateUser = repository.save(hibernateUser);

    if (!StringUtils.isEmpty(hibernateUser.getEmail())) {
      String massage =
          "Congratulation. You registrated in service for renting car.\n"
              + "Your login in system:"
              + hibernateUser.getCredentials().getLogin();
      mailSender.send(hibernateUser.getEmail(), "Registration in rental cars service.", massage);
    }

    for (HibernateRole updatedRole : hibernateUser.getRoles()) {
      repository.createRoleRow(hibernateUser.getId(), updatedRole.getId());
    }

    return hibernateUser;
  }

  @Override
  public HibernateUser delete(Long userId) {

    Optional<HibernateUser> result = repository.findById(userId);

    if (result.isPresent()) {
      HibernateUser hibernateUser = result.get();
      hibernateUser.setIsDeleted(true);
      repository.save(hibernateUser);

      return repository.findById(userId).get();
    } else {
      throw new EntityNotFoundException(
          String.format("User with this id \"%s\" is not found", userId));
    }
  }

  @Override
  public HibernateUser update(HibernateUser hibernateUser) {

    return repository.save(hibernateUser);
  }

  @Override
  public Page<HibernateUser> findAll(Pageable pageable) {
    return repository.findAll(pageable);
  }

  @Override
  public List<HibernateUser> findAll() {
    return repository.findAll();
  }

  public HibernateUser findById(Long userId) {
    Optional<HibernateUser> result = repository.findById(userId);

    if (result.isPresent()) {
      return result.get();
    } else {
      throw new EntityNotFoundException(
          String.format("User with this id \"%s\" not found", userId));
    }
  }

  @Override
  public Optional<HibernateUser> findByCredentialsLogin(String login) {
    return repository.findByCredentialsLogin(login);
  }
}
