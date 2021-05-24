package com.studentNotes.StudentsNotes.AdminService;

import com.studentNotes.StudentsNotes.AdminEntities.*;
import com.studentNotes.StudentsNotes.AdminRepo.AdminRepo;
import com.studentNotes.StudentsNotes.AdminRepo.CourseDetailRepo;
import com.studentNotes.StudentsNotes.AdminRepo.CourseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.ListIterator;
import java.util.Optional;

@Service
public class AdminCourseService {
    @Autowired
    private CourseRepo courseRepo;
    @Autowired
    private CourseDetailRepo courseDetailRepo;
    public boolean saveCourse(Course course){
        course.setCourse(course.getCourse().toUpperCase());
        Optional<Course> course1=courseRepo.findById(course.getCourse());
        if(course1.isPresent())
            return true;
        else
            courseRepo.save(course);
            return false;
    }
    public ArrayList<Course> getCourses(){
        return courseRepo.getAllCourse();
    }
    public ArrayList<CourseDetails> getCourseDetails(){
        return courseDetailRepo.getAllCourse();
    }
    public boolean addCoursesDetails(CourseDetails courseDetails){
        Optional<CourseDetails> courseDetails1=courseDetailRepo.findById(courseDetails.getId());
        if(courseDetails1.isPresent()){
            return false;
        }
        else{
            HashSet<String> set= new HashSet<String>(courseDetails.getSubjects());
            set.remove("");
            System.out.println(set);
            ArrayList<String> list=new ArrayList<String>(set);
            courseDetails.setSubjects(list);
            courseDetailRepo.save(courseDetails);
            return true;
        }
    }
    public ArrayList<CourseDetails> getSubjectsDetails(){
        return  courseDetailRepo.getAllCourse();
    }
    public boolean deleteSubject(DeleteH deleteH){
        Optional<CourseDetails> courseDetails=courseDetailRepo.findById(deleteH.getId());
        CourseDetails courseDetails1=courseDetails.get();
        ArrayList<String> details=courseDetails1.getSubjects();
        details.remove(deleteH.getIndex());
        courseDetails1.setSubjects(details);
        System.out.println(courseDetailRepo.save(courseDetails1));
        return true;
    }
    public ArrayList<Course> getCourseDetail(String course){
        ArrayList<Course> list=courseRepo.getOneCourse(course);
        return list;
    }
    public ArrayList<CourseDetails> getSubjectDetail(String course){
        ArrayList<CourseDetails> list=courseDetailRepo.getCourseDetails(course);
        return list;
    }
    public boolean deleteSemester(int id){
        courseDetailRepo.deleteById(id);
        return true;
    }
    public boolean updateSemester(UpdateSemester updateSemester){
       Optional<CourseDetails> courseDetails =courseDetailRepo.findById(updateSemester.getId());
       if(courseDetails.isPresent()){
           CourseDetails courseDetails1=courseDetails.get();
           ArrayList<String> subjects=courseDetails1.getSubjects();
           subjects.add(updateSemester.getSubject());
           courseDetails1.setSubjects(subjects);
           courseDetailRepo.save(courseDetails1);
           return true;
       }
       else{
            return false;
       }
    }
    public boolean deleteCourseD(OneSubject oneSubject){
        Optional<Course> optional=courseRepo.findById(oneSubject.getCourse());
        if(optional.isPresent()){
            courseRepo.deleteById(optional.get().getCourse());
            ArrayList<CourseDetails> details=courseDetailRepo.getCourseDetails(oneSubject.getCourse());
            for(CourseDetails c:details){
                courseDetailRepo.deleteById(c.getId());
            }
            return true;
        }
        else{
            return false;
        }
    }

}
