package com.studentNotes.StudentsNotes.TeacherEntities;

import com.studentNotes.StudentsNotes.AdminEntities.Course;
import com.studentNotes.StudentsNotes.TeacherRepo.TeacherRepo;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@ToString
public class TeacherUserDetails implements UserDetails {
    @Autowired
    private Teacher teacher;
    private String name;
    private String role;
    public TeacherUserDetails(Teacher teacher,String name,String role){
        this.teacher=teacher;
        this.name=name;
        this.role=role;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority simpleGrantedAuthority=new SimpleGrantedAuthority(teacher.getRole());
        return List.of(simpleGrantedAuthority);
    }

    @Override
    public String getPassword() {
        return teacher.getPassword();
    }

    @Override
    public String getUsername() {
        return teacher.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
