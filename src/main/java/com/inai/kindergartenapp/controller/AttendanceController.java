package com.inai.kindergartenapp.controller;

import com.inai.kindergartenapp.dto.SubjectDto;
import com.inai.kindergartenapp.entity.Subject;
import com.inai.kindergartenapp.enums.AccountType;
import com.inai.kindergartenapp.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;

@Controller
@RequestMapping("/kindergarten/{accountType}/{userEmail}/subjects/attendance/{subjectId}")
@RequiredArgsConstructor
public class AttendanceController {
    private final DirectorService directorService;
    private final StudentService studentService;
    private final TeacherService teacherService;
    private final SubjectService subjectService;
    private final AttendanceService attendanceService;
    private final MainService mainService;

    @GetMapping
    public String attendance(@PathVariable("accountType")String accountType ,
                             @PathVariable("userEmail") String userEmail,
                             @PathVariable("subjectId")Long subjectId,
                             @RequestParam(name="date", required = false) String date , Model model){

        model.addAttribute("present",attendanceService.getPresentStudents(date,subjectId));
        model.addAttribute("absent",attendanceService.getAbsentStudents(date,subjectId));
        model.addAttribute("attendance_date", mainService.getDate(date));


        Subject subject=subjectService.findById(Long.valueOf(subjectId));
        model.addAttribute("subject", SubjectDto.from(subject));

        if(accountType.equals(AccountType.DIRECTOR.getAccountType().toLowerCase())){
            model.addAttribute("user", directorService.findByEmail(userEmail));
        }else if(accountType.equals(AccountType.STUDENT.getAccountType().toLowerCase())){
            model.addAttribute("user", studentService.findByEmail(userEmail));
        }else if(accountType.equals(AccountType.TEACHER.getAccountType().toLowerCase())){
            model.addAttribute("user", teacherService.findByEmail(userEmail));
        }

        return "attendance";
    }

    @PostMapping("/check")
    public String takeAttendance(@RequestParam String attendanceDate, @RequestParam(required = false) ArrayList<Long> presentStudents, @PathVariable("accountType")String accountType , @PathVariable("userEmail") String userEmail, @PathVariable("subjectId")Long subjectId){
        attendanceService.takeAttendance(presentStudents,attendanceDate, subjectId);
        return String.format("redirect:/kindergarten/%s/%s/subjects/attendance/%s?date=%s",accountType.toLowerCase(),userEmail,subjectId,attendanceDate);
    }

}
