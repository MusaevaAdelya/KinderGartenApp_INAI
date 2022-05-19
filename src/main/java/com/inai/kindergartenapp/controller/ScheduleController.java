package com.inai.kindergartenapp.controller;

import com.inai.kindergartenapp.enums.AccountType;
import com.inai.kindergartenapp.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/kindergarten/{accountType}/{userEmail}/schedule")
@RequiredArgsConstructor
public class ScheduleController {
    private final DirectorService directorService;
    private final StudentService studentService;
    private final TeacherService teacherService;
    private final SubjectService subjectService;
    private final LessonService lessonService;

    @GetMapping
    public String getSchedulePage(@PathVariable("accountType")String accountType , @PathVariable("userEmail") String userEmail,
                         @RequestParam(name="date", required = false) String date, Model model){
        model.addAttribute("subjects",subjectService.getSubjectsDto());
        model.addAttribute("schedule_date", lessonService.getDate(date));
        model.addAttribute("lessons", lessonService.getLessons(date));

        if(accountType.equals(AccountType.DIRECTOR.getAccountType().toLowerCase())){
            model.addAttribute("user", directorService.findByEmail(userEmail));
        }else if(accountType.equals(AccountType.STUDENT.getAccountType().toLowerCase())){
            model.addAttribute("user", studentService.findByEmail(userEmail));
        }else if(accountType.equals(AccountType.TEACHER.getAccountType().toLowerCase())){
            model.addAttribute("user", teacherService.findByEmail(userEmail));
        }

        return "schedule";
    }

    @PostMapping("/add_lesson")
    public String addNewLesson(@RequestParam Long subjectId, @RequestParam String startTime,
                               @RequestParam String endTime, @RequestParam String lessonDate,
                               @PathVariable("accountType")String accountType , @PathVariable("userEmail") String userEmail){
        lessonService.addNewLesson(subjectId, startTime, endTime, lessonDate);
        return String.format("redirect:/kindergarten/%s/%s/schedule?date=%s",accountType.toLowerCase(),userEmail,lessonDate);
    }

    @PostMapping("/delete_lesson")
    public String deleteLesson(@RequestParam Long lessonId, @RequestParam String lessonDate,
                               @PathVariable("accountType")String accountType , @PathVariable("userEmail") String userEmail){

        lessonService.deleteLesson(lessonId);
        return String.format("redirect:/kindergarten/%s/%s/schedule?date=%s",accountType.toLowerCase(),userEmail,lessonDate);
    }
}
