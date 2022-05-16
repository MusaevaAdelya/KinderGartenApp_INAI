package com.inai.kindergartenapp.service;

import com.inai.kindergartenapp.dto.LessonDto;
import com.inai.kindergartenapp.entity.Lesson;
import com.inai.kindergartenapp.entity.Subject;
import com.inai.kindergartenapp.repository.LessonRepository;
import com.inai.kindergartenapp.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.jni.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LessonService {
    private final LessonRepository lessonRepository;
    private final SubjectRepository subjectRepository;

    public LocalDate getDate(String dateString) {
        LocalDate date;

        if (dateString == null) {
            date = LocalDate.now();
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            date = LocalDate.parse(dateString, formatter);
        }

        return date;
    }

    public List<LessonDto> getLessons(String dateString) {
        LocalDate lessonDate=getDate(dateString);

        List<Lesson> lessons=lessonRepository.findByDate(lessonDate);

        return lessons.stream().map(l->LessonDto.from(l)).collect(Collectors.toList());

    }

    public void addNewLesson(Long subjectId, String startTime, String endTime, String lessonDate) {

        lessonRepository.save(Lesson.builder()
                        .subject(subjectRepository.findById(subjectId).orElseThrow())
                        .date(LocalDate.parse(lessonDate,DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                        .startTime(LocalTime.parse(startTime))
                        .endTime(LocalTime.parse(endTime))
                .build());
    }

    public void deleteLesson(Long lessonId) {
        lessonRepository.deleteById(lessonId);
    }
}
