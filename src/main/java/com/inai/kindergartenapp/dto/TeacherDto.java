package com.inai.kindergartenapp.dto;

import com.inai.kindergartenapp.entity.Teacher;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor


public class TeacherDto {
    public static TeacherDto from(Teacher teacher){
        return builder()
                .id(teacher.getId())
                .fullname(teacher.getFullname())
                .email(teacher.getEmail())
                .accountType(teacher.getAccountType())
                .picture(teacher.getPicture())
                .password(teacher.getPassword())
                .build();
    }

    private Long id;
    private String fullname;
    private String email;
    private String password;
    private String accountType;
    private String picture;
}
