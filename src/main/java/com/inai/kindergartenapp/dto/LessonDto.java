package com.inai.kindergartenapp.dto;

import com.inai.kindergartenapp.entity.Lesson;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LessonDto {
    public static LessonDto from(Lesson lesson){
        return LessonDto.builder()
                .id(lesson.getId())
                .startTime(lesson.getStartTime())
                .endTime(lesson.getEndTime())
                .date(lesson.getDate())
                .subject(SubjectDto.from(lesson.getSubject()))
                .build();
    }

    private Long id;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDate date;
    private SubjectDto subject;
}
