package com.studentNotes.StudentsNotes.TeacherEntities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Component
@Entity
public class Teacher {
    private String name;
    @Id
    private String email;
    @JsonIgnore
    private String password;
    private ArrayList<String> courses;
    private String role;
    @OneToMany(mappedBy = "teacher",fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Notes> note;

}
