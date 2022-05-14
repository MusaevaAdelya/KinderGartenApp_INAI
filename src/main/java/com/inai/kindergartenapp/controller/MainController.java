package com.inai.kindergartenapp.controller;

import com.inai.kindergartenapp.entity.Student;
import com.inai.kindergartenapp.enums.AccountType;
import com.inai.kindergartenapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class MainController {
    private final UserService userService;

    @GetMapping
    public String login(@RequestParam(name="wrongInput", required = false, defaultValue = "false") String wrongInput,
                                  Model model){
        model.addAttribute("wrongInput",Boolean.parseBoolean(wrongInput));
        return "login";
    }

    @PostMapping("/login")
    public String handleLoginPost(@RequestParam String userEmail,
                                  @RequestParam String userPassword,
                                  @RequestParam String accountType){
        if(userService.checkUser(userEmail,userPassword,accountType)){
            if(accountType.equals(AccountType.DIRECTOR.getAccountType())){
                return "redirect:/kindergarten/"+accountType.toLowerCase()+"/"+userEmail+"/subjects";
            }else{
                return "";
            }
        }else{
            return "redirect:/?wrongInput=true";
        }
    }

    @GetMapping("/register")
    public String register(@RequestParam(name="invalid", required = false) String invalidEmail,
                        Model model){
        model.addAttribute("invalidEmail",invalidEmail);
        return "register";
    }

    @PostMapping("/register")
    public String handleRegisterPost(@ModelAttribute("newStudent") Student newStudent){
        if(userService.checkNewStudent(newStudent)){
            userService.addNewStudent(newStudent);
            return String.format("redirect:/kindergarten/%s/%s/subjects",newStudent.getAccountType().toLowerCase(),newStudent.getEmail());
        }else{
            return String.format("redirect:/register?invalid=%s",newStudent.getEmail());
        }
    }
}
