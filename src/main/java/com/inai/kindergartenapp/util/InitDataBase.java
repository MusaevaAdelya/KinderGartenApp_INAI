package com.inai.kindergartenapp.util;

import com.inai.kindergartenapp.entity.*;
import com.inai.kindergartenapp.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Configuration
public class InitDataBase {
    @Bean
    CommandLineRunner initData(DirectorRepository directorRepository, TeacherRepository teacherRepository, StudentRepository studentRepository, SubjectRepository subjectRepository, ClassroomRepository classroomRepository) {
        return args -> {

            Random rnd = new Random();

            directorRepository.save(Director.builder()
                    .fullname("Albus Dumbledore")
                    .email("albus.dumbledore@gmail.com")
                    .password("qwerty")
                    .picture("dumbledore.jpg")
                    .build());


            List<String> teacherNames = new ArrayList<>();
            teacherNames.add("Minerva McGonagall");
            teacherNames.add("Remus Lupin");
            teacherNames.add("Rubeus Hagrid");
            teacherNames.add("Rolanda Hooch");
            teacherNames.add("Severus Snape");

            List<String> teachersPhoto=new ArrayList<>(List.of("mcgonagall.jpeg","lupin.jpg","hadgrid.jpg","hooch.jpg","snape.jpg"));

            List<Teacher> teachers = new ArrayList<>();

            for(int i=0;i<(teacherNames.size());i++){
                String[] fullName = teacherNames.get(i).split(" ");
                teachers.add(Teacher.builder()
                        .fullname(teacherNames.get(i))
                        .email(fullName[0].toLowerCase()+"."+fullName[1].toLowerCase() + "@gmail.com")
                        .password("qwerty")
                        .picture(teachersPhoto.get(i))
                        .build());
            }

            List<String> subjectNames = new ArrayList<>();
            subjectNames.add("Metamorphosis");
            subjectNames.add("Defence Against the Dark Arts");
            subjectNames.add("Care of Magical Creatures");
            subjectNames.add("Flying");
            subjectNames.add("Potions");

            List<Subject> subjects = new ArrayList<>();
            for (int i = 0; i < subjectNames.size(); i++) {
                subjects.add(Subject.builder()
                        .name(subjectNames.get(i))
                        .teacher(teachers.get(i))
                        .build());
            }


            List<String> studentNames = new ArrayList<>();
            studentNames.add("Harry Potter");
            studentNames.add("Hermione Granger");
            studentNames.add("Ronald Weasley");
            studentNames.add("Draco Malfoy");
            studentNames.add("Luna Lovegood");
            studentNames.add("Fred Weasley");
            studentNames.add("George Weasley");
            studentNames.add("Tom Riddle");
            studentNames.add("Cedric Diggory");
            studentNames.add("Viktor Krum");
            studentNames.add("Ginevra Weasley");

            List<String> studentPhotos=new ArrayList<>(List.of("potter.jpg","granger.jpeg","ron.jpg","malfoy.jpg","lovegood.jpg","fred.jpg","george.jpg","riddle.jpg","diggory.jpg","krum.jpg","ginerva.jpg"));

            List<Student> students = new ArrayList<>();

            for(int i=0;i<(studentNames.size());i++){
                String[] fullName = studentNames.get(i).split(" ");
                students.add(Student.builder()
                        .fullname(studentNames.get(i))
                        .email(fullName[0].toLowerCase()+"."+fullName[1].toLowerCase() + "@gmail.com")
                        .password("qwerty")
                                .picture(studentPhotos.get(i))
                        .build());
            }

            List<Classroom> classrooms = new ArrayList<>();

            for (Subject subject : subjects) {
                List<Student> randomStudents = new ArrayList<>();
                List<Student> allStudents = new ArrayList<>(students);
                for (int i = 0; i < (rnd.nextInt(allStudents.size()) + 1); i++) {
                    int rndNum = rnd.nextInt(allStudents.size());
                    Student rndStudent = allStudents.get(rndNum);
                    randomStudents.add(rndStudent);
                    allStudents.remove(rndStudent);
                }

                classrooms.add(Classroom.builder()
                        .subject(subject)
                        .students(randomStudents)
                        .build());
            }

            studentRepository.saveAll(students);
            teacherRepository.saveAll(teachers);
            subjectRepository.saveAll(subjects);
            classroomRepository.saveAll(classrooms);
        };


    }
}
