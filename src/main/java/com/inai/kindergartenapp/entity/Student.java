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
public class Student {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;

    @Column(name="account_type")
    @Builder.Default
    private String accountType= AccountType.STUDENT.getAccountType();

//    @ManyToOne(optional = false)
//    private Classroom classrooms;
//
//    public Classroom getClassrooms() {
//        return classrooms;
//    }
//
//    public void setClassrooms(Classroom classrooms) {
//        this.classrooms = classrooms;
//    }
}


