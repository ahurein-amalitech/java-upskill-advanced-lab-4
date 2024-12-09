package org.example.part_one_java_performance.dto;

import lombok.*;
import org.example.part_one_java_performance.entity.Role;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateDto {
    private String password;
    private String firstName;
    private String lastName;
    private Role role;
}
