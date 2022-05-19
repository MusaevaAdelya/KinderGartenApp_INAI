package com.inai.kindergartenapp.service;

import com.inai.kindergartenapp.dto.SubjectDto;
import com.inai.kindergartenapp.entity.*;
import com.inai.kindergartenapp.enums.AccountType;
import com.inai.kindergartenapp.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubjectService {
    private final SubjectRepository subjectRepository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;
    private final GradeRepository gradeRepository;
    private final AttendanceRepository attendanceRepository;
    private final HomeworkRepository homeworkRepository;
    private final LessonRepository lessonRepository;

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
        Subject subject=subjectRepository.findById(Long.valueOf(subjectId)).orElseThrow();

        List<Long> attendanceId=attendanceRepository.findAllBySubject(subject).stream().map(Attendance::getId).collect(Collectors.toList());
        attendanceRepository.deleteAllById(attendanceId);

        List<Long> gradesId=gradeRepository.findAllBySubject(subject).stream().map(Grade::getId).collect(Collectors.toList());
        gradeRepository.deleteAllById(gradesId);

        List<Long> homeworksId=homeworkRepository.findAllBySubject(subject).stream().map(Homework::getId).collect(Collectors.toList());
        homeworkRepository.deleteAllById(homeworksId);

        List<Long> lessonsId=lessonRepository.findAllBySubject(subject).stream().map(Lesson::getId).collect(Collectors.toList());
        lessonRepository.deleteAllById(lessonsId);

        subjectRepository.deleteById(Long.valueOf(subjectId));

    }

    public Subject findById(Long subjectId) {
        return subjectRepository.findById(subjectId).orElseThrow();
    }

    public String getSubjectName(String subject) {
        if(subject.equals("all")){
            return "All Subjects";
        }else{
            return subjectRepository.findById(Long.valueOf(subject)).orElseThrow().getName();
        }
    }

    public void deleteClass(Long studentId, Long subjectId) {
        Subject subject=subjectRepository.findById(subjectId).orElseThrow();
        Student student=studentRepository.findById(studentId).orElseThrow();
        subject.getStudents().remove(student);
        subjectRepository.save(subject);

    }

    public void enrollStudent(Long studentId, String classroomCode) {
        Student student=studentRepository.findById(studentId).orElseThrow();
        Subject subject=subjectRepository.findByCode(classroomCode).orElseThrow();
        subject.getStudents().add(student);
        subjectRepository.save(subject);

        gradeRepository.save(Grade.builder()
                        .student(student)
                        .subject(subject)
                .build());
    }

    public List<SubjectDto> getSubjectsByAccountType(String accountType, String userEmail) {
        if(accountType.equals(AccountType.STUDENT.getAccountType())){
            Student student=studentRepository.findByEmail(userEmail).orElseThrow();
            return getSubjectsByStudent(student);
        }else if(accountType.equals(AccountType.TEACHER.getAccountType())){
            Teacher teacher=teacherRepository.findByEmail(userEmail).orElseThrow();
            return getSubjectsByTeacher(teacher);
        }else{
            return getSubjectsDto();
        }
    }

    public List<SubjectDto> getSubjectsByStudent(Student student){
        List<Subject> subjects=subjectRepository.getAllBy().stream().filter(s->s.getStudents().contains(student)).collect(Collectors.toList());
        return subjects.stream().map(s-> SubjectDto.from(s)).collect(Collectors.toList());
    }

    public List<SubjectDto> getSubjectsByTeacher(Teacher teacher){
        List<Subject> subjects=subjectRepository.getAllBy().stream().filter(s->s.getTeacher().getId()==teacher.getId()).collect(Collectors.toList());
        return subjects.stream().map(s->SubjectDto.from(s)).collect(Collectors.toList());
    }
}