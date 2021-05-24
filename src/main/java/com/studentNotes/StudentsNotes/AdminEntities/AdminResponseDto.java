package com.studentNotes.StudentsNotes.AdminEntities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import javax.persistence.Id;


@Getter
@Setter
@ToString
public class AdminResponseDto {
    private String adminEmail;
    private String adminName;
}
