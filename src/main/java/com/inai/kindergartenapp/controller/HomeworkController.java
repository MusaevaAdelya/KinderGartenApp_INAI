package com.inai.kindergartenapp.controller;

import com.inai.kindergartenapp.dto.SubjectDto;
import com.inai.kindergartenapp.enums.AccountType;
import com.inai.kindergartenapp.service.DirectorService;
import com.inai.kindergartenapp.service.StudentService;
import com.inai.kindergartenapp.service.SubjectService;
import com.inai.kindergartenapp.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

//@Controller
//@RequestMapping("/kindergarten/{accountType}/{userEmail}/subjects/homework/{subjectId}")
//@RequiredArgsConstructor
//public class HomeworkController {
//    private final DirectorService directorService;
//    private final StudentService studentService;
//    private final TeacherService teacherService;
//    private final SubjectService subjectService;
//
//    @GetMapping
//    public String attendance(@PathVariable("accountType")String accountType ,
//                             @PathVariable("userEmail") String userEmail,
//                             @PathVariable("subjectId")Long subjectId, Model model){
//
//        model.addAttribute("grades",gradeService.getBySubjectOrdered(subjectService.findById(subjectId)));
//
//        model.addAttribute("subject", SubjectDto.from(subjectService.findById(subjectId)));
//
//        if(accountType.equals(AccountType.DIRECTOR.getAccountType().toLowerCase())){
//            model.addAttribute("user", directorService.findByEmail(userEmail));
//        }else if(accountType.equals(AccountType.STUDENT.getAccountType().toLowerCase())){
//            model.addAttribute("user", studentService.findByEmail(userEmail));
//        }else if(accountType.equals(AccountType.TEACHER.getAccountType().toLowerCase())){
//            model.addAttribute("user", teacherService.findByEmail(userEmail));
//        }
//
//        return "grades";
//    }
//}
