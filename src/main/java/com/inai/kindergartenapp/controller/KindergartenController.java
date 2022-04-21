package com.inai.kindergartenapp.controller;


import com.inai.kindergartenapp.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class KindergartenController {
    private UserService service;

    public KindergartenController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public String handleLoginPage(@RequestParam(name="wrongInput", required = false, defaultValue = "false") String wrongInput,
                                  Model model){
        model.addAttribute("wrongInput",Boolean.parseBoolean(wrongInput));
        return "login";
    }

    @GetMapping("/kindergarten")
    public String handleKinderGarten(){
        return "demo";
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.SEE_OTHER)
    public String handleLoginPost(@RequestParam String userEmail,
                                  @RequestParam String userPassword,
                                  @RequestParam String accountType, Model model){
        if(service.checkUser(userEmail,userPassword,accountType)){
            return "redirect:/kindergarten";
        }else{
            return "redirect:/?wrongInput=true";
        }
    }


}
