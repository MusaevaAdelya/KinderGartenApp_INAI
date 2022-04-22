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
    @Builder.Default
    private Integer firstGrade=0;

    @Column(name="second_grade")
    @Builder.Default
    private Integer secondGrade=0;

    @Column(name="third_grade")
    @Builder.Default
    private Integer thirdGrade=0;

    public double getAverageGrade(){
        return ((firstGrade+secondGrade+thirdGrade)/3.0);
    }
}
