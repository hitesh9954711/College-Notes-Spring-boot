package com.studentNotes.StudentsNotes.TeacherEntities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
@Getter
@Setter
@Entity
public class TeacherRequest {
    private String name;
    @Id
    private String email;
    private ArrayList<String> courses;
}
