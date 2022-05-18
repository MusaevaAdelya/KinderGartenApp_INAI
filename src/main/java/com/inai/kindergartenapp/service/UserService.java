package com.inai.kindergartenapp.service;


import com.inai.kindergartenapp.entity.Director;
import com.inai.kindergartenapp.entity.Student;
import com.inai.kindergartenapp.entity.Teacher;
import com.inai.kindergartenapp.enums.AccountType;
import com.inai.kindergartenapp.repository.DirectorRepository;
import com.inai.kindergartenapp.repository.StudentRepository;
import com.inai.kindergartenapp.repository.TeacherRepository;
import com.inai.kindergartenapp.util.FileStorageImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final DirectorRepository directorRepository;
    private final FileStorageImpl fileStorage;

    public List<Student> getStudents(){
        return studentRepository.getAllBy();
    }

    public List<Teacher> getTeachers(){
        return teacherRepository.getAllBy();
    }

    public List<Director> getDirectors(){
        return directorRepository.getAllBy();
    }

    public boolean checkUser(String userEmail,String userPassword,String accountType){
        boolean res=false;
        if(accountType.equals(AccountType.STUDENT.getAccountType())){
            for(Student s:getStudents()){
                if(s.getEmail().equals(userEmail) && s.getPassword().equals(userPassword)){
                    res=true;
                }
            }
        }else if(accountType.equals(AccountType.TEACHER.getAccountType())){
            for(Teacher t:getTeachers()){
                if(t.getEmail().equals(userEmail) && t.getPassword().equals(userPassword)){
                    res=true;
                }
            }
        }else if(accountType.equals(AccountType.DIRECTOR.getAccountType())){
            for(Director d:getDirectors()){
                if(d.getEmail().equals(userEmail) && d.getPassword().equals(userPassword)){
                    res=true;
                }
            }
        }


        return res;
    }

    public boolean checkNewStudent(Student newStudent) {
        if(getAllEmails().contains(newStudent.getEmail())){
            return false;
        }else{
            return true;
        }
    }


    public List<String> getAllEmails(){
        List<String> allEmails=new ArrayList<>();

        allEmails.addAll(getStudents().stream().map(Student::getEmail).collect(Collectors.toList()));
        allEmails.addAll(getTeachers().stream().map(Teacher::getEmail).collect(Collectors.toList()));
        allEmails.addAll(getDirectors().stream().map(Director::getEmail).collect(Collectors.toList()));

        return allEmails;
    }

    public void addNewStudent(Student newStudent) throws IOException {
        studentRepository.save(Student.builder()
                        .fullname(newStudent.getFullname())
                        .email(newStudent.getEmail())
                        .password(newStudent.getPassword())
                        .picture(createAnonPic())
                .build());
    }


    public String createAnonPic() throws IOException {
        return fileStorage.save(new URL("https://i.pinimg.com/474x/90/c8/d0/90c8d0fde36b92c18a6c19a06b4b2735.jpg").openStream());
    }
}
