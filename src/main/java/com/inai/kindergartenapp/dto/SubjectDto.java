package com.inai.kindergartenapp.dto;

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
public class SubjectDto {
    public static SubjectDto from(Subject subject){
        return builder()
                .id(subject.getId())
                .name(subject.getName())
                .code(subject.getCode())
                .teacher(TeacherDto.from(subject.getTeacher()))
                .students(subject.getStudents().stream().map(s->StudentDto.from(s)).collect(Collectors.toList()))
                .build();
    }
    private Long id;
    private String name;
    private String  code;
    private TeacherDto teacher;
    private List<StudentDto> students;
}
