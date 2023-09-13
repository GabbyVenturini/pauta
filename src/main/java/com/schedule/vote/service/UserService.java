package com.schedule.vote.service;

import com.schedule.vote.model.User;
import com.schedule.vote.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> getUser(Long id) {
        return userRepository.findById(id);
    }

    public User createUser(User user) {
        if (!user.getName().isEmpty()) {
            return userRepository.save(user);
        } else {
            throw new RuntimeException("Usuário inválido.");
        }
    }
}