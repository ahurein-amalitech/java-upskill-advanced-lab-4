package com.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/students")
public class UserController {
    private final UserService service;

    public UserController(UserService userService) {
        service = userService;
    }

    @GetMapping("{id}")
    public String getUser(@PathVariable long id){
        return this.service.getUser(id);
    }

    @GetMapping
    public List<String> getUsers(){
        return this.service.getAllUsers();
    }
}
