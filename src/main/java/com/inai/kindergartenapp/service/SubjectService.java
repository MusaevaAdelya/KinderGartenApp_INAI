package com.inai.kindergartenapp.service;

import com.inai.kindergartenapp.dto.SubjectDto;
import com.inai.kindergartenapp.entity.Subject;
import com.inai.kindergartenapp.repository.SubjectRepository;
import com.inai.kindergartenapp.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubjectService {
    private final SubjectRepository subjectRepository;
    private final TeacherRepository teacherRepository;

    public List<SubjectDto> getSubjectsDto(){
        return subjectRepository.getAllBy().stream().map(s->SubjectDto.from(s)).collect(Collectors.toList());
    }

    public void createSubject(String subjectTitle,String teacherId){
        subjectRepository.save(Subject.builder()
                        .name(subjectTitle)
                        .teacher(teacherRepository.findById(Long.valueOf(teacherId)).orElseThrow())
                .build());
    }

    public void deleteSubjectById(String subjectId){
        subjectRepository.deleteById(Long.valueOf(subjectId));
    }

    public Subject findById(Long subjectId) {
        return subjectRepository.findById(subjectId).orElseThrow();
    }
}