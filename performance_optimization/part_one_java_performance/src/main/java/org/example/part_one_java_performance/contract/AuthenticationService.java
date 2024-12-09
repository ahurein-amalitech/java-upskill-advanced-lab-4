package org.example.part_one_java_performance.contract;

import org.example.part_one_java_performance.dto.CreateUserDto;
import org.example.part_one_java_performance.dto.SignInDto;
import org.example.part_one_java_performance.dto.SuccessfulLoginResponseDto;

public interface AuthenticationService {
    SuccessfulLoginResponseDto register(CreateUserDto request);
    SuccessfulLoginResponseDto authenticate(SignInDto request);
}
