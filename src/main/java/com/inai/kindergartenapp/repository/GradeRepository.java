package com.inai.kindergartenapp.repository;

import com.inai.kindergartenapp.entity.Grade;
import com.inai.kindergartenapp.entity.Student;
import com.inai.kindergartenapp.entity.Subject;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GradeRepository extends CrudRepository<Grade,Long> {
    List<Grade> getAllBySubject(Subject subject);

    List<Grade> getAllByStudent(Student student);

    Void deleteAllBySubject(Subject subject);

    Grade findByStudentAndSubject(Student student,Subject subject);

    List<Grade> getAllBySubjectOrderByStudentFullname(Subject subject);
}
