package com.inai.kindergartenapp.controller;


import com.inai.kindergartenapp.enums.AccountType;
import com.inai.kindergartenapp.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/kindergarten/{accountType}/{userEmail}/rating")
@RequiredArgsConstructor
public class RatingController {
    private final DirectorService directorService;
    private final StudentService studentService;
    private final TeacherService teacherService;
    private final SubjectService subjectService;
    private final GradeService gradeService;

    @GetMapping
    public String rating(@PathVariable("accountType")String accountType , @PathVariable("userEmail") String userEmail, @RequestParam(name="subject", required = false, defaultValue = "all") String subject, Model model){
        model.addAttribute("subjects",subjectService.getSubjectsDto());
        model.addAttribute("grades",gradeService.getRatingGrades(subject));
        model.addAttribute("currentSubject", subjectService.getSubjectName(subject));
        model.addAttribute("progress",gradeService.getAcademicProgress());

        if(accountType.equals(AccountType.DIRECTOR.getAccountType().toLowerCase())){
            model.addAttribute("user", directorService.findByEmail(userEmail));
        }else if(accountType.equals(AccountType.STUDENT.getAccountType().toLowerCase())){
            model.addAttribute("user", studentService.findByEmail(userEmail));
        }else if(accountType.equals(AccountType.TEACHER.getAccountType().toLowerCase())){
            model.addAttribute("user", teacherService.findByEmail(userEmail));
        }

        return "rating";
    }
}
