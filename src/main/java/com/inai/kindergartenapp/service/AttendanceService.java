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
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            date = LocalDate.parse(dateString, formatter);
        }

        Subject subject=subjectRepository.findById(subjectId).orElseThrow();


        return AttendanceDto.from(attendanceRepository.findByDateAndSubjectAndPresent(date,subject,true));

    }

    public AttendanceDto getAbsentStudents(String dateString, Long subjectId) {
        LocalDate date;

        if(dateString==null){
            date=LocalDate.now();
        }else{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            date = LocalDate.parse(dateString, formatter);
        }

        Subject subject=subjectRepository.findById(subjectId).orElseThrow();

        return AttendanceDto.from(attendanceRepository.findByDateAndSubjectAndPresent(date,subject,false));
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

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate attendanceDate = LocalDate.parse(attendanceDateString, formatter);

            Attendance attendancePresent=attendanceRepository.findByDateAndSubjectAndPresent(attendanceDate,subject,true);
            attendancePresent.setStudents(presentStudents);

            Attendance attendanceAbsent=attendanceRepository.findByDateAndSubjectAndPresent(attendanceDate,subject,false);
            attendanceAbsent.setStudents(absentStudents);


            attendanceRepository.save(attendancePresent);
            attendanceRepository.save(attendanceAbsent);
    }
}
