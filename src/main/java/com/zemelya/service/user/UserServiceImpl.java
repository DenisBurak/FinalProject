package com.zemelya.service.user;

import com.zemelya.repository.user.UserRepositoryInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepositoryInterface userRepository;

    @Override
    public User findById(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findOne(Integer id) {
        return userRepository.findOne(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public List<User> findAll(int limit, int offset) {
        return userRepository.findAll(limit, offset);
    }

    @Override
    public User create(User object) {
        return userRepository.create(object);
    }

    @Override
    public User update(User object) {
        return userRepository.update(object);
    }

    @Override
    public Integer delete(Integer id) {
        return userRepository.delete(id);
    }
}
