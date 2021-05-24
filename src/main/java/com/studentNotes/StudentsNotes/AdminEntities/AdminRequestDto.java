package com.studentNotes.StudentsNotes.AdminEntities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class AdminRequestDto {
    private String email;
    private String name;
}
