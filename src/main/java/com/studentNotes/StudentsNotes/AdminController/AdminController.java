package com.studentNotes.StudentsNotes.AdminController;

import com.studentNotes.StudentsNotes.AdminEntities.*;
import com.studentNotes.StudentsNotes.AdminService.AdminCourseService;
import com.studentNotes.StudentsNotes.AdminService.AdminService;
import com.studentNotes.StudentsNotes.AdminService.AdminTeacherService;
import com.studentNotes.StudentsNotes.TeacherEntities.Teacher;
import com.studentNotes.StudentsNotes.TeacherEntities.TeacherRequest;
import com.studentNotes.StudentsNotes.TeacherEntities.TeacherResponseDto;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/admin")
@CrossOrigin
public class AdminController {

    @Autowired
    private AdminCourseService adminCourseService;
    @Autowired
    private AdminTeacherService adminTeacherService;
    @Autowired
    private AdminService adminService;
    @PostMapping("/test")
    public ResponseEntity test(){
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @PostMapping("/addCourse")
    public ResponseEntity addCourse(@RequestBody Course course){
        if(adminCourseService.saveCourse(course)){
            System.out.println("course already exits");
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Course Already exits");
        }
        else{
            System.out.println("course is saved  successfully");
            return ResponseEntity.ok().build();
        }
    }
    @GetMapping("/courseDetails")
    public ResponseEntity courseDetails(){
        return ResponseEntity.accepted().body(adminCourseService.getCourses());
    }
    @PostMapping("/addCourseDetails")
    public ResponseEntity addCourseDetails(@RequestBody CourseDetails courseDetails){
        if(adminCourseService.addCoursesDetails(courseDetails)){
            return ResponseEntity.ok().build();
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("courseDetails already exits");
        }
    }
    @GetMapping("/getCoursesDetails")
    public ResponseEntity getCoursesDetails(){
        return ResponseEntity.accepted().body(adminCourseService.getCourseDetails());
    }
    @PostMapping("/getCourseDetails")
    public ResponseEntity getCourseDetails(@RequestBody OneSubject course){
        return ResponseEntity.accepted().body(adminCourseService.getCourseDetail(course.getCourse()));
    }
    @PostMapping("/getSubjectDetails")
    public ResponseEntity getSubject(@RequestBody OneSubject course){
        return ResponseEntity.accepted().body(adminCourseService.getSubjectDetail(course.getCourse()));
    }
    @GetMapping("/getSubjectsDetails")
    public ResponseEntity getSubjectsDetails(){
        return ResponseEntity.accepted().body(adminCourseService.getSubjectsDetails());
    }
    @PostMapping("/deleteSubject")
    public ResponseEntity deleteSubject(@RequestBody DeleteH deleteH){
        if(adminCourseService.deleteSubject(deleteH)) {
            System.out.println("ok");
            return ResponseEntity.ok().build();
        }
        else{
            System.out.println("not ok");
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
    }
    @PostMapping("/deleteSemester")
    public ResponseEntity deleteSemester(@RequestBody DeleteSemester deleteSemester){
        if(adminCourseService.deleteSemester(deleteSemester.getId())) {
            System.out.println("ok");
            return ResponseEntity.ok().build();
        }
        else{
            System.out.println("not ok");
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
    }
    @PostMapping("/updateSemester")
    public ResponseEntity updateSemester(@RequestBody UpdateSemester updateSemester){
            if(adminCourseService.updateSemester(updateSemester)){
                return ResponseEntity.ok().build();
            }
            else{
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
            }
    }
    @GetMapping("/getTeachersRequests")
    public ResponseEntity getTeachersRequests(){
        ArrayList<TeacherRequest> teacherRequests=adminTeacherService.getTeachersR();
        return ResponseEntity.accepted().body(teacherRequests);
    }
    @PostMapping("/acceptTeacherRequest")
    public ResponseEntity acceptTeacherRequest(@RequestBody OneSubject email){
        if(adminTeacherService.acceptTeacherReq(email)){
            return ResponseEntity.ok().build();
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
    }
    @PostMapping("/deleteTeacherRequest")
    public ResponseEntity deleteTeacherRequest(@RequestBody OneSubject oneSubject){
        if(adminTeacherService.deleteTeacherReq(oneSubject)){
            return ResponseEntity.ok().build();
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
    }
    @GetMapping("/getTeacherList")
    public ResponseEntity getTeachers(){
        ArrayList<TeacherResponseDto> teacherList=adminTeacherService.getTeacherList();
        return ResponseEntity.accepted().body(teacherList);
    }
    @GetMapping("/getAdminList")
    public ResponseEntity getAdminList(){
        ArrayList<AdminResponseDto> adminResponseDtos=adminTeacherService.getAdminList();
        return ResponseEntity.accepted().body(adminResponseDtos);
    }
    @PostMapping("/addAdmin")
    public ResponseEntity addAdmin(@RequestBody AdminRequestDto adminRequestDto){
        if(adminService.addAdmin(adminRequestDto)){
            return ResponseEntity.accepted().build();
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
    }
    @PostMapping("/deleteAdmin")
    public ResponseEntity deleteAdmin(@RequestBody  OneSubject oneSubject){
        if(adminService.deleteAdmn(oneSubject)){
            return ResponseEntity.accepted().build();
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
    }
    @PostMapping("/deleteTeacher")
    public ResponseEntity deleteT(@RequestBody OneSubject oneSubject){
        if(adminTeacherService.deleteTeacher(oneSubject)){
            return ResponseEntity.accepted().build();
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
    }
    @PostMapping("/deleteCourse")
    public ResponseEntity deleteCourse(@RequestBody OneSubject oneSubject){
        if(adminCourseService.deleteCourseD(oneSubject)){
            return ResponseEntity.accepted().build();
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
    }
    @PostMapping("/adminProfileUpdate")
    public ResponseEntity adminUpdate(@RequestBody AdminUpdateRequestDto adminUpdateRequestDto){
        if(adminService.adminUpdateService(adminUpdateRequestDto)){
            return ResponseEntity.accepted().build();
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
    }
}
