package com.inai.kindergartenapp.repository;

import com.inai.kindergartenapp.entity.Student;
import com.inai.kindergartenapp.entity.Subject;
import com.inai.kindergartenapp.entity.Teacher;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface SubjectRepository extends CrudRepository<Subject,Long> {
    List<Subject> getAllBy();

    Void deleteByTeacher(Teacher teacher);

    List<Subject> findAllByTeacher(Teacher teacher);

}
