package com.inai.kindergartenapp.controller;


import com.inai.kindergartenapp.dto.SubjectDto;
import com.inai.kindergartenapp.entity.Subject;
import com.inai.kindergartenapp.enums.AccountType;
import com.inai.kindergartenapp.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/kindergarten/{accountType}/{userEmail}/subjects")
@RequiredArgsConstructor
public class SubjectsController {
    private final SubjectService subjectService;
    private final DirectorService directorService;
    private final StudentService studentSerivice;
    private final TeacherService teacherService;
    private final GradeService gradeService;

    @GetMapping
    public String allSubjects(@PathVariable("accountType")String accountType ,@PathVariable("userEmail") String userEmail, Model model){
        model.addAttribute("subjects",subjectService.getSubjectsDto());
        model.addAttribute("teachers",teacherService.getAllTeachers());

        if(accountType.equals(AccountType.DIRECTOR.getAccountType().toLowerCase())){
            model.addAttribute("user", directorService.findByEmail(userEmail));
        }else if(accountType.equals(AccountType.STUDENT.getAccountType().toLowerCase())){
            model.addAttribute("user", studentSerivice.findByEmail(userEmail));
        }else if(accountType.equals(AccountType.TEACHER.getAccountType().toLowerCase())){
            model.addAttribute("user", teacherService.findByEmail(userEmail));
        }

        return "subjects";
    }

    @PostMapping("/newsubject")
    public String addNewSubject(@RequestParam String subjectTitle,@RequestParam String teacherId,@PathVariable("accountType")String accountType ,@PathVariable("userEmail") String userEmail){
        subjectService.createSubject(subjectTitle,teacherId);
        return String.format("redirect:/kindergarten/%s/%s/subjects",accountType.toLowerCase(),userEmail);
    }

    @PostMapping("/delete_subject")
    public String deleteSubject(@PathVariable("accountType")String accountType ,@PathVariable("userEmail") String userEmail,@RequestParam String subjectId){
        subjectService.deleteSubjectById(subjectId);
        return String.format("redirect:/kindergarten/%s/%s/subjects",accountType.toLowerCase(),userEmail);
    }

    @GetMapping("/students/{subjectId}")
    public String studentsOfClass(@PathVariable("subjectId") Long subjectId,@PathVariable("accountType")String accountType ,@PathVariable("userEmail") String userEmail,Model model){
        if(accountType.equals(AccountType.DIRECTOR.getAccountType().toLowerCase())){
            model.addAttribute("user", directorService.findByEmail(userEmail));
        }else if(accountType.equals(AccountType.STUDENT.getAccountType().toLowerCase())){
            model.addAttribute("user", studentSerivice.findByEmail(userEmail));
        }else if(accountType.equals(AccountType.TEACHER.getAccountType().toLowerCase())){
            model.addAttribute("user", teacherService.findByEmail(userEmail));
        }

        Subject subject=subjectService.findById(subjectId);
        model.addAttribute("subject",SubjectDto.from(subject));
        model.addAttribute("grades",gradeService.getBySubject(subject));

        return "subjectinfo";
    }

}
