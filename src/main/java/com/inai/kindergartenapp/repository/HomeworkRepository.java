package com.inai.kindergartenapp.repository;

import com.inai.kindergartenapp.entity.Homework;
import com.inai.kindergartenapp.entity.Subject;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface HomeworkRepository extends CrudRepository<Homework,Long> {
    Void deleteBySubject(Subject subject);

    @Query("select h from Homework h where h.subject = ?1 and h.date = ?2 order by h.id asc")
    List<Homework> findAllBySubjectAndDate(Subject subject, LocalDate date);
}
