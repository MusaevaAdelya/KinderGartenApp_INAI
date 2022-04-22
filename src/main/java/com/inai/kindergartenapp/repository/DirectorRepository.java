package com.inai.kindergartenapp.repository;

import com.inai.kindergartenapp.entity.Director;
import com.inai.kindergartenapp.entity.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface DirectorRepository extends CrudRepository<Director,Long> {
    List<Director> getAllBy();

    Optional<Director> findByEmail(String email);
}
