package com.studentNotes.StudentsNotes.AdminService;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class Login {
    private String userEmail;
    private String userPassword;
}
