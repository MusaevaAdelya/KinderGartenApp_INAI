package com.inai.kindergartenapp.controller;

import com.inai.kindergartenapp.enums.AccountType;
import com.inai.kindergartenapp.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/kindergarten/{accountType}/{userEmail}/students")
@RequiredArgsConstructor
public class StudentsController {
    private final SubjectService subjectService;
    private final DirectorService directorService;
    private final StudentService studentSerivice;
    private final TeacherService teacherService;
    private final GradeService gradeService;

    @GetMapping
    public String allSubjects(@PathVariable("accountType")String accountType , @PathVariable("userEmail") String userEmail, Model model){
        model.addAttribute("grades",studentSerivice.studentGrades());

        if(accountType.equals(AccountType.DIRECTOR.getAccountType().toLowerCase())){
            model.addAttribute("user", directorService.findByEmail(userEmail));
        }else if(accountType.equals(AccountType.STUDENT.getAccountType().toLowerCase())){
            model.addAttribute("user", studentSerivice.findByEmail(userEmail));
        }else if(accountType.equals(AccountType.TEACHER.getAccountType().toLowerCase())){
            model.addAttribute("user", teacherService.findByEmail(userEmail));
        }

        return "students";
    }
}
