package com.schedule.vote.service;

import com.schedule.vote.exceptions.BadRequestException;
import com.schedule.vote.model.User;
import com.schedule.vote.repository.UserRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser(Long id) {
        var user = userRepository.findById(id);
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
        var updateUser = userRepository.findById(id);
        if (updateUser.isPresent()) {
            var user = updateUser.get();
            user.setName(newUser.getName());
            return userRepository.save(user);
        } else {
            throw new ObjectNotFoundException(id, User.class.getSimpleName());
        }
    }

    public List<User> findAll() {
        //TODO: IMPLEMENTAR LÓGICA PARA RETORNAR UM ERRO SE NÃO EXISTIR USUÁRIOS NA BASE DE DADOS.
        return userRepository.findAll();
    }
}
