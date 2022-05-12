package com.inai.kindergartenapp.controller;

import com.inai.kindergartenapp.dto.SubjectDto;
import com.inai.kindergartenapp.enums.AccountType;
import com.inai.kindergartenapp.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping("/kindergarten/{accountType}/{userEmail}/subjects/grades/{subjectId}")
@RequiredArgsConstructor
public class GradeController {
    private final DirectorService directorService;
    private final StudentService studentService;
    private final TeacherService teacherService;
    private final SubjectService subjectService;
    private final GradeService gradeService;

    @GetMapping
    public String getGradesPage(@PathVariable("accountType")String accountType ,
                             @PathVariable("userEmail") String userEmail,
                             @PathVariable("subjectId")Long subjectId, Model model){

        model.addAttribute("grades",gradeService.getBySubjectOrdered(subjectService.findById(subjectId)));

        model.addAttribute("subject", SubjectDto.from(subjectService.findById(subjectId)));

        if(accountType.equals(AccountType.DIRECTOR.getAccountType().toLowerCase())){
            model.addAttribute("user", directorService.findByEmail(userEmail));
        }else if(accountType.equals(AccountType.STUDENT.getAccountType().toLowerCase())){
            model.addAttribute("user", studentService.findByEmail(userEmail));
        }else if(accountType.equals(AccountType.TEACHER.getAccountType().toLowerCase())){
            model.addAttribute("user", teacherService.findByEmail(userEmail));
        }

        return "grades";
    }

    @PostMapping("/edit")
    public String editGrades(@RequestParam ArrayList<Integer> grade, @RequestParam Long studentId, @PathVariable("accountType")String accountType , @PathVariable("userEmail") String userEmail, @PathVariable("subjectId")Long subjectId){
        gradeService.editGrades(studentId, grade, subjectId);
        return String.format("redirect:/kindergarten/%s/%s/subjects/grades/%s",accountType.toLowerCase(),userEmail,subjectId);
    }
}
