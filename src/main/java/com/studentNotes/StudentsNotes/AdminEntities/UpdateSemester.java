package com.studentNotes.StudentsNotes.AdminEntities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@ToString
@Getter
@Setter
public class UpdateSemester {
    private int id;
    private String subject;
}
