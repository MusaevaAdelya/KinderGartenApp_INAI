package com.inai.kindergartenapp.repository;

import com.inai.kindergartenapp.entity.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student,Long> {
}
