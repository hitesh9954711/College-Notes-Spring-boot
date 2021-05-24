package com.studentNotes.StudentsNotes.StudentEntities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class StudentRequestDto {
    private String name;
    private String email;
    private String password;
    private String courses;
}
