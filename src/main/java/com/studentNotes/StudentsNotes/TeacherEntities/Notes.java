package com.studentNotes.StudentsNotes.TeacherEntities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Entity
@Getter
@Setter
@Component
public class Notes {
    @Id
    private int id;

    private String course;
    private String semester;
    private String subject;
    private String unit;
    private String topic;
    @Lob
    private ArrayList<String> videos;
    @Lob
    private ArrayList<String> files;
    private String date;
    @ManyToOne()
    private Teacher teacher;

    public Notes(String course, String semester, String subject, String unit, String topic, ArrayList<String> images, ArrayList<String> files, Teacher teacher) {
        this.course = course;
        this.semester = semester;
        this.subject = subject;
        this.unit = unit;
        this.topic = topic;
        this.videos = images;
        this.files = files;
        this.teacher = teacher;
    }


    public Notes(){

    }
}