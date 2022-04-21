package com.inai.kindergartenapp.dto;

import com.inai.kindergartenapp.entity.Homework;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HomeworkDto {
    public static HomeworkDto from(Homework homework){
        return builder()
                .id(homework.getId())
                .task(homework.getTask())
                .date(homework.getDate())
                .subject(SubjectDto.from(homework.getSubject()))
                .build();
    }

    private Long id;
    private String task;
    private LocalDate date;
    private SubjectDto subject;
}
