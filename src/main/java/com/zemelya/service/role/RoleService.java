package com.zemelya.service.role;

import java.util.List;
import java.util.Optional;

public interface RoleService {

    Role findById(Integer id);

    Optional<Role> findOne(Integer id);

    List<Role> findAll();

    List<Role> findAll(int limit, int offset);

    Role create(Role object);

    Role update(Role object);

    Integer delete(Integer id);
}
