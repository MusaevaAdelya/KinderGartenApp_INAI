package com.inai.kindergartenapp.dto;

import com.inai.kindergartenapp.entity.Teacher;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubjectDto {
    private Long id;
    private String name;
    private String  code;
    private Teacher teacher;
}
