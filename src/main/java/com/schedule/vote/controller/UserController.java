package com.schedule.vote.controller;

import com.schedule.vote.dto.user.InUser;
import com.schedule.vote.dto.user.OutUser;
import com.schedule.vote.model.User;
import com.schedule.vote.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @GetMapping("/users")
    public List<User> findAll() {
        return userService.findAll();
    }

    @PostMapping("/insert")
    public OutUser createUser(@RequestBody InUser inUser) {
        return userService.createUser(inUser);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User newUser){
        return userService.updateUser(id, newUser);
    }
}
