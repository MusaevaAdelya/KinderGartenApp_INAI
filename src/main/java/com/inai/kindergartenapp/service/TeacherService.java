package com.inai.kindergartenapp.service;

import com.inai.kindergartenapp.dto.StudentDto;
import com.inai.kindergartenapp.dto.TeacherDto;
import com.inai.kindergartenapp.entity.Student;
import com.inai.kindergartenapp.entity.Subject;
import com.inai.kindergartenapp.entity.Teacher;
import com.inai.kindergartenapp.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
        for(Subject subject:subjects){
            gradeRepository.deleteAllBySubject(subject);
            subjectRepository.delete(subject);

        }
        teacherRepository.deleteById(Long.valueOf(teacherId));
    }
}
