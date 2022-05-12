package com.inai.kindergartenapp.service;

import com.inai.kindergartenapp.dto.GradeDto;
import com.inai.kindergartenapp.dto.RatingDto;
import com.inai.kindergartenapp.dto.StudentDto;
import com.inai.kindergartenapp.dto.SubjectDto;
import com.inai.kindergartenapp.entity.Grade;
import com.inai.kindergartenapp.entity.Student;
import com.inai.kindergartenapp.entity.Subject;
import com.inai.kindergartenapp.repository.GradeRepository;
import com.inai.kindergartenapp.repository.StudentRepository;
import com.inai.kindergartenapp.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GradeService {
    private final GradeRepository gradeRepository;
    private final SubjectService subjectService;
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;


    public List<GradeDto> getBySubject(Subject subject) {
        return gradeRepository.getAllBySubject(subject).stream().map(g -> GradeDto.from(g)).collect(Collectors.toList());
    }

    public List<GradeDto> getBySubjectOrdered(Subject subject) {
        return gradeRepository.getAllBySubjectOrderByStudentFullname(subject).stream().map(g -> GradeDto.from(g)).collect(Collectors.toList());
    }


    public List<RatingDto> getRatingGrades(String subject) {
        List<RatingDto> result = new ArrayList<>();

        if(subject.equals("all")){

            List<Student> allStudents=studentRepository.getAllBy();
            for(Student s:allStudents){
                List<Grade> studentGrades=gradeRepository.getAllByStudent(s);
                Double averageGrade=studentGrades.stream().mapToDouble(g -> g.getAverageGrade()).sum()/studentGrades.size();
                result.add(RatingDto.builder()
                                .student(StudentDto.from(s))
                                .grade(averageGrade)
                        .build());
            }

        }else{

            List<GradeDto> gradesDto=getBySubject(subjectService.findById(Long.valueOf(subject)));

            for(GradeDto g:gradesDto){
                result.add(RatingDto.builder()
                        .student(g.getStudent())
                        .grade(g.getAverageGrade())
                        .build());
            }


        }

        Collections.sort(result,Comparator.comparing(RatingDto::getGrade));
        Collections.reverse(result);
        return result;

    }

    public List<RatingDto> getGradesBySubject(Subject subject){
        List<RatingDto> result = new ArrayList<>();
        List<GradeDto> gradesDto=getBySubject(subject);

        for(GradeDto g:gradesDto){
            result.add(RatingDto.builder()
                    .student(g.getStudent())
                    .grade(g.getAverageGrade())
                    .build());
        }

        Collections.sort(result,Comparator.comparing(RatingDto::getGrade));
        Collections.reverse(result);
        return result;
    }


    public void editGrades(Long studentId, ArrayList<Integer> grades, Long subjectId) {
        Subject subject=subjectRepository.findById(subjectId).orElseThrow();
        Student student=studentRepository.findById(studentId).orElseThrow();
        Grade grade=gradeRepository.findByStudentAndSubject(student,subject);

        grade.setFirstGrade(grades.get(0));
        grade.setSecondGrade(grades.get(1));
        grade.setThirdGrade(grades.get(2));

        gradeRepository.save(grade);
    }
}
