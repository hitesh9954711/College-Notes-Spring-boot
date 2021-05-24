package com.studentNotes.StudentsNotes.AdminRepo;

import com.studentNotes.StudentsNotes.AdminEntities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Repository
public interface CourseRepo extends JpaRepository<Course,String> {

    @Query("from Course")
    public ArrayList<Course> getAllCourse();
    @Query("from Course where course=:course")
    public ArrayList<Course> getOneCourse(@RequestParam("course") String course);
    @Query("from Course where course=:course")
    public Course getOneCourse1(@RequestParam("course") String course);
}
