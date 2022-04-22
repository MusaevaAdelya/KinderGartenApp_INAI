package com.inai.kindergartenapp.service;

import com.inai.kindergartenapp.dto.DirectorDto;
import com.inai.kindergartenapp.entity.Director;
import com.inai.kindergartenapp.repository.DirectorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DirectorService {
    private final DirectorRepository directorRepository;

    public DirectorDto findByEmail(String userEmail){
        Director director=directorRepository.findByEmail(userEmail).orElseThrow();
        return DirectorDto.from(director);
    }
}
