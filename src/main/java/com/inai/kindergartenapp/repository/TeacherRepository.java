package com.inai.kindergartenapp.repository;

import com.inai.kindergartenapp.entity.Teacher;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TeacherRepository extends CrudRepository<Teacher,Long> {
    Optional<Teacher> findByFullname(String fullName);
}
