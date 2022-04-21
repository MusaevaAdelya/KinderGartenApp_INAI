package com.inai.kindergartenapp.entity;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name="subjects")
@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Subject {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Builder.Default
    private String  code= UUID.randomUUID().toString();

    @ManyToOne
    @JoinColumn(name="teacher_id")
    private Teacher teacher;


}


