package com.inai.kindergartenapp.entity;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class User {
    private String fullname;
    private String email;
    private String password;
    private String picture;

}
