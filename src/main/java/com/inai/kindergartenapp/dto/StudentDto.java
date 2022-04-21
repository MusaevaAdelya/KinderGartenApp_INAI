package com.inai.kindergartenapp.dto;

import com.inai.kindergartenapp.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {
    public static StudentDto from(Student student){
        return builder()
                .id(student.getId())
                .fullname(student.getFullname())
                .email(student.getEmail())
                .accountType(student.getAccountType())
                .picture(student.getPicture())
                .build();
    }

    private Long id;
    private String fullname;
    private String email;
    private String password;
    private String accountType;
    private String picture;

}


