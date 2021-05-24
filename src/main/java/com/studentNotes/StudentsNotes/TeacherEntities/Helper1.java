package com.studentNotes.StudentsNotes.TeacherEntities;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;

@Getter
@Setter
@ToString
public class Helper1 {
    private String name;
    private String email;
    private String role;
    private ArrayList<String> courses;
    public Helper1(String name,String email,String role,ArrayList<String> courses){
        this.name=name;
        this.email=email;
        this.role=role;
        this.courses=courses;
    }
}
