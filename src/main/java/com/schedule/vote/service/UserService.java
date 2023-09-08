package com.schedule.vote.service;

import com.schedule.vote.model.User;
import com.schedule.vote.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    public Optional<User> getUser(Long id) {

        return userRepository.findById(id);
    }

    public User createUser(User user){
        return userRepository.save(user);
    }
}
