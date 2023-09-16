package com.schedule.vote.service;

import com.schedule.vote.model.User;
import com.schedule.vote.repository.UserRepository;
import org.springframework.data.jpa.domain.AbstractPersistable_;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new RuntimeException("Usuario nao existe!");
        }
    }

    public User createUser(User user) {
        if (!user.getName().isEmpty()) {
            return userRepository.save(user);
        } else {
            throw new RuntimeException("Usuário inválido.");
        }
    }

    public User updateUser(Long id, User updateUSer) {
        Optional<User> updateUser = userRepository.findById(id);
        if (updateUser.isPresent()) {
            return updateUSer;
        }
        if(!updateUser.isEmpty()) {
            return updateUSer;
        }else {
            throw new RuntimeException("Usuário com ID" + id + "nao encontrado.");
        }
    }
}
