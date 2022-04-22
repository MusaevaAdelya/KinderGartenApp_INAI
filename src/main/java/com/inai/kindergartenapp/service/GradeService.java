package com.inai.kindergartenapp.service;

import com.inai.kindergartenapp.dto.GradeDto;
import com.inai.kindergartenapp.dto.SubjectDto;
import com.inai.kindergartenapp.entity.Subject;
import com.inai.kindergartenapp.repository.GradeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GradeService {
    private final GradeRepository gradeRepository;


    public List<GradeDto> getBySubject(Subject subject) {
        return gradeRepository.getAllBySubject(subject).stream().map(g -> GradeDto.from(g)).collect(Collectors.toList());
    }


}
