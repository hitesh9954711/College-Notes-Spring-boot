package com.studentNotes.StudentsNotes.TeacherEntities;

import com.studentNotes.StudentsNotes.AdminEntities.Course;
import com.studentNotes.StudentsNotes.AdminEntities.CourseDetails;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Setter
@Getter
@ToString
@Component
public class TeacherCourseResponseDto {
    ArrayList<Course> courseFromCourse=new ArrayList<Course>();
    ArrayList<ArrayList<CourseDetails>> courseDetails=new ArrayList<ArrayList<CourseDetails>>();
}
