package com.studentNotes.StudentsNotes.TeacherEntities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.Id;
import java.util.ArrayList;

@Component
@Getter
@Setter
public class TeacherNotesResponseDto {
    private String date;
    private String course;
    private String email;

}
