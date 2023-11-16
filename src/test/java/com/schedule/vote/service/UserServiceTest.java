package com.schedule.vote.service;

import com.schedule.vote.model.User;
import com.schedule.vote.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

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
    public void shouldCreateuser(){
        var user = new User();
        user.setId(1L);
        user.setName("Gabby");

        assertNotNull(user);
        assertEquals(1L, user.getId());
        assertEquals("Gabby", user.getName());
    }
}
