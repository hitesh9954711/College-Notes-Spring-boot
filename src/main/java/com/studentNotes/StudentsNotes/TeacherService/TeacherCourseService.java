package com.studentNotes.StudentsNotes.TeacherService;

import com.studentNotes.StudentsNotes.AdminEntities.Course;
import com.studentNotes.StudentsNotes.AdminEntities.CourseDetails;
import com.studentNotes.StudentsNotes.AdminEntities.OneSubject;
import com.studentNotes.StudentsNotes.AdminRepo.CourseDetailRepo;
import com.studentNotes.StudentsNotes.AdminRepo.CourseRepo;
import com.studentNotes.StudentsNotes.TeacherEntities.Teacher;
import com.studentNotes.StudentsNotes.TeacherEntities.TeacherCourseResponseDto;
import com.studentNotes.StudentsNotes.TeacherRepo.TeacherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Optional;

@Service
public class TeacherCourseService {

    @Autowired
    private CourseRepo courseRepo;
    @Autowired
    private CourseDetailRepo courseDetailsRepo;
    @Autowired
    private TeacherRepo teacherRepo;
    @Autowired
    private TeacherCourseResponseDto teacherCourseResponseDto;
    public TeacherCourseResponseDto getParticularTeacherCourses(OneSubject oneSubject){
        Optional<Teacher> teacher =teacherRepo.findById(oneSubject.getCourse());
        ArrayList<String> courses=new ArrayList<String>();
        ArrayList<Course> courseFromCourse=new ArrayList<Course>();
        ArrayList<ArrayList<CourseDetails>> courseDetails=new ArrayList<ArrayList<CourseDetails>>();
        if(teacher.isPresent()) {
            Teacher teacher1 = teacher.get();
            courses=teacher1.getCourses();
            ListIterator listIterator=courses.listIterator();
            while(listIterator.hasNext()){
                courseFromCourse.add(courseRepo.getOneCourse1((String)listIterator.next()));
            }
            ListIterator listIterator1=courses.listIterator();
            while(listIterator1.hasNext()){
                courseDetails.add(courseDetailsRepo.getCourseDetails((String) listIterator1.next()));
            }
            teacherCourseResponseDto.setCourseFromCourse(courseFromCourse);
            teacherCourseResponseDto.setCourseDetails(courseDetails);
            return teacherCourseResponseDto;
        }
        else{
            return null;
        }
    }
}
