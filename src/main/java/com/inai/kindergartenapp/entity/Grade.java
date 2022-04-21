package com.inai.kindergartenapp.entity;

import lombok.*;

import javax.persistence.*;


@Entity
@Table(name="grades")
@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Grade {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="subject_id")
    private Subject subject;

    @ManyToOne
    @JoinColumn(name="student_id")
    private Student student;

    @Column(name="first_grade")
    private Double firstGrade;

    @Column(name="second_grade")
    private Double secondGrade;

    @Column(name="third_grade")
    private Double thirdGrade;
}
