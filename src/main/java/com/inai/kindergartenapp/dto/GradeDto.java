package com.inai.kindergartenapp.dto;

import com.inai.kindergartenapp.entity.Grade;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GradeDto {
    public static GradeDto from(Grade grade){
        return builder()
                .id(grade.getId())
                .subject(SubjectDto.from(grade.getSubject()))
                .student(StudentDto.from(grade.getStudent()))
                .firstGrade(grade.getFirstGrade())
                .secondGrade(grade.getSecondGrade())
                .thirdGrade(grade.getThirdGrade())
                .build();
    }

    private Long id;
    private SubjectDto subject;
    private StudentDto student;
    private Integer firstGrade;
    private Integer secondGrade;
    private Integer thirdGrade;


    public double getAverageGrade(){
        return ((firstGrade+secondGrade+thirdGrade)/3.0);
    }
}
