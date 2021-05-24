package com.studentNotes.StudentsNotes.TeacherService;

import com.studentNotes.StudentsNotes.AdminEntities.Admin;
import com.studentNotes.StudentsNotes.AdminEntities.AdminUpdateRequestDto;
import com.studentNotes.StudentsNotes.AdminRepo.AdminRepo;
import com.studentNotes.StudentsNotes.TeacherEntities.Teacher;
import com.studentNotes.StudentsNotes.TeacherEntities.TeacherRequest;
import com.studentNotes.StudentsNotes.TeacherRepo.TeacherRepo;
import com.studentNotes.StudentsNotes.TeacherRepo.TeacherRequestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TeacherRequestService {
    @Autowired
    TeacherRequestRepo teacherRequestRepo;
    @Autowired
    AdminRepo adminRepo;
    @Autowired
    TeacherRepo teacherRepo;
    public boolean saveTeacherRequest(TeacherRequest teacherRequest){
        Optional<TeacherRequest> optional=teacherRequestRepo.findById(teacherRequest.getEmail());
        Optional<Admin> adminO=adminRepo.findById(teacherRequest.getEmail());
        if(optional.isPresent() || adminO.isPresent()){
            return false;
        }
        else{
            teacherRequestRepo.save(teacherRequest);
            return true;
        }
    }
    public boolean updateTeacher(AdminUpdateRequestDto adminUpdateRequestDto){
        Optional<Teacher> teacher=teacherRepo.findById(adminUpdateRequestDto.getEmail());
        if(teacher.isPresent()){
            Teacher t=teacher.get();
            t.setName(adminUpdateRequestDto.getName());
            t.setPassword(adminUpdateRequestDto.getPassword());
            teacherRepo.save(t);
            return true;
        }
        else{
            return false;
        }
    }
}
