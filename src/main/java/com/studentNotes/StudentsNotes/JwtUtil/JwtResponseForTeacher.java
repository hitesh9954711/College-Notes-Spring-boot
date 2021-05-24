package com.studentNotes.StudentsNotes.JwtUtil;

import com.studentNotes.StudentsNotes.AdminEntities.Course;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;

@Setter
@Getter
@ToString
public class JwtResponseForTeacher {
    private String token;
    private String name;
    private String email;
    private String role;
    private ArrayList<String> courses;
    public JwtResponseForTeacher(String token, String name, String email, String role, ArrayList<String> courses){
        this.token=token;
        this.name=name;
        this.email=email;
        this.role=role;
        this.courses=courses;
    }
}
