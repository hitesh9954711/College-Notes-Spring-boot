package com.studentNotes.StudentsNotes.AdminService;

import com.studentNotes.StudentsNotes.AdminEntities.Admin;
import com.studentNotes.StudentsNotes.AdminRepo.AdminRepo;
import com.studentNotes.StudentsNotes.StudentEntities.Student;
import com.studentNotes.StudentsNotes.StudentEntities.StudentUserDetails;
import com.studentNotes.StudentsNotes.StudentRepo.StudentRepo;
import com.studentNotes.StudentsNotes.TeacherEntities.Teacher;
import com.studentNotes.StudentsNotes.TeacherEntities.TeacherUserDetails;
import com.studentNotes.StudentsNotes.TeacherRepo.TeacherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
public class AdminUserDetailService implements UserDetailsService {

    @Autowired
    AdminRepo adminRepo;
    @Autowired
    TeacherRepo teacherRepo;
    @Autowired
    StudentRepo studentRepo;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
                Optional<Admin> admin=adminRepo.findById(s);
                Optional<Teacher> teacher=teacherRepo.findById(s);
                Optional<Student> student=studentRepo.findById(s);
                Admin admin1=null;
                Teacher teacher1=null;
                Student student1=null;
                try{
                    admin1=admin.get();
                }
                catch (Exception e){

                }
                try{
                    teacher1=teacher.get();
                }
                catch (Exception e){

                }
                if(admin.isPresent()){
                    AdminUserDetail adminUserDetail=new AdminUserDetail(admin1,admin1.getAdminName(),admin1.getAdminRole());
                    return adminUserDetail;
                }
                else if(teacher.isPresent()){
                    TeacherUserDetails teacherUserDetails=new TeacherUserDetails(teacher1,teacher1.getName(),teacher1.getRole());
                    return teacherUserDetails;
                }
                else if(student.isPresent()){
                    student1=student.get();
                    StudentUserDetails studentUserDetails=new StudentUserDetails(student1,student1.getName(),student1.getRole());
                    return studentUserDetails;
                }
                else{
                    return null;
                }

            }

}
