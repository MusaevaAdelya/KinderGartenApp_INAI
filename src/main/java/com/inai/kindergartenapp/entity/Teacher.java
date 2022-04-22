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
@NoArgsConstructor
@AllArgsConstructor
public class Teacher{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String fullname;
    private String email;
    private String password;
    private String picture;

    @Column(name="account_type")
    private String accountType;


}
