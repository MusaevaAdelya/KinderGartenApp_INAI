package com.inai.kindergartenapp.dto;

import com.inai.kindergartenapp.entity.Classroom;
import com.inai.kindergartenapp.entity.Student;
import com.inai.kindergartenapp.entity.Subject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClassroomDto {
    public static ClassroomDto from(Classroom classroom){
        return builder()
                .id(classroom.getId())
                .subject(SubjectDto.from(classroom.getSubject()))
                .students(classroom.getStudents().stream().map(s->StudentDto.from(s)).collect(Collectors.toList()))
                .build();
    }

    private Long id;
    private SubjectDto subject;
    private List<StudentDto> students;
}
