package com.inai.kindergartenapp.service;


import com.inai.kindergartenapp.entity.Director;
import com.inai.kindergartenapp.entity.Student;
import com.inai.kindergartenapp.entity.Teacher;
import com.inai.kindergartenapp.entity.User;
import com.inai.kindergartenapp.enums.AccountType;
import com.inai.kindergartenapp.repository.DirectorRepository;
import com.inai.kindergartenapp.repository.StudentRepository;
import com.inai.kindergartenapp.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final DirectorRepository directorRepository;

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

}
