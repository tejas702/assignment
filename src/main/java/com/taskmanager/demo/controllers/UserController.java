package com.taskmanager.demo.controllers;

import com.taskmanager.demo.dtos.UserDto;
import com.taskmanager.demo.entities.UserEntity;
import com.taskmanager.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/add")
    public UserEntity addUsers() {
        return userService.addUsers();
    }

    @CrossOrigin(allowedHeaders = {"*", "Content-Type", "token", "authorization", "content-type", "accept", "Content type"}, origins = "*", originPatterns = "*")
    @PostMapping("/get-tasks")
    public List<String> getTasks(@RequestBody UserDto userDto) {
        return userService.getTasks(userDto);
    }
}
