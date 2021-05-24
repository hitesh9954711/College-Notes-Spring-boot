package com.studentNotes.StudentsNotes.TeacherEntities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Getter
@Setter
public class TeacherResponseDto {
    private String name;
    private String email;
    private ArrayList<String> courses;
}
