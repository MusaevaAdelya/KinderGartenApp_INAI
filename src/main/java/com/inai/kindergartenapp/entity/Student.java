package com.inai.kindergartenapp.entity;

import com.inai.kindergartenapp.enums.AccountType;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="students")
@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Student{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String fullname;
    private String email;
    private String password;

    @Builder.Default
    private String picture="anonymous.jpg";

    @Column(name="account_type")
    @Builder.Default
    private String accountType= AccountType.STUDENT.getAccountType();

}


