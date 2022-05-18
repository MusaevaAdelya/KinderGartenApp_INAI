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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;
    private final DirectorRepository directorRepository;
    private final FileStorageImpl fileStorage;

    public void updateUser(String newFullname, String newEmail, String newPassword, String accountType, Long userId) throws IOException {
        if(accountType.equals(AccountType.STUDENT.getAccountType())){
            Student student=studentRepository.findById(userId).orElseThrow();
            student.setEmail(newEmail);
            student.setFullname(newFullname);
            student.setPassword(newPassword);
            studentRepository.save(student);


        }else if(accountType.equals(AccountType.TEACHER.getAccountType())){
            Teacher teacher=teacherRepository.findById(userId).orElseThrow();
            teacher.setEmail(newEmail);
            teacher.setFullname(newFullname);
            teacher.setPassword(newPassword);
            teacherRepository.save(teacher);

        }else{
            Director director=directorRepository.findById(userId).orElseThrow();
            director.setEmail(newEmail);
            director.setFullname(newFullname);
            director.setPassword(newPassword);
            directorRepository.save(director);

        }

    }


    public void changeAvatar(InputStream newPicture, Long userId, String accountType) throws IOException {
        if(accountType.equals(AccountType.STUDENT.getAccountType())){
            Student student=studentRepository.findById(userId).orElseThrow();
            String oldPictureName=student.getPicture();
            String newPictureName= fileStorage.save(newPicture);
            student.setPicture(newPictureName);
            File file = new File("avatars/"+oldPictureName);
            file.delete();
            studentRepository.save(student);


        }else if(accountType.equals(AccountType.TEACHER.getAccountType())){
            Teacher teacher=teacherRepository.findById(userId).orElseThrow();
            String oldPictureName=teacher.getPicture();
            String newPictureName= fileStorage.save(newPicture);
            teacher.setPicture(newPictureName);
            File file = new File("avatars/"+oldPictureName);
            file.delete();
            teacherRepository.save(teacher);

        }else{
            Director director=directorRepository.findById(userId).orElseThrow();
            String oldPictureName=director.getPicture();
            String newPictureName= fileStorage.save(newPicture);
            director.setPicture(newPictureName);
            File file = new File("avatars/"+oldPictureName);
            file.delete();
            directorRepository.save(director);

        }

    }
}
