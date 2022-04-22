package com.inai.kindergartenapp.repository;

import com.inai.kindergartenapp.entity.Attendance;
import com.inai.kindergartenapp.entity.Subject;
import org.springframework.data.repository.CrudRepository;

public interface AttendanceRepository extends CrudRepository<Attendance,Long> {
    Void deleteBySubject(Subject subject);

}
