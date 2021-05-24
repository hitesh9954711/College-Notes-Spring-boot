package com.studentNotes.StudentsNotes.AdminService;

import com.studentNotes.StudentsNotes.AdminEntities.Admin;
import com.studentNotes.StudentsNotes.AdminEntities.AdminResponseDto;
import com.studentNotes.StudentsNotes.AdminEntities.OneSubject;
import com.studentNotes.StudentsNotes.AdminRepo.AdminRepo;
import com.studentNotes.StudentsNotes.Mail.MailServicesImp;
import com.studentNotes.StudentsNotes.Mail.PasswordGenerator;
import com.studentNotes.StudentsNotes.TeacherEntities.Helper1;
import com.studentNotes.StudentsNotes.TeacherEntities.Teacher;
import com.studentNotes.StudentsNotes.TeacherEntities.TeacherRequest;
import com.studentNotes.StudentsNotes.TeacherEntities.TeacherResponseDto;
import com.studentNotes.StudentsNotes.TeacherRepo.TeacherRepo;
import com.studentNotes.StudentsNotes.TeacherRepo.TeacherRequestRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class AdminTeacherService {
    @Autowired
    private TeacherRequestRepo teacherRequestRepo;
    @Autowired
    private MailServicesImp mailServicesImp;
    @Autowired
    private Teacher teacher;
    @Autowired
    private TeacherRepo teacherRepo;
    @Autowired
    private AdminRepo adminRepo;
    public ArrayList<TeacherRequest> getTeachersR(){
        return (ArrayList<TeacherRequest>) teacherRequestRepo.findAll();
    }

    public boolean acceptTeacherReq(OneSubject oneSubject){
        Optional<TeacherRequest> optional=teacherRequestRepo.findById(oneSubject.getCourse());
        if(optional.isPresent()){
            TeacherRequest teacherRequest=optional.get();
            teacher.setEmail(teacherRequest.getEmail());
            teacher.setCourses(teacherRequest.getCourses());
            teacher.setName(teacherRequest.getName());
            teacher.setRole("ROLE_TEACHER");
            teacher.setPassword(PasswordGenerator.generate(8));
        try{
            mailServicesImp.send(teacherRequest.getEmail(),"collegenotesnotes@gmail.com","Teacher Password",
                    "<p>email</p>" + teacher.getEmail() + "<p>password</p>" + teacher.getPassword());
            teacherRepo.save(teacher);
            teacherRequestRepo.deleteById(teacher.getEmail());
            return true;
        }
        catch (Exception e){
            return false;
        }
        }
        else{
            return false;
        }
    }
    public boolean deleteTeacherReq(OneSubject oneSubject){
        Optional<TeacherRequest> optional=teacherRequestRepo.findById(oneSubject.getCourse());
        if(optional.isPresent()){
            teacherRequestRepo.deleteById(oneSubject.getCourse());
            try{
                mailServicesImp.send(oneSubject.getCourse(),"collegenotesnotes@gmail.com","Teacher Password",
                        "Your Request for teacher is rejected by admin");
                return true;
            }
            catch (Exception e){
                return false;
            }

        }
        else{
            return  false;
        }
    }
    public ArrayList<TeacherResponseDto> getTeacherList(){
        ArrayList<Teacher> all= (ArrayList<Teacher>) teacherRepo.findAll();
        ArrayList<TeacherResponseDto> teacherResponse = new ArrayList<TeacherResponseDto>();
        for(Teacher t:all){
            TeacherResponseDto teacherResponseDto=new TeacherResponseDto();
            teacherResponseDto.setName(t.getName());
            teacherResponseDto.setEmail(t.getEmail());
            teacherResponseDto.setCourses(t.getCourses());
            System.out.println(teacherResponse);
            teacherResponse.add(teacherResponseDto);
        }
        return teacherResponse;
    }
    public ArrayList<AdminResponseDto> getAdminList(){
        ArrayList<Admin> all=(ArrayList<Admin>) adminRepo.findAll();
        ArrayList<AdminResponseDto> adminList=new ArrayList<AdminResponseDto>();
        for(Admin admin:all){
            AdminResponseDto adminResponseDto=new AdminResponseDto();
            adminResponseDto.setAdminEmail(admin.getAdminEmail());
            adminResponseDto.setAdminName(admin.getAdminName());
            adminList.add(adminResponseDto);
        }
        return adminList;
    }
    public boolean deleteTeacher(OneSubject oneSubject){
        Optional<Teacher> teacher=teacherRepo.findById(oneSubject.getCourse());
        if(teacher.isPresent()){
            teacherRepo.deleteById(oneSubject.getCourse());
            return true;
        }
        else{
            return false;
        }
    }

    public Helper1 getName(String email){
        Optional<Teacher> teacher=teacherRepo.findById(email);
        if(teacher.isPresent()){
            Teacher teacher1=teacher.get();
            Helper1 helper=new Helper1(teacher1.getName(),teacher1.getEmail(),teacher1.getRole(),teacher1.getCourses());
            return helper;
        }
        else{
            return null;
        }
    }
}
