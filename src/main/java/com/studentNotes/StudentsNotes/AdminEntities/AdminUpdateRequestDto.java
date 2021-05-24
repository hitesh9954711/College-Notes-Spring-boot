package com.studentNotes.StudentsNotes.AdminEntities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class AdminUpdateRequestDto {
    private String name;
    private String email;
    private String password;
}
