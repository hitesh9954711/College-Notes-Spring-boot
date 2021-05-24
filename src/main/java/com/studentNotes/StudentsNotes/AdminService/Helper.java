package com.studentNotes.StudentsNotes.AdminService;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Helper {
    private String name;
    private String email;
    private String role;
    public Helper(String name,String email,String role){
        this.name=name;
        this.email=email;
        this.role=role;
    }
}
