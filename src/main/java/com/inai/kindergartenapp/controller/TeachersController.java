package com.inai.kindergartenapp.controller;

import com.inai.kindergartenapp.enums.AccountType;
import com.inai.kindergartenapp.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/kindergarten/{accountType}/{userEmail}/teachers")
@RequiredArgsConstructor
public class TeachersController {
    private final SubjectService subjectService;
    private final DirectorService directorService;
    private final StudentService studentSerivice;
    private final TeacherService teacherService;
    private final GradeService gradeService;

    @GetMapping
    public String allSubjects(@PathVariable("accountType")String accountType , @PathVariable("userEmail") String userEmail, Model model){
        model.addAttribute("teachers",teacherService.getAllTeachers());

        if(accountType.equals(AccountType.DIRECTOR.getAccountType().toLowerCase())){
            model.addAttribute("user", directorService.findByEmail(userEmail));
        }else if(accountType.equals(AccountType.STUDENT.getAccountType().toLowerCase())){
            model.addAttribute("user", studentSerivice.findByEmail(userEmail));
        }else if(accountType.equals(AccountType.TEACHER.getAccountType().toLowerCase())){
            model.addAttribute("user", teacherService.findByEmail(userEmail));
        }

        return "teachers";
    }

    @PostMapping("/delete_teacher")
    public String deleteSubject(@PathVariable("accountType")String accountType ,@PathVariable("userEmail") String userEmail,@RequestParam String teacherId){
        teacherService.deleteTeacherById(teacherId);
        return String.format("redirect:/kindergarten/%s/%s/teachers",accountType.toLowerCase(),userEmail);
    }
}
