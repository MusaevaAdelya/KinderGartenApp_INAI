package com.inai.kindergartenapp.controller;

import com.inai.kindergartenapp.enums.AccountType;
import com.inai.kindergartenapp.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@Controller
@RequestMapping("/kindergarten/{accountType}/{userEmail}/profile")
@RequiredArgsConstructor
public class ProfileController {
    private final DirectorService directorService;
    private final StudentService studentService;
    private final TeacherService teacherService;
    private final SubjectService subjectService;
    private final ProfileService profileService;

    @GetMapping
    public String getProfilePage(@PathVariable("accountType") String accountType,
                                  @PathVariable("userEmail") String userEmail, Model model) {

        model.addAttribute("subjects", subjectService.getSubjectsByAccountType(accountType, userEmail));

        if (accountType.equals(AccountType.DIRECTOR.getAccountType().toLowerCase())) {
            model.addAttribute("user", directorService.findByEmail(userEmail));
        } else if (accountType.equals(AccountType.STUDENT.getAccountType().toLowerCase())) {
            model.addAttribute("user", studentService.findByEmail(userEmail));
        } else if (accountType.equals(AccountType.TEACHER.getAccountType().toLowerCase())) {
            model.addAttribute("user", teacherService.findByEmail(userEmail));
        }

        return "profile";
    }

    @PostMapping("/enroll_class")
    public String enrollClass(@RequestParam Long studentId, @RequestParam String classroomCode,
                              @PathVariable("accountType") String accountType, @PathVariable("userEmail") String userEmail) {
        subjectService.enrollStudent(studentId, classroomCode);
        return String.format("redirect:/kindergarten/%s/%s/profile", accountType.toLowerCase(), userEmail);
    }

    @PostMapping("/delete_class")
    public String deleteClass(@RequestParam Long studentId, @RequestParam Long subjectId,
                              @PathVariable("accountType") String accountType, @PathVariable("userEmail") String userEmail) {
        subjectService.deleteClass(studentId, subjectId);
        return String.format("redirect:/kindergarten/%s/%s/profile", accountType.toLowerCase(), userEmail);
    }

    @PostMapping("/edit_profile")
    public String editProfileInfo(@RequestParam String newFullname, @RequestParam String newEmail,
                                  @RequestParam Long userId, @RequestParam String newPassword,
                                  @PathVariable("accountType") String accountType, @PathVariable("userEmail") String userEmail) throws IOException {
        profileService.updateUser(newFullname, newEmail, newPassword, accountType, userId);
        return String.format("redirect:/kindergarten/%s/%s/profile", accountType.toLowerCase(), userEmail);
    }

    @PostMapping("/change_avatar")
    public String changeAvatar(@RequestParam MultipartFile newPicture,@RequestParam Long userId,
                               @PathVariable("accountType") String accountType, @PathVariable("userEmail") String userEmail) throws IOException {
        profileService.changeAvatar(newPicture.getInputStream(), userId, accountType);
        return String.format("redirect:/kindergarten/%s/%s/profile", accountType.toLowerCase(), userEmail);
    }
}
