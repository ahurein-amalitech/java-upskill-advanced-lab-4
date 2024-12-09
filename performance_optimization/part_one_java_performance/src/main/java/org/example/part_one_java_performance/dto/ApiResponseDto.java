package org.example.part_one_java_performance.dto;

import lombok.*;
import lombok.Data;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponseDto<T> {
    private String message;
}