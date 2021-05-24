package com.studentNotes.StudentsNotes.TeacherService;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
@Setter
@Getter
@ToString
public class JwtResponseForStudent {
    private String token;
    private String name;
    private String email;
    private String role;
    private String courses;
    public JwtResponseForStudent(String token, String name, String email, String role, String courses){
        this.token=token;
        this.name=name;
        this.email=email;
        this.role=role;
        this.courses=courses;
    }
}
