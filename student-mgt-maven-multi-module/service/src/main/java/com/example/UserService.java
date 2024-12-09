package com.example;

import java.util.List;

public interface UserService {
    String getUser(long id);
    List<String> getAllUsers();
}