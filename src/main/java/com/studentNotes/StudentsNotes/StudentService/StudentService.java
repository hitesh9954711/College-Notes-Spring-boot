package com.studentNotes.StudentsNotes.StudentService;

import com.studentNotes.StudentsNotes.AdminEntities.Admin;
import com.studentNotes.StudentsNotes.AdminEntities.AdminUpdateRequestDto;
import com.studentNotes.StudentsNotes.AdminRepo.AdminRepo;
import com.studentNotes.StudentsNotes.StudentEntities.Helper3;
import com.studentNotes.StudentsNotes.StudentEntities.Student;
import com.studentNotes.StudentsNotes.StudentEntities.StudentRequestDto;
import com.studentNotes.StudentsNotes.StudentRepo.StudentRepo;
import com.studentNotes.StudentsNotes.TeacherEntities.Helper1;
import com.studentNotes.StudentsNotes.TeacherEntities.Teacher;
import com.studentNotes.StudentsNotes.TeacherRepo.TeacherRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class StudentService {
    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private AdminRepo adminRepo;
    @Autowired
    private TeacherRepo teacherRepo;
    @Autowired
    private Student student;
    Random random=new Random();
    public boolean addStudent(StudentRequestDto studentRequestDto){
        Optional<Admin> adminOptional=adminRepo.findById(studentRequestDto.getEmail());
        Optional<Teacher> teacherOptional=teacherRepo.findById(studentRequestDto.getEmail());
        if(adminOptional.isPresent()){
            return false;
        }
        else if(teacherOptional.isPresent()){
            return false;
        }
        else{
            BeanUtils.copyProperties(studentRequestDto,student);
            student.setRole("ROLE_STUDENT");
            studentRepo.save(student);
            return true;
        }

    }
    public Helper3 getName(String email){
        Optional<Student> student=studentRepo.findById(email);
        if(student.isPresent()){
            Student student1=student.get();
            Helper3 helper=new Helper3(student1.getName(),student1.getEmail(),student1.getRole(),student1.getCourses());
            return helper;
        }
        else{
            return null;
        }
    }
    public boolean update(AdminUpdateRequestDto adminUpdateRequestDto){
        Optional<Student> student=studentRepo.findById(adminUpdateRequestDto.getEmail());
        if(student.isPresent()){
            Student s=student.get();
            s.setName(adminUpdateRequestDto.getName());
            s.setPassword(adminUpdateRequestDto.getPassword());
            studentRepo.save(s);
            return true;
        }
        else{
            return false;
        }
    }
}
