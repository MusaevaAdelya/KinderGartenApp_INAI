package com.inai.kindergartenapp.service;

import com.inai.kindergartenapp.dto.AttendanceDto;
import com.inai.kindergartenapp.dto.StudentDto;
import com.inai.kindergartenapp.entity.Attendance;
import com.inai.kindergartenapp.entity.Student;
import com.inai.kindergartenapp.entity.Subject;
import com.inai.kindergartenapp.repository.AttendanceRepository;
import com.inai.kindergartenapp.repository.StudentRepository;
import com.inai.kindergartenapp.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AttendanceService {
    private final AttendanceRepository attendanceRepository;
    private final SubjectRepository subjectRepository;
    private final StudentRepository studentRepository;

    public AttendanceDto getPresentStudents(String dateString, Long subjectId) {
        LocalDate date;

        if(dateString==null){
            date=LocalDate.now();
        }else{
            date = formatDate(dateString);
        }

        if(checkDate(date)){
            Subject subject=subjectRepository.findById(subjectId).orElseThrow();
            Attendance attendance;

            try{
                attendance=attendanceRepository.findByDateAndSubjectAndPresent(date,subject,true);
            }catch(NoSuchElementException ex){
                return null;
            }

            return AttendanceDto.from(attendance);

        }else{
            return null;
        }


    }

    public AttendanceDto getAbsentStudents(String dateString, Long subjectId) {
        LocalDate date;

        if(dateString==null){
            date=LocalDate.now();
        }else{
            date = formatDate(dateString);
        }

        if(checkDate(date)){
            Subject subject=subjectRepository.findById(subjectId).orElseThrow();
            return AttendanceDto.from(attendanceRepository.findByDateAndSubjectAndPresent(date,subject,false));
        }else{
            return null;
        }

    }

    public void takeAttendance(ArrayList<Long> presentStudentsId, String attendanceDateString, Long subjectId) {
             Subject subject=subjectRepository.findById(subjectId).orElseThrow();
             List<Student> presentStudents=new ArrayList<>();
             if(presentStudentsId!=null){
                presentStudents=presentStudentsId.stream().map(i->studentRepository.findById(i).orElseThrow()).collect(Collectors.toList());

             }

             List<Student> absentStudents=new ArrayList<>();
             for(Student student: subject.getStudents()){
                 if(!presentStudents.contains(student)){
                     absentStudents.add(student);
                 }
             }

            LocalDate attendanceDate = formatDate(attendanceDateString);

            Attendance attendancePresent=attendanceRepository.findByDateAndSubjectAndPresent(attendanceDate,subject,true);
            attendancePresent.setStudents(presentStudents);

            Attendance attendanceAbsent=attendanceRepository.findByDateAndSubjectAndPresent(attendanceDate,subject,false);
            attendanceAbsent.setStudents(absentStudents);


            attendanceRepository.save(attendancePresent);
            attendanceRepository.save(attendanceAbsent);
    }



    public boolean checkDate(LocalDate userDate){
        if(userDate.isBefore(formatDate("2022-05-01")) || userDate.isAfter(LocalDate.now().plusDays(7))){
            return false;
        }else{
            return true;
        }
    }

    public LocalDate formatDate(String stringDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(stringDate, formatter);
    }


}
