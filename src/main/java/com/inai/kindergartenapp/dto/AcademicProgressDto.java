package com.inai.kindergartenapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.HashMap;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AcademicProgressDto {
    private StudentDto student;
    private HashMap<SubjectDto,Double> progress;
}
