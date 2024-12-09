package org.example.part_one_java_performance.service;

import lombok.RequiredArgsConstructor;
import org.example.part_one_java_performance.contract.AuthenticationService;
import org.example.part_one_java_performance.contract.JwtService;
import org.example.part_one_java_performance.contract.UserRepository;
import org.example.part_one_java_performance.dto.CreateUserDto;
import org.example.part_one_java_performance.dto.SignInDto;
import org.example.part_one_java_performance.dto.SuccessfulLoginResponseDto;
import org.example.part_one_java_performance.entity.Role;
import org.example.part_one_java_performance.entity.User;
import org.example.part_one_java_performance.exceptions.AlreadyExistException;
import org.example.part_one_java_performance.exceptions.UserNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authManager;

    @Override
    public SuccessfulLoginResponseDto register(CreateUserDto request) {
        var userExist = repository.findByEmail(request.getEmail());
        if(userExist.isPresent()) throw new AlreadyExistException("User exist already");

        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();

        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return SuccessfulLoginResponseDto.builder()
                .token(jwtToken)
                .message("Account created successfully")
                .build();
    }

    @Override
    public SuccessfulLoginResponseDto authenticate(SignInDto request) {
        authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        User user = repository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return SuccessfulLoginResponseDto.builder()
                .token(jwtToken)
                .message("Login successful")
                .build();
    }
}
