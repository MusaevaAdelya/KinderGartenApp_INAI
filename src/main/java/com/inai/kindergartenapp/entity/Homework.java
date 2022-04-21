package com.inai.kindergartenapp.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name="homeworks")
@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Homework {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String task;
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name="subject_id")
    private Subject subject;
}


