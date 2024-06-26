package com.schedule.vote.service;

import com.schedule.vote.dto.user.InUser;
import com.schedule.vote.dto.user.OutUser;
import com.schedule.vote.exceptions.BadRequestException;
import com.schedule.vote.mapper.UserMapper;
import com.schedule.vote.model.User;
import com.schedule.vote.repository.UserRepository;
import org.hibernate.ObjectNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.BDDAssertions.then;
import static org.assertj.core.api.BDDAssertions.thenThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private UserMapper userMapper;
    @InjectMocks
    private UserService userService;

    @Test
    public void shouldReturnUser(){
        var user = mock(User.class);

        given(user.getId()).willReturn(1L);
        given(user.getName()).willReturn("Gabby");

        given(userRepository.findById(1L)).willReturn(Optional.of(user));

        var result = userService.getUser(user.getId());

        then(result.getId()).isEqualTo(1L);
        then(result.getName()).isEqualTo("Gabby");
    }

    @Test
    public void shouldCreateUser(){
        var inUser = mock(InUser.class);
        var outUser = mock(OutUser.class);
        var user = mock(User.class);

        given(outUser.getId()).willReturn(1L);
        given(outUser.getName()).willReturn("Gabby");
        given(user.getName()).willReturn("Gabby");
        given(userMapper.transformaInUserParaUser(inUser)).willReturn(user);
        given(userMapper.transformaUserParaOutUser(user)).willReturn(outUser);

        given(userRepository.save(user)).willReturn(user);

        var result = userService.createUser(inUser);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Gabby", result.getName());
    }

    @Test
    public void shouldUpdateUser(){
        var user = mock(User.class);

        given(user.getId()).willReturn(1L);
        given(user.getName()).willReturn("Gabby");

        var newUser = mock(User.class);
        given(user.getId()).willReturn(2L);
        given(user.getName()).willReturn("Gui");

        given(userRepository.save(user)).willReturn(user);
        given(userRepository.findById(user.getId())).willReturn(Optional.of(user));

        var result = userService.updateUser(2L, newUser);

        assertEquals(2L, result.getId());
        assertEquals("Gui", result.getName());
    }

    @Test
    public void shouldListUser() {
        var userOne = mock(User.class);

        given(userOne.getId()).willReturn(1L);
        given(userOne.getName()).willReturn("Gabby");

        var userTwo = mock(User.class);

        given(userTwo.getId()).willReturn(2L);
        given(userTwo.getName()).willReturn("Gui");

        given(userRepository.findAll()).willReturn(List.of(userOne, userTwo));

        var result = userService.findAll();

        assertEquals(1L, result.get(0).getId());
        assertEquals("Gabby", result.get(0).getName());

        assertEquals(2L, result.get(1).getId());
        assertEquals("Gui", result.get(1).getName());
    }

    @Test
    public void shouldGetErrorUser(){

        thenThrownBy(()-> userService.getUser(null))
                .isInstanceOf(ObjectNotFoundException.class);
    }

    @Test
    public void shouldCreateUserError(){
        var user = mock(User.class);
        var inUser = mock(InUser.class);

        given(user.getName()).willReturn("");
        given(userMapper.transformaInUserParaUser(inUser)).willReturn(user);

        thenThrownBy(()-> userService.createUser(inUser))
                .isInstanceOf(BadRequestException.class);
    }

    @Test
    public void shouldUpdateUserError(){

        thenThrownBy(()-> userService.updateUser(null, null))
                .isInstanceOf(ObjectNotFoundException.class);
    }
}
