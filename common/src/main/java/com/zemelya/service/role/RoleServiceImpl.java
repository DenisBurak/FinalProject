package com.zemelya.service.role;

import com.zemelya.domain.hibernate.HibernateRole;
import com.zemelya.repository.role.RoleSpringDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

  private final RoleSpringDataRepository repository;

  @Override
  public HibernateRole create(HibernateRole hibernateRole) {

    return saveRole(hibernateRole);
  }

  @Override
  public HibernateRole delete(Integer roleId) {

    HibernateRole hibernateRole = findById(roleId);

    hibernateRole.setIsDeleted(true);
    repository.save(hibernateRole);

    return findById(roleId);
  }

  @Override
  public HibernateRole update(HibernateRole hibernateRole) {

    return saveRole(hibernateRole);
  }

  @Override
  public Page<HibernateRole> findAll(Pageable pageable) {
    return repository.findAll(pageable);
  }

  @Override
  public List<HibernateRole> findAll() {
    return repository.findAll();
  }

  @Override
  public List<Object[]> findOnlyRoles() {
    return repository.findOnlyRoles();
  }

  @Override
  public HibernateRole findById(Integer roleId) {
    Optional<HibernateRole> result = repository.findById(roleId);

    if (result.isPresent()) {
      return result.get();
    } else {
      throw new EntityNotFoundException(
          String.format("Role with this id \"%s\" is not found", roleId));
    }
  }

  private HibernateRole saveRole(HibernateRole hibernateRole) {
    return repository.save(hibernateRole);
  }
}
