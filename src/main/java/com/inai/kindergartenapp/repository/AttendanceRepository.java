package com.inai.kindergartenapp.repository;

import com.inai.kindergartenapp.entity.Attendance;
import org.springframework.data.repository.CrudRepository;

public interface AttendanceRepository extends CrudRepository<Attendance,Long> {
}
