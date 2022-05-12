package com.inai.kindergartenapp.repository;

import com.inai.kindergartenapp.entity.Homework;
import com.inai.kindergartenapp.entity.Subject;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface HomeworkRepository extends CrudRepository<Homework,Long> {
    Void deleteBySubject(Subject subject);

    List<Homework> findAllBySubjectAndDate(Subject subject, LocalDate date);
}
