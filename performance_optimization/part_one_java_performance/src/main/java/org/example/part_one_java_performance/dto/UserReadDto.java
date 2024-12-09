package org.example.part_one_java_performance.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.part_one_java_performance.entity.Role;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserReadDto {
    private int id;
    private String email;
    private String firstName;
    private String lastName;
    private Role role;
}
