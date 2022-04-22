package com.inai.kindergartenapp.service;

import com.inai.kindergartenapp.dto.DirectorDto;
import com.inai.kindergartenapp.dto.StudentDto;
import com.inai.kindergartenapp.entity.Director;
import com.inai.kindergartenapp.entity.Grade;
import com.inai.kindergartenapp.entity.Student;
import com.inai.kindergartenapp.repository.GradeRepository;
import com.inai.kindergartenapp.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final GradeRepository gradeRepository;

    public StudentDto findByEmail(String userEmail){
        Student student=studentRepository.findByEmail(userEmail).orElseThrow();
        return StudentDto.from(student);
    }

    public HashMap<StudentDto,Double> studentGrades(){
        List<Student> allSudents=studentRepository.getAllByOrderByFullname();
        HashMap<StudentDto,Double> result=new HashMap<>();
        for(Student student:allSudents){
            Double averageGrade=0.0;
            for(Grade grade:gradeRepository.getAllByStudent(student)){
                averageGrade+=grade.getAverageGrade();
            }
            result.put(StudentDto.from(student),averageGrade);
        }

        return result;
    }
}
