package com.inai.kindergartenapp.repository;

import com.inai.kindergartenapp.entity.Director;
import com.inai.kindergartenapp.entity.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends CrudRepository<Student,Long> {
    List<Student> getAllByOrderByFullname();


    @Query("select s from Student s order by s.fullname")
    List<Student> getAllBy();

    Optional<Student> findByEmail(String email);
}
