package com.inai.kindergartenapp.dto;

import com.inai.kindergartenapp.entity.Student;
import com.inai.kindergartenapp.entity.Subject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceDto {
    private Long id;
    private LocalDate date;
    private List<Student> student;
    private Boolean present;
    private Subject subject;
}
