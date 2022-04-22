package com.inai.kindergartenapp.controller;

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
                                  @RequestParam String accountType, Model model){
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
}
