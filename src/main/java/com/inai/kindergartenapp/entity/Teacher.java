package com.inai.kindergartenapp.entity;


import com.inai.kindergartenapp.enums.AccountType;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="teachers")
@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;

    @Column(name="account_type")
    @Builder.Default
    private String accountType= AccountType.TEACHER.getAccountType();
}
