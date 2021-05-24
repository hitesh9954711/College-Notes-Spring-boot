package com.studentNotes.StudentsNotes.AdminRepo;

import com.studentNotes.StudentsNotes.AdminEntities.Course;
import com.studentNotes.StudentsNotes.AdminEntities.CourseDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Repository
public interface CourseDetailRepo extends JpaRepository<CourseDetails,Integer> {
    @Query("from CourseDetails")
    public ArrayList<CourseDetails> getAllCourse();
    @Query("from CourseDetails where course=:course")
    public ArrayList<CourseDetails> getCourseDetails(@RequestParam("course") String course);

}
