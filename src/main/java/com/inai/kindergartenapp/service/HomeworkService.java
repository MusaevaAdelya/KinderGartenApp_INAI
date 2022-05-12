package com.inai.kindergartenapp.service;

import com.inai.kindergartenapp.dto.HomeworkDto;
import com.inai.kindergartenapp.entity.Homework;
import com.inai.kindergartenapp.entity.Subject;
import com.inai.kindergartenapp.repository.HomeworkRepository;
import com.inai.kindergartenapp.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HomeworkService {
    private final SubjectRepository subjectRepository;
    private final HomeworkRepository homeworkRepository;


    public List<HomeworkDto> getHomeworks(Long subjectId, String dateString) {
        LocalDate date;

        if (dateString == null) {
            date = LocalDate.now();
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            date = LocalDate.parse(dateString, formatter);
        }

        Subject subject = subjectRepository.findById(subjectId).orElseThrow();

        List<Homework> homeworks = homeworkRepository.findAllBySubjectAndDate(subject, date);
        return homeworks.stream().map(h -> HomeworkDto.from(h)).collect(Collectors.toList());
    }

    public void deleteTask(Long deleteTaskId) {
        homeworkRepository.deleteById(deleteTaskId);
    }

    public void addNewHomework(Long subjectId, String newTaskDate, String taskDescription) {
        homeworkRepository.save(Homework.builder()
                .task(taskDescription)
                .date(LocalDate.parse(newTaskDate, DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .subject(subjectRepository.findById(subjectId).orElseThrow())
                .build());
    }
}
