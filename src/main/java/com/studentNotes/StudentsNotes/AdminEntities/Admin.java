package com.studentNotes.StudentsNotes.AdminEntities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity
@Component
public class Admin {
    @Id
    private String adminEmail;
    private String adminName;
    private String adminRole;
    private String adminPassword;
}
