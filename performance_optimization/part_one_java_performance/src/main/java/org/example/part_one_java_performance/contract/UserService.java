package org.example.part_one_java_performance.contract;

import org.example.part_one_java_performance.dto.UserReadDto;
import org.example.part_one_java_performance.dto.UserUpdateDto;

import java.util.List;

public interface UserService {
    List<UserReadDto> getAllUsers();
    UserReadDto getUserById(int id);
    UserReadDto updateUser(int id, UserUpdateDto userUpdateDto);
    void deleteUser(int id);
}
