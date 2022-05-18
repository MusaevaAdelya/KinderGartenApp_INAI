package com.inai.kindergartenapp.util;

import com.inai.kindergartenapp.entity.*;
import com.inai.kindergartenapp.enums.AccountType;
import com.inai.kindergartenapp.repository.*;
import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Configuration
public class InitDataBase {
    @Bean
    CommandLineRunner initData(DirectorRepository directorRepository, TeacherRepository teacherRepository, StudentRepository studentRepository, SubjectRepository subjectRepository, GradeRepository gradeRepository, AttendanceRepository attendanceRepository, HomeworkRepository homeworkRepository, LessonRepository lessonRepository) {
        return args -> {
            lessonRepository.deleteAll();
            homeworkRepository.deleteAll();
            gradeRepository.deleteAll();
            attendanceRepository.deleteAll();
            subjectRepository.deleteAll();
            directorRepository.deleteAll();
            teacherRepository.deleteAll();
            studentRepository.deleteAll();


            Random rnd = new Random();
            Lorem lorem = LoremIpsum.getInstance();

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

            List<String> teachersPhoto = new ArrayList<>(List.of("mcgonagall.jpg", "lupin.jpg", "hagrid.jpg", "hooch.jpg", "snape.jpg"));

            List<Teacher> teachers = new ArrayList<>();

            for (int i = 0; i < (teacherNames.size()); i++) {
                String[] fullName = teacherNames.get(i).split(" ");
                teachers.add(Teacher.builder()
                        .fullname(teacherNames.get(i))
                        .email(fullName[0].toLowerCase() + "." + fullName[1].toLowerCase() + "@gmail.com")
                        .password("qwerty")
                        .picture(teachersPhoto.get(i))
                                .accountType(AccountType.TEACHER.getAccountType())
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

            List<String> studentPhotos = new ArrayList<>(List.of("potter.jpg", "granger.jpg", "ron.jpg", "malfoy.jpg", "lovegood.jpg", "fred.jpg", "fred.jpg", "riddle.jpg", "diggory.jpg", "krum.jpg", "ginevra.jpg"));

            List<Student> students = new ArrayList<>();

            for (int i = 0; i < (studentNames.size()); i++) {
                String[] fullName = studentNames.get(i).split(" ");
                students.add(Student.builder()
                        .fullname(studentNames.get(i))
                        .email(fullName[0].toLowerCase() + "." + fullName[1].toLowerCase() + "@gmail.com")
                        .password("qwerty")
                        .picture(studentPhotos.get(i))
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
                List<Student> randomStudents = new ArrayList<>();
                List<Student> allStudents = new ArrayList<>(students);
                for (int j = 0; j < (rnd.nextInt(students.size() - 5) + 5); j++) {
                    int rndNum = rnd.nextInt(allStudents.size());
                    Student rndStudent = allStudents.get(rndNum);
                    randomStudents.add(rndStudent);
                    allStudents.remove(rndStudent);
                }

                subjects.add(Subject.builder()
                        .name(subjectNames.get(i))
                        .teacher(teachers.get(i))
                        .students(randomStudents)
                        .build());
            }

            List<Grade> grades = new ArrayList<>();
            for (Subject s : subjects) {
                for (int i = 0; i < s.getStudents().size(); i++) {
                    grades.add(Grade.builder()
                            .subject(s)
                            .student(s.getStudents().get(i))
                            .firstGrade(rnd.nextInt(11))
                            .secondGrade(rnd.nextInt(11))
                            .thirdGrade(rnd.nextInt(11))
                            .build());
                }
            }

            List<Attendance> attendances=new ArrayList<>();

            for(Subject subject:subjects){
                for(int i=0;i<LocalDate.now().getDayOfMonth();i++){
                    List<Student> presentStudents=new ArrayList<>();
                    List<Student> absentStudents=new ArrayList<>();

                    for(Student student:subject.getStudents()){
                        if(rnd.nextInt(2)==1){
                            presentStudents.add(student);
                        }else{
                            absentStudents.add(student);
                        }

                    }
                    attendances.add(Attendance.builder()
                                    .date(LocalDate.now().minusDays(i))
                                    .students(presentStudents)
                                    .present(true)
                                    .subject(subject)
                            .build());

                    attendances.add(Attendance.builder()
                            .date(LocalDate.now().minusDays(i))
                            .students(absentStudents)
                            .present(false)
                            .subject(subject)
                            .build());
                }

                for(int i=1;i<=LocalDate.now().plusDays(7).getDayOfMonth();i++){
                    attendances.add(Attendance.builder()
                            .date(LocalDate.now().plusDays(i))
                            .students(subject.getStudents())
                                    .present(false)
                                    .subject(subject)
                            .build());
                }
            }

            List<Homework> homeworks=new ArrayList<>();

            for(Subject subject:subjects){
                for(int i=0;i<LocalDate.now().getDayOfMonth();i++){
                    for(int j=0;j<(rnd.nextInt(5)+5);j++){
                        homeworks.add(Homework.builder()
                                        .subject(subject)
                                        .date(LocalDate.now().minusDays(i))
                                        .task(lorem.getWords(5,20))
                                .build());
                    }
                }
            }

            List<Lesson> lessons=new ArrayList<>();
            List<Subject> subjectsTemp=new ArrayList<>(subjects);
            for(int i=0;i<LocalDate.now().getDayOfMonth();i++){
                Collections.shuffle(subjectsTemp);
                LocalTime startTime=LocalTime.parse("08:00");
                LocalTime endTime=LocalTime.parse("09:30");
                for(Subject subject: subjectsTemp){
                    lessons.add(Lesson.builder()
                                    .startTime(startTime)
                                    .endTime(endTime)
                                    .date(LocalDate.now().minusDays(i))
                                    .subject(subject)
                            .build());

                    startTime=startTime.plusMinutes(90);
                    endTime=endTime.plusMinutes(90);
                }
            }

            studentRepository.saveAll(students);
            teacherRepository.saveAll(teachers);
            subjectRepository.saveAll(subjects);
            gradeRepository.saveAll(grades);
            attendanceRepository.saveAll(attendances);
            homeworkRepository.saveAll(homeworks);
            lessonRepository.saveAll(lessons);
        };


    }
}
