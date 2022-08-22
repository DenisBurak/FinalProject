package com.zemelya.service.role;

import com.zemelya.domain.Role;
import com.zemelya.repository.role.RoleRepositoryInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepositoryInterface roleRepository;

    @Override
    public Role findById(Integer id) {
        return roleRepository.findById(id);
    }

    @Override
    public Optional<Role> findOne(Integer id) {
        return roleRepository.findOne(id);
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public List<Role> findAll(int limit, int offset) {
        return roleRepository.findAll(limit, offset);
    }

    @Override
    public Role create(Role object) {
        return roleRepository.create(object);
    }

    @Override
    public Role update(Role object) {
        return roleRepository.update(object);
    }

    @Override
    public Integer delete(Integer id) {
        return roleRepository.delete(id);
    }
}
