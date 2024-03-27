package com.schedule.vote.service;

import com.schedule.vote.dto.user.InUser;
import com.schedule.vote.dto.user.OutUser;
import com.schedule.vote.exceptions.BadRequestException;
import com.schedule.vote.mapper.UserMapper;
import com.schedule.vote.model.User;
import com.schedule.vote.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public User getUser(Long id) {
        var user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new ObjectNotFoundException(id, User.class.getSimpleName());
        }
    }

    public OutUser createUser(InUser inUser) {
        var user = userMapper.transformaInUserParaUser(inUser);

        if (!user.getName().isEmpty()) {
            var userSaved = userRepository.save(user);

            return userMapper.transformaUserParaOutUser(userSaved);
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
        var users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new BadRequestException("Nenhum usuário encontrado na base de dados");
        }
        return users;
    }
}
