package com.inai.kindergartenapp.dto;

import com.inai.kindergartenapp.entity.Student;
import com.inai.kindergartenapp.entity.Subject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClassroomDto {
    private Long id;
    private Subject subject;
    private List<Student> student;
    private Double firstGrade;
    private Double secondGrade;
    private Double thirdGrade;
}
