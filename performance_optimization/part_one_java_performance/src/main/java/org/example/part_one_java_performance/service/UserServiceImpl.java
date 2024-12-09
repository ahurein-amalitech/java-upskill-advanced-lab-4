package org.example.part_one_java_performance.service;

import lombok.RequiredArgsConstructor;
import org.example.part_one_java_performance.contract.UserRepository;
import org.example.part_one_java_performance.contract.UserService;
import org.example.part_one_java_performance.dto.UserReadDto;
import org.example.part_one_java_performance.dto.UserUpdateDto;
import org.example.part_one_java_performance.entity.Role;
import org.example.part_one_java_performance.entity.User;
import org.example.part_one_java_performance.exceptions.UnauthorizedRequestException;
import org.example.part_one_java_performance.exceptions.UserNotFoundException;
import org.example.part_one_java_performance.utilities.UserDetailsUtility;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final UserDetailsUtility userDetailsUtility;

    @Override
    public List<UserReadDto> getAllUsers() {
        var users = userRepository.findAll();
        return users.stream()
                .map(user -> modelMapper.map(user, UserReadDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserReadDto getUserById(int id) {
        var user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        return this.modelMapper.map(user, UserReadDto.class);
    }

    @Override
    public UserReadDto updateUser(int id, UserUpdateDto userUpdateDto) {
        User existingUser = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        User user = userDetailsUtility.getCurrentUserDetails();

        if(user.getId() != id && user.getRole() != Role.Admin){
            throw new UnauthorizedRequestException();
        }
        this.modelMapper.map(userUpdateDto, existingUser);

        var updatedUser = userRepository.save(existingUser);
        return this.modelMapper.map(updatedUser, UserReadDto.class);
    }

    @Override
    public void deleteUser(int id) {
        var user = userRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException());
        userRepository.delete(user);
    }
}
