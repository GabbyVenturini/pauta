package com.schedule.vote.service;

import com.schedule.vote.exceptions.BadRequestException;
import com.schedule.vote.model.User;
import com.schedule.vote.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new ObjectNotFoundException(id, User.class.getSimpleName());
        }
    }

    public User createUser(User user) {
        if (!user.getName().isEmpty()) {
            return userRepository.save(user);
        } else {
            throw new BadRequestException("Usuário inválido.");
        }
    }

    public User updateUser(Long id, User newUser) {
        Optional<User> updateUser = userRepository.findById(id);
        if (updateUser.isPresent()) {
            User user = updateUser.get();
            user.setName(newUser.getName());
            return userRepository.save(user);
        } else {
            throw new ObjectNotFoundException(id, User.class.getSimpleName());
        }
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}
