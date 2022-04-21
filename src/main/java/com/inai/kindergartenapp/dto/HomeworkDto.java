package com.inai.kindergartenapp.dto;

import com.inai.kindergartenapp.entity.Subject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HomeworkDto {
    private Long id;
    private String task;
    private LocalDate date;
    private Subject subject;
}
