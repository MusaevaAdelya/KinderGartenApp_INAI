package com.inai.kindergartenapp.service;


import com.inai.kindergartenapp.entity.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private List<User> users=new ArrayList<>();

    public UserService(){
        users.add(new User("teacher@gmail.com","qwerty","teacher"));
        users.add(new User("mentor@gmail.com","qwerty","mentor"));
        users.add(new User("director@gmail.com","qwerty","director"));
    }

    public List<User> getUsers(){
        return users;
    }

    public boolean checkUser(String userEmail,String userPassword,String accountType){
        boolean res=false;
        for(User u:users){
            if(u.getEmail().equals(userEmail) && u.getPassword().equals(userPassword) && u.getAccountType().equals(accountType)){
                res=true;
            }
        }
        return res;
    }

}
