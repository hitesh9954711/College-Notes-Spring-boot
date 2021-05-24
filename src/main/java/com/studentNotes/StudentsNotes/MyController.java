package com.studentNotes.StudentsNotes;


import com.studentNotes.StudentsNotes.AdminEntities.Admin;
import com.studentNotes.StudentsNotes.AdminService.*;
import com.studentNotes.StudentsNotes.JwtUtil.JwtResponse;
import com.studentNotes.StudentsNotes.JwtUtil.JwtResponseForTeacher;
import com.studentNotes.StudentsNotes.JwtUtil.JwtUtil;
import com.studentNotes.StudentsNotes.StudentEntities.Helper3;
import com.studentNotes.StudentsNotes.StudentEntities.StudentRequestDto;
import com.studentNotes.StudentsNotes.StudentRepo.StudentRepo;
import com.studentNotes.StudentsNotes.StudentService.StudentService;
import com.studentNotes.StudentsNotes.TeacherEntities.Helper1;
import com.studentNotes.StudentsNotes.TeacherEntities.TeacherRequest;
import com.studentNotes.StudentsNotes.TeacherRepo.TeacherRepo;
import com.studentNotes.StudentsNotes.TeacherService.JwtResponseForStudent;
import com.studentNotes.StudentsNotes.TeacherService.TeacherRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@RestController
@CrossOrigin
public class MyController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private AdminUserDetailService adminUserDetailService;
    @Autowired
    private  JwtUtil jwtUtil;
    @Autowired
    private AdminRepoService adminRepoService;
    @Autowired
    private MyService myService;
    @Autowired
    private TeacherRequestService teacherRequestService;
    @Autowired
    private AdminTeacherService adminTeacherService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentRepo studentRepo;
    @PostMapping("/doLogin")
    public ResponseEntity login(@RequestBody Login login){
        String name="";
        String role="";
        String email="";
        String token="";
        ArrayList<String> courses=null;
        String c="";
        try{
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getUserEmail(),login.getUserPassword()));
            UserDetails userDetails=adminUserDetailService.loadUserByUsername(login.getUserEmail());
            String actualRole="";
            List<SimpleGrantedAuthority> authorities= (List<SimpleGrantedAuthority>) userDetails.getAuthorities();
            SimpleGrantedAuthority simpleGrantedAuthority=authorities.get(0);
            actualRole=simpleGrantedAuthority.getAuthority();
            if(actualRole.equals("ROLE_ADMIN")){
                Helper helper=adminRepoService.getName(login.getUserEmail());
                 name=helper.getName();
                 role=helper.getRole();
                 email=helper.getEmail();
                 token=jwtUtil.generateToken(userDetails);
            }
            else if(actualRole.equals("ROLE_TEACHER")){
                Helper1 helper=adminTeacherService.getName(login.getUserEmail());
                 name=helper.getName();
                 role=helper.getRole();
                 email=helper.getEmail();
                 courses=helper.getCourses();
                 token=jwtUtil.generateToken(userDetails);
            }
            else if(actualRole.equals("ROLE_STUDENT")){
                Helper3 helper=studentService.getName(login.getUserEmail());
                name=helper.getName();
                role=helper.getRole();
                email=helper.getEmail();
                c=helper.getCourses();
                token=jwtUtil.generateToken(userDetails);
            }

            return ResponseEntity.accepted().body(new JwtResponseForStudent(token,name,email,role,c));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("credential is not correct");
        }
    }
    @GetMapping("/getCourses")
    public ResponseEntity getCourse(){
        return ResponseEntity.accepted().body(myService.getCourses());
    }
    @PostMapping("/saveTeacherRequest")
    public ResponseEntity saveTeacherRequest(@RequestBody TeacherRequest teacherRequest){
        if(teacherRequestService.saveTeacherRequest(teacherRequest)){
            return ResponseEntity.ok().build();
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
    }
    @PostMapping("/saveStudent")
    public ResponseEntity saveStudent(@RequestBody StudentRequestDto studentRequestDto){
        if(studentService.addStudent(studentRequestDto)){
            return ResponseEntity.ok().build();
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
    }
    @PostMapping("/resetPassword")
    public ResponseEntity reset(@RequestBody Login login){
        if(myService.resetPassword(login)){
            return ResponseEntity.ok().build();
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
    }
}
