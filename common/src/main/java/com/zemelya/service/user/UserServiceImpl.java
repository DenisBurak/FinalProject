package com.zemelya.service.user;

import com.zemelya.domain.hibernate.HibernateRole;
import com.zemelya.domain.hibernate.HibernateUser;
import com.zemelya.repository.user.UserSpringDataRepository;
import com.zemelya.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserSpringDataRepository userSpringDataRepository;

  public final AdminService adminService;

  @Transactional
  @Override
  public HibernateUser create(HibernateUser hibernateUser) {

    final Integer DEFAULT_USER_ROLE_ID = 2;

    hibernateUser = adminService.setRoles(hibernateUser, DEFAULT_USER_ROLE_ID);

    hibernateUser = userSpringDataRepository.save(hibernateUser);

    for (HibernateRole updatedRole : hibernateUser.getRoles()) {
      userSpringDataRepository.createRoleRow(hibernateUser.getId(), updatedRole.getId());
    }

    return hibernateUser;
  }

  @Transactional
  @Override
  public HibernateUser delete(Long userId) {

    Optional<HibernateUser> result = userSpringDataRepository.findById(userId);

    if (result.isPresent()) {
      HibernateUser hibernateUser = result.get();
      hibernateUser.setIsDeleted(true);
      userSpringDataRepository.save(hibernateUser);

      return userSpringDataRepository.findById(userId).get();
    } else {
      throw new EntityNotFoundException(
          String.format("User with this id \"%s\" not found", userId));
    }
  }

  @Transactional
  @Override
  public HibernateUser update(HibernateUser hibernateUser) {

    return userSpringDataRepository.save(hibernateUser);
  }

  @Override
  public Page<HibernateUser> findAll(Pageable pageable) {
    return userSpringDataRepository.findAll(pageable);
  }

  @Override
  public List<HibernateUser> findAll() {
    return userSpringDataRepository.findAll();
  }

  public HibernateUser findById(Long userId) {
    Optional<HibernateUser> result = userSpringDataRepository.findById(userId);

    if (result.isPresent()) {
      return result.get();
    } else {
      throw new EntityNotFoundException(
          String.format("User with this id \"%s\" not found", userId));
    }
  }
}
