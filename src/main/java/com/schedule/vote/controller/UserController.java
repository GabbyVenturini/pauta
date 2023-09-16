package com.schedule.vote.controller;

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

    @GetMapping("/Users")
    public List<User> findAll() {
        return userService.findAll();
    }

    @PostMapping("/insert")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PutMapping("/{id}")
    public User UserController(@PathVariable Long id, @RequestBody User updateUser){
        return userService.updateUser(id, updateUser);
    }
}
