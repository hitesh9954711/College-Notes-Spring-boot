package com.studentNotes.StudentsNotes.AdminEntities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;

@Entity
@Getter
@Setter
@ToString
public class Course {
    @Id
    private String course;
    private ArrayList<String> semesters;
}
