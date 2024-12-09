package com.example;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public String getUser(long id) {
        return "Ahurein";
    }

    @Override
    public List<String> getAllUsers() {
        return Arrays.asList("Ahurein", "Eben");
    }
}