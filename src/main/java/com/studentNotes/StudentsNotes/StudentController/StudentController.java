package com.studentNotes.StudentsNotes.StudentController;

import com.studentNotes.StudentsNotes.AdminEntities.AdminUpdateRequestDto;
import com.studentNotes.StudentsNotes.AdminEntities.Course;
import com.studentNotes.StudentsNotes.AdminEntities.CourseDetails;
import com.studentNotes.StudentsNotes.AdminEntities.OneSubject;
import com.studentNotes.StudentsNotes.AdminRepo.CourseDetailRepo;
import com.studentNotes.StudentsNotes.AdminRepo.CourseRepo;
import com.studentNotes.StudentsNotes.StudentService.StudentService;
import com.studentNotes.StudentsNotes.TeacherEntities.DailyNotes;
import com.studentNotes.StudentsNotes.TeacherEntities.Notes;
import com.studentNotes.StudentsNotes.TeacherRepo.DailyNotesRepi;
import com.studentNotes.StudentsNotes.TeacherRepo.NotesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/student")
@CrossOrigin
public class StudentController {
    @Autowired
    private DailyNotesRepi dailyNotesRepi;
    @Autowired
    private NotesRepo repo;
    @Autowired
    private CourseRepo courseRepo;
    @Autowired
    private CourseDetailRepo courseDetailRepo;
    @Autowired
    private StudentService studentService;

    @PostMapping("/test")
    public ResponseEntity test(){
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
    @PostMapping("/getStudentDailyNotes")
    public ResponseEntity getAllStudentDailyNotes(@RequestBody OneSubject oneSubject){
        ArrayList<DailyNotes> dailyNotes=dailyNotesRepi.getAllStudentDailyNotes(oneSubject.getCourse());
        return ResponseEntity.accepted().body(dailyNotes);
    }
    @PostMapping("/getStudentSemesterNotes")
    public ResponseEntity getStudentSemesterNotes(@RequestBody OneSubject oneSubject){
        ArrayList<Notes> notes=repo.getAllSemesterNotes1(oneSubject.getCourse());
        return ResponseEntity.accepted().body(notes);
    }
    @PostMapping("/getStudentCourse")
    public ResponseEntity getStudentCourse(@RequestBody OneSubject oneSubject){
        Course course =courseRepo.getOneCourse1(oneSubject.getCourse());
        return ResponseEntity.accepted().body(course);
    }
    @PostMapping("/getStudentCourseDetail")
    public ResponseEntity getStudentCourseDetail(@RequestBody OneSubject oneSubject){
        ArrayList<CourseDetails> course =courseDetailRepo.getCourseDetails(oneSubject.getCourse());
        return ResponseEntity.accepted().body(course);
    }
    @PostMapping("/getStudentUpdate")
    public ResponseEntity studenUpdate(@RequestBody AdminUpdateRequestDto adminUpdateRequestDto){
        if(studentService.update(adminUpdateRequestDto)){
            return ResponseEntity.accepted().build();
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
    }
}
