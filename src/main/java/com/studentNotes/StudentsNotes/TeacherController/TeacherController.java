package com.studentNotes.StudentsNotes.TeacherController;

import com.studentNotes.StudentsNotes.AdminEntities.*;
import com.studentNotes.StudentsNotes.AdminRepo.CourseRepo;
import com.studentNotes.StudentsNotes.Mail.PasswordGenerator;
import com.studentNotes.StudentsNotes.TeacherEntities.*;
import com.studentNotes.StudentsNotes.TeacherRepo.DailyNotesRepi;
import com.studentNotes.StudentsNotes.TeacherRepo.NotesRepo;
import com.studentNotes.StudentsNotes.TeacherService.TeacherCourseService;
import com.studentNotes.StudentsNotes.TeacherService.TeacherNotesService;
import com.studentNotes.StudentsNotes.TeacherService.TeacherRequestService;
import org.apache.coyote.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import static org.springframework.http.HttpHeaders.CONTENT_DISPOSITION;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.beans.Beans;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.Files.copy;
import static java.nio.file.Paths.get;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/teacher")
@CrossOrigin
public class TeacherController {
    @Autowired
    private TeacherCourseService teacherCourseService;
    @Autowired
    private TeacherNotesService teacherNotesService;
    @Autowired
    private NotesRepo notesRepo;
    @Autowired
    private CourseRepo courseRepo;
    @Autowired
    private DailyNotesRepi dailyNotesRepi;
    @Autowired
    private  DailyNotes dailyNotes;
    @Autowired
    private TeacherRequestService teacherRequestService;
    Random r=new Random();
    @PostMapping("/getTeacherCourses")
    public ResponseEntity getTeacherCourses(@RequestBody OneSubject oneSubject){
        TeacherCourseResponseDto teacherCourseResponseDto =teacherCourseService.getParticularTeacherCourses(oneSubject);
        if(teacherCourseResponseDto!=null){
            return ResponseEntity.accepted().body(teacherCourseResponseDto);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
    }
    @PostMapping("/addNotes")
    public ResponseEntity addNotes(@RequestParam("videos") List<MultipartFile> videos, @RequestParam("files") List<MultipartFile> files,
                                   @RequestParam("course") String course, @RequestParam("semester") String semester, @RequestParam("subject") String subject,
                                   @RequestParam("unit") String unit,@RequestParam("check") String check, HttpServletRequest request,@RequestParam("topic") String topic, @RequestParam("date") String date,@RequestParam("email") String email){
      if(teacherNotesService.AddN(videos,files,course,semester,subject,date,topic,unit,check,request,email)){
          return ResponseEntity.accepted().build();
      }
      else{
          return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
      }
    }
    @PostMapping("/getDailyNotes")
    public ResponseEntity getNotes(@RequestBody TeacherNotesResponseDto date){
        ArrayList<DailyNotes> dailyNotes=teacherNotesService.getDailyNotesByDate(date.getDate(),date.getCourse(),date.getEmail());

        return ResponseEntity.accepted().body(dailyNotes);
    }
    @PostMapping("/deleteOneDailyNote")
    public ResponseEntity deleteOneDailyNote(@RequestBody NotesDeleteRequestDtoForOneNote notesDeleteRequestDtoForOneNote){
        if(teacherNotesService.deleteOneNoteFromDailyNotes(notesDeleteRequestDtoForOneNote)){
            return ResponseEntity.accepted().build();
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
    }
    @PostMapping("/deleteSemesterNote")
    public ResponseEntity deleteONeDailyNoteFromSemester(@RequestBody NotesDeleteRequestDtoForOneNote notesDeleteRequestDtoForOneNote,HttpServletRequest request){
        if(teacherNotesService.deleteOneNoteFromSemesterNotes(notesDeleteRequestDtoForOneNote,request)){
            return ResponseEntity.accepted().build();
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
    }
    @PostMapping("/deleteCompleteSemesterNotes")
    public ResponseEntity deleteCompleteNoteFromSemester(@RequestBody DeleteH deleteH,HttpServletRequest request){
        if(teacherNotesService.deleteOneNoteCompletelyFromSemesterNotes(deleteH,request)){
            return ResponseEntity.accepted().build();
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
    }
    @PostMapping("/deleteOneCompleteNote")
    public ResponseEntity deleteOneCompleteNote(@RequestBody DeleteH deleteH){
        if(teacherNotesService.deleteOneNoteCompletelyFromDailyNotes(deleteH)){
            return ResponseEntity.accepted().build();
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
    }
    @PostMapping("/updateNotes")
    public ResponseEntity updateNotes(@RequestParam("id") int id,List<MultipartFile> videos, @RequestParam("files") List<MultipartFile> files,HttpServletRequest request){
        if(videos.size()==0 || files.size()==0){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
        else{
            if(teacherNotesService.AddN(videos,files,id,request)){
                return ResponseEntity.accepted().build();
            }
            else{
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
            }
        }
    }
    @PostMapping("/updateSemesterNotes")
    public ResponseEntity updateSemesterNotes(@RequestParam("id") int id,List<MultipartFile> videos, @RequestParam("files") List<MultipartFile> files,HttpServletRequest request){
        System.out.println("worked");
        if(videos.size()==0 || files.size()==0){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
        else{
            if(teacherNotesService.AddNN(videos,files,id,request)){
                return ResponseEntity.accepted().build();
            }
            else{
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
            }
        }
    }

    @GetMapping("/addNotesToDailyNotes")
    public ResponseEntity add(@RequestParam("id") int id){
        String pattern = "M/dd/yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        System.out.println("worked");
        Optional<Notes> notes=notesRepo.findById(id);
        if(notes.isPresent()){
            Notes notes1=notes.get();
            BeanUtils.copyProperties(notes1,dailyNotes);
            dailyNotes.setDate(simpleDateFormat.format(new Date()));
            int isd=r.nextInt();
            System.out.println(isd);
            dailyNotes.setId(isd);
            dailyNotesRepi.save(dailyNotes);

            System.out.println(dailyNotes.toString());
            return ResponseEntity.accepted().build();
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
    }

    @PostMapping("/getAllDailyNotes")
    public ResponseEntity getAllDailyNotes(@RequestBody SearchNotesDto searchNotesDto){
        ArrayList<DailyNotes> dailyNotes=teacherNotesService.getAllNotes(searchNotesDto);
        if(dailyNotes==null){
            return ResponseEntity.status((HttpStatus.NOT_ACCEPTABLE)).build();
        }
        else {
            return ResponseEntity.accepted().body(dailyNotes);
        }

    }
    @GetMapping("/download")
    public ResponseEntity download(@RequestParam("name") String name,@RequestParam("check") boolean check, HttpServletRequest request, HttpServletResponse httpServletResponse){
        System.out.println(name);
        System.out.println(check);
        try {
            //Path filePath = get(DIRECTORY).toAbsolutePath().normalize().resolve(filename);
            if(check){
                String path1=request.getRealPath("/")+"notesFiles";
                System.out.println(path1);
                Path videoNamePath = Paths.get(path1).toAbsolutePath().normalize().resolve(name);
                System.out.println(videoNamePath.toString());
                if(!Files.exists(videoNamePath)) {
                    throw new FileNotFoundException(videoNamePath + " was not found on the server");
                }
                Resource resource =  new UrlResource(videoNamePath.toUri());
                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.add("File-Name", name);
                httpHeaders.add(CONTENT_DISPOSITION, "attachment;File-Name=" + resource.getFilename());
                System.out.println("worked");
                return ResponseEntity.ok().contentType(MediaType.parseMediaType(Files.probeContentType(videoNamePath)))
                        .headers(httpHeaders).body(resource);
            }
            else{
                String path1=request.getRealPath("/")+"notesVideos";
                System.out.println(path1);
                Path videoNamePath = Paths.get(path1).toAbsolutePath().normalize().resolve(name);
                System.out.println(videoNamePath.toString());
                if(!Files.exists(videoNamePath)) {
                    throw new FileNotFoundException(videoNamePath + " was not found on the server");
                }
                Resource resource =  new UrlResource(videoNamePath.toUri());
                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.add("File-Name", name);
                httpHeaders.add(CONTENT_DISPOSITION, "attachment;File-Name=" + resource.getFilename());
                System.out.println("worked");
                return ResponseEntity.ok().contentType(MediaType.parseMediaType(Files.probeContentType(videoNamePath)))
                        .headers(httpHeaders).body(resource);
            }


        } catch (IOException e) {
            System.out.println("In catch");
            e.printStackTrace();
            return ResponseEntity.status((HttpStatus.NOT_ACCEPTABLE)).build();

        }

    }
    @GetMapping("getAllNotes")
    public ResponseEntity getAllNotes(@RequestParam("course") String course,@RequestParam("email") String email) {
        ArrayList<Notes> notes = teacherNotesService.getAllSemesterNotes(course, email);
        return ResponseEntity.accepted().body(notes);
    }

    @PostMapping("/updateTeacher")
    public ResponseEntity updateTeacher(@RequestBody AdminUpdateRequestDto adminUpdateRequestDto){
        if(teacherRequestService.updateTeacher(adminUpdateRequestDto)){
            return ResponseEntity.accepted().build();
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
    }
}
