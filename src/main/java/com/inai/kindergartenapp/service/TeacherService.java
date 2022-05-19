package com.inai.kindergartenapp.service;

import com.inai.kindergartenapp.dto.StudentDto;
import com.inai.kindergartenapp.dto.TeacherDto;
import com.inai.kindergartenapp.entity.*;
import com.inai.kindergartenapp.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeacherService {
    private final TeacherRepository teacherRepository;
    private final SubjectRepository subjectRepository;
    private final AttendanceRepository attendanceRepository;
    private final GradeRepository gradeRepository;
    private final HomeworkRepository homeworkRepository;
    private final LessonRepository lessonRepository;

    public TeacherDto findByEmail(String userEmail){
        Teacher student=teacherRepository.findByEmail(userEmail).orElseThrow();
        return TeacherDto.from(student);
    }

    public List<TeacherDto> getAllTeachers(){
        return teacherRepository.getAllBy().stream().map(t->TeacherDto.from(t)).collect(Collectors.toList());
    }

    public void deleteTeacherById(String teacherId) {
        Teacher teacher=teacherRepository.findById(Long.valueOf(teacherId)).orElseThrow();
        List<Subject> subjects=subjectRepository.findAllByTeacher(teacher);
        List<Grade> grades=new ArrayList<>();
        List<Attendance> attendances=new ArrayList<>();
        List<Homework> homeworks=new ArrayList<>();
        List<Lesson> lessons=new ArrayList<>();
        for(Subject subject:subjects){
            grades.addAll(gradeRepository.findAllBySubject(subject));
            attendances.addAll(attendanceRepository.findAllBySubject(subject));
            homeworks.addAll(homeworkRepository.findAllBySubject(subject));
            lessons.addAll(lessonRepository.findAllBySubject(subject));

        }
        List<Long> gradesId=grades.stream().map(Grade::getId).collect(Collectors.toList());
        gradeRepository.deleteAllById(gradesId);

        List<Long> attendanceId=attendances.stream().map(Attendance::getId).collect(Collectors.toList());
        attendanceRepository.deleteAllById(attendanceId);

        List<Long> homeworkId=homeworks.stream().map(Homework::getId).collect(Collectors.toList());
        homeworkRepository.deleteAllById(homeworkId);

        List<Long> lessonId=lessons.stream().map(Lesson::getId).collect(Collectors.toList());
        lessonRepository.deleteAllById(lessonId);

        List<Long> subjectsId=subjects.stream().map(Subject::getId).collect(Collectors.toList());
        subjectRepository.deleteAllById(subjectsId);

        teacherRepository.deleteById(Long.valueOf(teacherId));
    }
}
