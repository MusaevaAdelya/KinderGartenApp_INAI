package com.inai.kindergartenapp.dto;

import com.inai.kindergartenapp.entity.Student;
import com.inai.kindergartenapp.entity.Subject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GradeDto {
    private Long id;
    private Subject subject;
    private Student student;
    private Double firstGrade;
    private Double secondGrade;
    private Double thirdGrade;
}
