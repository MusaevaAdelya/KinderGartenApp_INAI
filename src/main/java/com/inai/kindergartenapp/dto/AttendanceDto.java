package com.inai.kindergartenapp.dto;

import com.inai.kindergartenapp.entity.Attendance;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceDto {
    public static AttendanceDto from(Attendance attendance){
        return builder()
                .id(attendance.getId())
                .subject(SubjectDto.from(attendance.getSubject()))
                .students(attendance.getStudents().stream().map(s->StudentDto.from(s)).collect(Collectors.toList()))
                .date(attendance.getDate())
                .present(attendance.getPresent())
                .build();
    }

    private Long id;
    private LocalDate date;
    private List<StudentDto> students;
    private Boolean present;
    private SubjectDto subject;
}
