package com.inai.kindergartenapp.dto;

import com.inai.kindergartenapp.entity.Director;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DirectorDto {
    public static DirectorDto from(Director director){
        return builder()
                .id(director.getId())
                .fullname(director.getFullname())
                .email(director.getEmail())
                .accountType(director.getAccountType())
                .picture(director.getPicture())
                .build();
    }

    private Long id;
    private String fullname;
    private String email;
    private String password;
    private String accountType;
    private String picture;
}
