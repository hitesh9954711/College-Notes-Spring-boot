package com.studentNotes.StudentsNotes.AdminService;

import com.studentNotes.StudentsNotes.AdminEntities.Admin;
import com.studentNotes.StudentsNotes.AdminRepo.AdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminRepoService {
    @Autowired
    private AdminRepo adminRepo;

    public Helper getName(String email){
        Optional<Admin> admin=adminRepo.findById(email);
        Admin admin1=admin.get();
        Helper helper=new Helper(admin1.getAdminName(),admin1.getAdminEmail(),admin1.getAdminRole());
        if(admin.isPresent()){
            return helper;
        }
        else{
            return null;
        }
    }

}
