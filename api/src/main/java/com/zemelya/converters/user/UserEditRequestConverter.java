package com.zemelya.converters.user;

import com.zemelya.controller.request.user.UserChangeRequest;
import com.zemelya.domain.hibernate.HibernateUser;
import javax.persistence.EntityNotFoundException;
import com.zemelya.repository.user.UserSpringDataRepository;
import org.springframework.stereotype.Component;

@Component
public class UserEditRequestConverter extends UserBaseConverter<UserChangeRequest, HibernateUser> {

    private final UserSpringDataRepository userRepository;

    public UserEditRequestConverter(UserSpringDataRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public HibernateUser convert(UserChangeRequest request) {

        HibernateUser hibernateUser = userRepository.findById(request.getId()).orElseThrow(EntityNotFoundException::new);
        return doConvert(hibernateUser, request);
    }
}