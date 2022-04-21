package com.inai.kindergartenapp.entity;


import lombok.*;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name="classrooms")
@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Classroom {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name="subject_id")
    private Subject subject;

    @ManyToMany
    @JoinColumn(name="student_id")
    private List<Student> students;
}