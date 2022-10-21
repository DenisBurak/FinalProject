package com.zemelya.service;

import com.zemelya.domain.hibernate.HibernateRole;
import com.zemelya.domain.hibernate.HibernateUser;
import com.zemelya.repository.role.RoleSpringDataRepository;
import com.zemelya.repository.user.UserSpringDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AdminService {

    public final UserSpringDataRepository userSpringDataRepository;

    public final RoleSpringDataRepository roleSpringDataRepository;

    public HibernateUser setRoles(HibernateUser user, Integer roleId){

        Set<HibernateRole> roles = user.getRoles();

        Set<HibernateRole> updatedRoles = new HashSet<>();

        if (!CollectionUtils.isEmpty(roles)) {
            updatedRoles.addAll(roles);
        }
        updatedRoles.add(roleSpringDataRepository.findById(roleId).get());

        user.setRoles(updatedRoles);

        return user;

    }
}
