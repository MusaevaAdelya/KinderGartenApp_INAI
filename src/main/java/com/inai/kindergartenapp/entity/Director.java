package com.inai.kindergartenapp.entity;


import com.inai.kindergartenapp.enums.AccountType;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="directors")
@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Director {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String fullname;

    private String email;
    private String password;
    private String picture;

    @Column(name="account_type")
    @Builder.Default
    private String accountType= AccountType.DIRECTOR.getAccountType();
}
