package com.studentNotes.StudentsNotes.TeacherEntities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class NotesDeleteRequestDtoForOneNote {
    private int id;
    private int index;
    private boolean check;
}
