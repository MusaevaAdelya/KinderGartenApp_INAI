package com.inai.kindergartenapp.repository;

import com.inai.kindergartenapp.entity.Director;
import com.inai.kindergartenapp.entity.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends CrudRepository<Student,Long> {
    List<Student> getAllByOrderByFullname();

    List<Student> getAllBy();

    Optional<Student> findByEmail(String email);
}
