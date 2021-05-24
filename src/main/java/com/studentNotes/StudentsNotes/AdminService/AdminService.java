package com.studentNotes.StudentsNotes.AdminService;

import com.studentNotes.StudentsNotes.AdminEntities.*;
import com.studentNotes.StudentsNotes.AdminRepo.AdminRepo;
import com.studentNotes.StudentsNotes.Mail.MailServicesImp;
import com.studentNotes.StudentsNotes.Mail.PasswordGenerator;
import com.studentNotes.StudentsNotes.StudentEntities.Student;
import com.studentNotes.StudentsNotes.StudentRepo.StudentRepo;
import com.studentNotes.StudentsNotes.TeacherEntities.Teacher;
import com.studentNotes.StudentsNotes.TeacherRepo.TeacherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.Optional;

@Service
public class AdminService {
    @Autowired
    private MailServicesImp mailServicesImp;
    @Autowired
    private AdminRepo adminRepo;
    @Autowired
    private TeacherRepo teacherRepo;
    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private Admin admin;
    public boolean addAdmin(AdminRequestDto adminRequestDto){
        Optional<Admin> optional=adminRepo.findById(adminRequestDto.getEmail());
        Optional<Teacher> optionalTeacherRepo=teacherRepo.findById(adminRequestDto.getEmail());
        Optional<Student> optionalStudent=studentRepo.findById(adminRequestDto.getEmail());
        if(optional.isPresent() || optionalTeacherRepo.isPresent() || optionalStudent.isPresent()){
            return false;
        }
        else{
            admin.setAdminEmail(adminRequestDto.getEmail());
            admin.setAdminName(adminRequestDto.getName());
            admin.setAdminRole("ROLE_ADMIN");
            admin.setAdminPassword(PasswordGenerator.generate(10));
            try {
                mailServicesImp.send(admin.getAdminEmail(),"collegenotesntes@gmail.com","ADMIN PASSWORD","Admin Password "+admin.getAdminPassword());
                adminRepo.save(admin);
                return true;
            } catch (MessagingException e) {
                return false;
            }

        }
    }
    public boolean deleteAdmn(OneSubject oneSubject){
        Optional<Admin> admin=adminRepo.findById(oneSubject.getCourse());
        if(admin.isPresent()){
            adminRepo.deleteById(oneSubject.getCourse());
            return true;
        }
        else{
            return  false;
        }
    }
    public boolean adminUpdateService(AdminUpdateRequestDto adminUpdateRequestDto){
        Optional<Admin> optional=adminRepo.findById(adminUpdateRequestDto.getEmail());
        if(optional.isPresent()){
            Admin admin=optional.get();
            admin.setAdminName(adminUpdateRequestDto.getName());
            admin.setAdminPassword(adminUpdateRequestDto.getPassword());
            adminRepo.save(admin);
            return true;
        }
        else{
            return false;
        }
    }
}
