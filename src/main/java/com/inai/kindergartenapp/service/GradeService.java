package com.inai.kindergartenapp.service;

import com.inai.kindergartenapp.dto.*;
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
                Double averageGrade=0.0;
                if(studentGrades.size()!=0){
                    averageGrade=studentGrades.stream( ).mapToDouble(g -> g.getAverageGrade()).sum()/studentGrades.size();
                }
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


    public HashMap<SubjectDto,Double> getAcademicProgress(){
        HashMap<SubjectDto,Double> result=new HashMap<>();

        List<Subject> subjects=subjectRepository.getAllBy();

        for(Subject s:subjects){
            List<Grade> grades=gradeRepository.getAllBySubject(s);
            Double average=grades.stream().mapToDouble(g->g.getAverageGrade()).sum()/grades.size();
            result.put(SubjectDto.from(s),average);
        }

        return result;

    }

    public List<AcademicProgressDto> getStudentsAcademicProgress(){
        List<AcademicProgressDto> result=new ArrayList<>();

        List<Student> students=studentRepository.getAllBy();
        for(Student student:students){
            HashMap<SubjectDto,Double> studentsProgress=new HashMap<>();
            List<Grade> studentsGrades=gradeRepository.getAllByStudent(student);
            studentsGrades.forEach(sg->studentsProgress.put(SubjectDto.from(sg.getSubject()),sg.getAverageGrade()));
            result.add(AcademicProgressDto.builder()
                            .student(StudentDto.from(student))
                            .progress(studentsProgress)
                    .build());

        }

        return result;

    }
}
