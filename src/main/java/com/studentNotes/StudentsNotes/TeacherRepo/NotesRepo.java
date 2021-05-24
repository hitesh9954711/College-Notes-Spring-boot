package com.studentNotes.StudentsNotes.TeacherRepo;

import com.studentNotes.StudentsNotes.TeacherEntities.DailyNotes;
import com.studentNotes.StudentsNotes.TeacherEntities.Notes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Repository
public interface NotesRepo extends JpaRepository<Notes,Integer> {
    @Query("from Notes where  course=:course and teacher_email=:teacher_email")
    public ArrayList<Notes> getAllSemesterNotes( @RequestParam("course") String course,@RequestParam("teacher_email") String teacher_email);
    @Query("from Notes where  course=:course")
    public ArrayList<Notes> getAllSemesterNotes1( @RequestParam("course") String course);
}
