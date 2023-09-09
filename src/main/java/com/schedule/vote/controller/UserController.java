package com.schedule.vote.controller;

import com.schedule.vote.model.User;
import com.schedule.vote.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public Optional<User> getUser(@PathVariable Long id) {
        return userService.getUser(id);

    }

    @PostMapping("/insert")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }
}
