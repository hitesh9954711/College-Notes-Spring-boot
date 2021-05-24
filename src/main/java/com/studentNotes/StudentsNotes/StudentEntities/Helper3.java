package com.studentNotes.StudentsNotes.StudentEntities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
@Getter
@Setter
@ToString
public class Helper3 {
    private String name;
    private String email;
    private String role;
    private String courses;
    public Helper3(String name,String email,String role,String courses){
        this.name=name;
        this.email=email;
        this.role=role;
        this.courses=courses;
    }
}
