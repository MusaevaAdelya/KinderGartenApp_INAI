package com.inai.kindergartenapp.repository;

import com.inai.kindergartenapp.entity.Lesson;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface LessonRepository extends CrudRepository<Lesson,Long> {

    @Query("select l from Lesson l where l.date = ?1 order by l.startTime asc")
    List<Lesson> findByDate(LocalDate lessonDate);


    void deleteById(Long lessonId);
}
