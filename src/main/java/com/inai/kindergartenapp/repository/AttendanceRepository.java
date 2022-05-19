package com.inai.kindergartenapp.repository;

import com.inai.kindergartenapp.entity.Attendance;
import com.inai.kindergartenapp.entity.Subject;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public interface AttendanceRepository extends CrudRepository<Attendance,Long> {
    Void deleteBySubject(Subject subject);

    Attendance findByDateAndSubjectAndPresent(LocalDate date, Subject subject,boolean isPresent);

    void deleteAllBySubject(Subject subject);

    List<Attendance> findAllBySubject(Subject subject);
}
