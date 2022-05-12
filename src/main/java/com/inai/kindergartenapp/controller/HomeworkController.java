package com.inai.kindergartenapp.controller;

import com.inai.kindergartenapp.dto.SubjectDto;
import com.inai.kindergartenapp.enums.AccountType;
import com.inai.kindergartenapp.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/kindergarten/{accountType}/{userEmail}/subjects/homework/{subjectId}")
@RequiredArgsConstructor
public class HomeworkController {
    private final DirectorService directorService;
    private final StudentService studentService;
    private final TeacherService teacherService;
    private final SubjectService subjectService;
    private final HomeworkService homeworkService;

    @GetMapping
    public String getHomeworkPage(@PathVariable("accountType")String accountType ,
                             @PathVariable("userEmail") String userEmail,
                             @PathVariable("subjectId")Long subjectId,
                             @RequestParam(name="date", required = false) String date ,Model model){

        model.addAttribute("homeworks", homeworkService.getHomeworks(subjectId,date));

        model.addAttribute("subject", SubjectDto.from(subjectService.findById(subjectId)));


        if(accountType.equals(AccountType.DIRECTOR.getAccountType().toLowerCase())){
            model.addAttribute("user", directorService.findByEmail(userEmail));
        }else if(accountType.equals(AccountType.STUDENT.getAccountType().toLowerCase())){
            model.addAttribute("user", studentService.findByEmail(userEmail));
        }else if(accountType.equals(AccountType.TEACHER.getAccountType().toLowerCase())){
            model.addAttribute("user", teacherService.findByEmail(userEmail));
        }

        return "homework";
    }


    @PostMapping("/new_task")
    public String addNewTask(@RequestParam String newTaskDate, @RequestParam String taskDescription, @PathVariable("accountType")String accountType , @PathVariable("userEmail") String userEmail, @PathVariable("subjectId")Long subjectId){
        homeworkService.addNewHomework(subjectId, newTaskDate, taskDescription);
        return String.format("redirect:/kindergarten/%s/%s/subjects/homework/%s?date=%s",accountType.toLowerCase(),userEmail,subjectId,newTaskDate);
    }

    @PostMapping("/delete_task")
    public String deleteTask(@RequestParam Long deleteTaskId, @RequestParam String newTaskDate, @PathVariable("accountType")String accountType , @PathVariable("userEmail") String userEmail, @PathVariable("subjectId")Long subjectId){
        homeworkService.deleteTask(deleteTaskId);
        return String.format("redirect:/kindergarten/%s/%s/subjects/homework/%s?date=%s",accountType.toLowerCase(),userEmail,subjectId, newTaskDate);
    }

}
