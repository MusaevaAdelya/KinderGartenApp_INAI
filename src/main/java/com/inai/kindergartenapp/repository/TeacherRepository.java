package com.inai.kindergartenapp.repository;

import com.inai.kindergartenapp.entity.Teacher;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TeacherRepository extends CrudRepository<Teacher,Long> {
    Optional<Teacher> findByFullname(String fullName);
    List<Teacher> getAllBy();

    Optional<Teacher> findByEmail(String email);
}
