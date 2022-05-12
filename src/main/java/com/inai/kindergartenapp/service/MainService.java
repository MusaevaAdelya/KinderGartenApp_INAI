package com.inai.kindergartenapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class MainService {
    public String getDate(String date) {
        if(date==null){
            return String.valueOf(LocalDate.now());
        }else{
            return date;
        }
    }
}
