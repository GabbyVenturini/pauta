package com.schedule.vote.service;

import com.schedule.vote.model.User;
import com.schedule.vote.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserService userService;

    @Test
    public void shouldReturnUser(){
        var user = new User();
        user.setId(1L);
        user.setName("Gabby");

        given(userRepository.findById(1L)).willReturn(Optional.of(user));

        var result = userService.getUser(user.getId());

        then(result.getId()).isEqualTo(1L);
        then(result.getName()).isEqualTo("Gabby");
    }

    @Test
    public void shouldCreateUser(){
        var user = new User();
        user.setId(1L);
        user.setName("Gabby");

        given(userRepository.save(user)).willReturn(user);

        User result = userService.createUser(user);

        assertNotNull(user);
        assertEquals(1L, result.getId());
        assertEquals("Gabby", result.getName());
    }

    @Test
    public void shouldUpdateUser(){
        var user = new User();
        user.setId(1L);
        user.setName("Gabby");

        var newUser = new User();
        newUser.setId(1L);
        newUser.setName("Gui");

        given(userRepository.save(user)).willReturn(user);
        given(userRepository.findById(user.getId())).willReturn(Optional.of(user));

        var result = userService.updateUser(1L, newUser);

        assertEquals(1L, result.getId());
        assertEquals("Gui", result.getName());
    }

    @Test
    public void shouldListUser(){
        var userOne = new User();
        userOne.setId(1L);
        userOne.setName("Gabby");

        var userTwo = new User();
        userTwo.setId(2L);
        userTwo.setName("Gui");

        given(userRepository.findAll()).willReturn(List.of(userOne, userTwo));

        var result = userService.findAll();

        assertEquals(result.get(0).getId(),1L);
        assertEquals(result.get(1).getId(), 2L);

    }
}
