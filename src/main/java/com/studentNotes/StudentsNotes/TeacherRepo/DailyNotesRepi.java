package com.studentNotes.StudentsNotes.TeacherRepo;

import com.studentNotes.StudentsNotes.TeacherEntities.DailyNotes;
import com.studentNotes.StudentsNotes.TeacherEntities.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Repository
public interface DailyNotesRepi extends JpaRepository<DailyNotes,Integer> {

    @Query("from DailyNotes where date=:date and course=:course and teacher_email=:teacher_email")
    public ArrayList<DailyNotes> getDailyNotes(@RequestParam("date") String date,@RequestParam("course") String course,@RequestParam("teacher_email") String teacher_email);
    @Query("from DailyNotes where  teacher_email=:teacher_email and course=:course")
    public ArrayList<DailyNotes> getAllDailyNotes(@RequestParam("teacher_email") String teacher_email,@RequestParam("course") String course);
    @Query("from DailyNotes where course=:course")
    public ArrayList<DailyNotes> getAllStudentDailyNotes(@RequestParam("course") String course);
}
