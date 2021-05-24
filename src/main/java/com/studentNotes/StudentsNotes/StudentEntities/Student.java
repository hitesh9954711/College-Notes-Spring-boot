package com.studentNotes.StudentsNotes.StudentEntities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Id;

@Component
@Entity
@Setter
@Getter
public class Student {
    private String name;
    @Id
    private String email;
    private String password;
    private String courses;
    private String role;
}
