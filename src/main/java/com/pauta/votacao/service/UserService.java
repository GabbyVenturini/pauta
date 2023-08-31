package com.pauta.votacao.service;

import com.pauta.votacao.model.Usuario;
import com.pauta.votacao.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<Usuario> buscarUsuario(Long id) {
    return userRepository.findById(id);
    }
}
