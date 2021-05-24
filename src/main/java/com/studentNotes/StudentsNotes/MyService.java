package com.studentNotes.StudentsNotes;

import com.studentNotes.StudentsNotes.AdminEntities.Admin;
import com.studentNotes.StudentsNotes.AdminEntities.Course;
import com.studentNotes.StudentsNotes.AdminEntities.CourseDetails;
import com.studentNotes.StudentsNotes.AdminRepo.AdminRepo;
import com.studentNotes.StudentsNotes.AdminRepo.CourseRepo;
import com.studentNotes.StudentsNotes.AdminService.Login;
import com.studentNotes.StudentsNotes.StudentEntities.Student;
import com.studentNotes.StudentsNotes.StudentRepo.StudentRepo;
import com.studentNotes.StudentsNotes.TeacherEntities.Teacher;
import com.studentNotes.StudentsNotes.TeacherRepo.TeacherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class MyService {
    @Autowired
    CourseRepo courseRepo;
    @Autowired
    TeacherRepo teacherRepo;
    @Autowired
    StudentRepo studentRepo;
    @Autowired
    AdminRepo adminRepo;
    @Autowired
    Admin adminn;
    public ArrayList<Course> getCourses(){
        return courseRepo.getAllCourse();
    }
    public boolean resetPassword(Login login){
        Optional<Admin> admin11=adminRepo.findById("admin177001@gmail.com");
        if(admin11.isPresent()){

        }
        else{
            adminn.setAdminName("Admin");
            adminn.setAdminPassword("admin177001");
            adminn.setAdminRole("ADMIN_ROLE");
            adminn.setAdminEmail("admin177001@gmail.com");
            adminRepo.save(adminn);
        }
        Optional<Teacher> teacher =teacherRepo.findById(login.getUserEmail());
        Optional<Student> student=studentRepo.findById(login.getUserEmail());
        Optional<Admin> admin=adminRepo.findById(login.getUserEmail());
        if(teacher.isPresent()){
            Teacher teacher1=teacher.get();
            teacher1.setPassword(login.getUserPassword());
            return true;
        }
        else if(student.isPresent()){
            Student  student1=student.get();
            student1.setPassword(login.getUserPassword());
            return true;
        }
        else if(admin.isPresent()){
            Admin admin1=admin.get();
            admin1.setAdminPassword(login.getUserPassword());
            adminRepo.save(admin1);
            return true;
        }
        else {
            return false;
        }
    }
}
