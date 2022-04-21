package com.inai.kindergartenapp.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name="attendances")
@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Attendance {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;

    @ManyToMany
    @JoinColumn(name="student_id")
    private List<Student> student;
    private Boolean present;

    @ManyToOne
    @JoinColumn(name="subject_id")
    private Subject subject;
}
