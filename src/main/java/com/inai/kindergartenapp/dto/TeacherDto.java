package com.inai.kindergartenapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor


public class TeacherDto {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String accountType;
}
