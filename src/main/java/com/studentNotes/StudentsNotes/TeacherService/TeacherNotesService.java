package com.studentNotes.StudentsNotes.TeacherService;

import com.studentNotes.StudentsNotes.AdminEntities.DeleteH;
import com.studentNotes.StudentsNotes.AdminEntities.OneSubject;
import com.studentNotes.StudentsNotes.AdminEntities.SearchNotesDto;
import com.studentNotes.StudentsNotes.Mail.PasswordGenerator;
import com.studentNotes.StudentsNotes.TeacherEntities.*;
import com.studentNotes.StudentsNotes.TeacherRepo.DailyNotesRepi;
import com.studentNotes.StudentsNotes.TeacherRepo.NotesRepo;
import com.studentNotes.StudentsNotes.TeacherRepo.TeacherRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class TeacherNotesService {
    @Autowired
    private NotesRepo notesRepo;
    @Autowired
    private DailyNotesRepi dailyNotesRepi;
    @Autowired
    private Notes notes;
    @Autowired
    private DailyNotes dailyNotes;
    @Autowired
    private TeacherRepo teacherRepo;
    Random r=new Random();
    public boolean AddNN(List<MultipartFile> videos, List<MultipartFile> files,int id,HttpServletRequest request) {
        Optional<Notes> dailyNotes = notesRepo.findById(id);
        if (dailyNotes.isPresent()) {
            ArrayList<String> videoList = new ArrayList<>();
            ArrayList<String> fileList = new ArrayList<>();
            try {
                String vidoesPath = request.getRealPath("/") + "notesVideos";
                String filesPath = request.getRealPath("/") + "notesFiles";
                for (MultipartFile video : videos) {
                    if (video.isEmpty()) {
                        System.out.println("zero");
                    } else {
                        String originalVideoName = video.getOriginalFilename();
                        String ext = PasswordGenerator.generate(3);
                        String finalName = ext + originalVideoName;
                        Path videoNamePath = Paths.get(vidoesPath, finalName);
                        videoList.add(finalName);
                        try {
                            Files.write(videoNamePath, video.getBytes());
                        } catch (IOException e) {
                            e.printStackTrace();
                            return false;
                        }
                    }
                }
                for (MultipartFile file : files) {
                    if (file.isEmpty()) {
                        System.out.println("zero");
                    } else {
                        String originalFileName = file.getOriginalFilename();
                        String ext = PasswordGenerator.generate(3);
                        String finalName = ext + originalFileName;
                        Path fileNamePath = Paths.get(filesPath, finalName);
                        fileList.add(finalName);
                        try {
                            Files.write(fileNamePath, file.getBytes());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
            Notes notes=dailyNotes.get();
            ArrayList<String> file=notes.getFiles();
            ArrayList<String> video=notes.getVideos();
            file.addAll(fileList);
            video.addAll(videoList);
            notesRepo.save(notes);
            return true;
        }
        else{
            return  false;
        }
    }
    public boolean AddN(List<MultipartFile> videos, List<MultipartFile> files,int id,HttpServletRequest request) {
        Optional<DailyNotes> dailyNotes = dailyNotesRepi.findById(id);
        if (dailyNotes.isPresent()) {
            ArrayList<String> videoList = new ArrayList<>();
            ArrayList<String> fileList = new ArrayList<>();
            try {
                String vidoesPath = request.getRealPath("/") + "notesVideos";
                String filesPath = request.getRealPath("/") + "notesFiles";
                for (MultipartFile video : videos) {
                    if (video.isEmpty()) {
                        System.out.println("zero");
                    } else {
                        String originalVideoName = video.getOriginalFilename();
                        String ext = PasswordGenerator.generate(3);
                        String finalName = ext + originalVideoName;
                        Path videoNamePath = Paths.get(vidoesPath, finalName);
                        videoList.add(finalName);
                        try {
                            Files.write(videoNamePath, video.getBytes());
                        } catch (IOException e) {
                            e.printStackTrace();
                            return false;
                        }
                    }
                }
                for (MultipartFile file : files) {
                    if (file.isEmpty()) {
                        System.out.println("zero");
                    } else {
                        String originalFileName = file.getOriginalFilename();
                        String ext = PasswordGenerator.generate(3);
                        String finalName = ext + originalFileName;
                        Path fileNamePath = Paths.get(filesPath, finalName);
                        fileList.add(finalName);
                        try {
                            Files.write(fileNamePath, file.getBytes());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
            DailyNotes notes=dailyNotes.get();
            ArrayList<String> file=notes.getFiles();
            ArrayList<String> video=notes.getVideos();
            file.addAll(fileList);
            video.addAll(videoList);
            dailyNotesRepi.save(notes);
            return true;
        }
        else{
            return  false;
        }
    }
    public boolean AddN(List<MultipartFile> videos, List<MultipartFile> files,
                        String course, String semester, String subject,String date,String topic,
                        String unit, String check, HttpServletRequest request,String email) {
        try {
            ArrayList<String> videoList = new ArrayList<>();
            ArrayList<String> fileList = new ArrayList<>();
            String vidoesPath = request.getRealPath("/") + "notesVideos/";
            String filesPath = request.getRealPath("/") + "notesFiles/";
            for (MultipartFile filess : videos) {
                if (filess.isEmpty()) {
                    System.out.println("zero");
                } else {
                    byte [] bufferedbytes= new byte[30004490];
                    String originalFileName = filess.getOriginalFilename();
                    String ext = PasswordGenerator.generate(3);
                    String finalName = ext + originalFileName;
                    File file= new File(vidoesPath+finalName);
                    FileOutputStream outStream = null;
                    int count=0;
                    try {
                        BufferedInputStream fileInputStream = new BufferedInputStream(filess.getInputStream());
                        outStream = new FileOutputStream(file);
                        while ((count = fileInputStream.read(bufferedbytes)) != -1) {
                            outStream.write(bufferedbytes, 0, count);

                        }
                        outStream.close();
                        videoList.add(finalName);
                    }
                    catch(Exception e){
                        System.out.println(e);
                    }
//                    String originalFileName = file.getOriginalFilename();
//                    String ext = PasswordGenerator.generate(3);
//                    String finalName = ext + originalFileName;
//                    Path fileNamePath = Paths.get(filesPath, finalName);
//                    fileList.add(finalName);
//                    try {
//                        Files.write(fileNamePath, file.getBytes());
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
                }
            }
            for (MultipartFile filess : files) {
                if (filess.isEmpty()) {
                    System.out.println("zero");
                } else {
                    byte [] bufferedbytes= new byte[1024];
                    String originalFileName = filess.getOriginalFilename();
                    String ext = PasswordGenerator.generate(3);
                    String finalName = ext + originalFileName;
                    File file= new File(filesPath+finalName);
                    FileOutputStream outStream = null;
                    int count=0;
                    try {
                        BufferedInputStream fileInputStream = new BufferedInputStream(filess.getInputStream());
                        outStream = new FileOutputStream(file);
                        while ((count = fileInputStream.read(bufferedbytes)) != -1) {
                            outStream.write(bufferedbytes, 0, count);

                        }
                        outStream.close();
                        fileList.add(finalName);
                    }
                        catch(Exception e){
                            System.out.println(e);
                        }
//                    String originalFileName = file.getOriginalFilename();
//                    String ext = PasswordGenerator.generate(3);
//                    String finalName = ext + originalFileName;
//                    Path fileNamePath = Paths.get(filesPath, finalName);
//                    fileList.add(finalName);
//                    try {
//                        Files.write(fileNamePath, file.getBytes());
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
                }
            }
            String topic1 = topic.toUpperCase();
            if (check.equals("true")) {
                Notes notes=new Notes();
                notes.setCourse(course);
                notes.setDate(date);
                notes.setFiles(fileList);
                notes.setTopic(topic);
                notes.setVideos(videoList);
                notes.setId(r.nextInt());
                notes.setSemester(semester);
                notes.setSubject(subject);
                Optional<Teacher> t=teacherRepo.findById(email);
                Teacher t1=t.get();
                notes.setTeacher(t1);
                notes.setUnit(unit);

                BeanUtils.copyProperties(notes, dailyNotes);
                notesRepo.save(notes);
                dailyNotesRepi.save(dailyNotes);
            } else {
                Notes notes=new Notes();
                notes.setCourse(course);
                notes.setDate(date);
                notes.setFiles(fileList);
                notes.setVideos(videoList);
                notes.setTopic(topic);
                notes.setSemester(semester);
                notes.setSubject(subject);
                notes.setId(r.nextInt());
                Optional<Teacher> t=teacherRepo.findById(email);
                Teacher t1=t.get();
                notes.setTeacher(t1);
                notes.setUnit(unit);
                notesRepo.save(notes);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public ArrayList<DailyNotes> getDailyNotesByDate(String date,String course,String teacher_email){
        ArrayList<DailyNotes> dailyNotes=dailyNotesRepi.getDailyNotes(date,course,teacher_email);
        System.out.println(dailyNotes.size());
        ArrayList<DailyNotes> dailyNotes1=new ArrayList<DailyNotes>();
        ArrayList<TeacherNotesResponseDto> teacherNotesResponseDtos=new ArrayList<TeacherNotesResponseDto>();
        for(DailyNotes notes:dailyNotes){
            dailyNotes1.add(notes);
        }
        return dailyNotes1;
    }
    public boolean deleteOneNoteFromSemesterNotes(NotesDeleteRequestDtoForOneNote notesDeleteRequestDtoForOneNote,HttpServletRequest request){
        Optional<Notes> notes=notesRepo.findById(notesDeleteRequestDtoForOneNote.getId());
        DeleteFileAndImages deleteFileAndImages=new DeleteFileAndImages();
        if(notes.isPresent()){
            Notes note=notes.get();
            ArrayList<String> arrayList=new ArrayList<String>();
            if(notesDeleteRequestDtoForOneNote.isCheck()==true){
                arrayList=note.getFiles();
                String filePath=request.getRealPath("/")+"notesFiles"+File.separator+arrayList.get(notesDeleteRequestDtoForOneNote.getIndex());
                arrayList.remove(notesDeleteRequestDtoForOneNote.getIndex());
                note.setFiles(arrayList);
                notesRepo.save(note);
                return true;
            }
            else{
                arrayList=note.getVideos();
                String filePath=request.getRealPath("/")+"notesVideos"+File.separator+arrayList.get(notesDeleteRequestDtoForOneNote.getIndex());
                arrayList.remove(notesDeleteRequestDtoForOneNote.getIndex());
                note.setVideos(arrayList);
                notesRepo.save(note);
                return true;
            }
        }
        else{
            return false;
        }
    }
    public boolean deleteOneNoteFromDailyNotes(NotesDeleteRequestDtoForOneNote notesDeleteRequestDtoForOneNote){
        Optional<DailyNotes> dailyNotes=dailyNotesRepi.findById(notesDeleteRequestDtoForOneNote.getId());
        if(dailyNotes.isPresent()){
            DailyNotes dailyNotes1=dailyNotes.get();
            ArrayList<String> arrayList=new ArrayList<String>();
            if(notesDeleteRequestDtoForOneNote.isCheck()==true){
                arrayList=dailyNotes1.getFiles();
                arrayList.remove(notesDeleteRequestDtoForOneNote.getIndex());
                dailyNotes1.setFiles(arrayList);
                dailyNotesRepi.save(dailyNotes1);
                return true;
            }
            else{
                arrayList=dailyNotes1.getVideos();
                arrayList.remove(notesDeleteRequestDtoForOneNote.getIndex());
                dailyNotes1.setVideos(arrayList);
                dailyNotesRepi.save(dailyNotes1);
                return true;
            }
        }
        else{
            return false;
        }
    }
    public boolean deleteOneNoteCompletelyFromSemesterNotes(DeleteH deleteH,HttpServletRequest request){
        Optional<Notes> notes=notesRepo.findById(deleteH.getId());
        ArrayList<String> files=new ArrayList<>();
        ArrayList<String> videos=new ArrayList<>();
        DeleteFileAndImages deleteFileAndImages=new DeleteFileAndImages();
        if(notes.isPresent()){
            Notes note=notes.get();
            files=note.getFiles();
            videos=note.getVideos();
            for(String name:files){
                String filePath=request.getRealPath("/")+"notesVideos"+File.separator+name;
                deleteFileAndImages.delete(filePath);
            }
            for(String name:videos){
                String videoPath=request.getRealPath("/")+"notesFiles"+File.separator+name;
                deleteFileAndImages.delete(videoPath);
            }
            notesRepo.deleteById(deleteH.getId());
            return true;
        }
        else{
            return false;
        }

    }
    public boolean deleteOneNoteCompletelyFromDailyNotes(DeleteH deleteH){
        Optional<DailyNotes> dailyNotes=dailyNotesRepi.findById(deleteH.getId());
        if(dailyNotes.isPresent()){
            dailyNotesRepi.deleteById(deleteH.getId());
            return true;
        }
        else{
            return false;
        }
    }
    public ArrayList<DailyNotes> getAllNotes(SearchNotesDto searchNotesDto){
        String teacher_email=searchNotesDto.getEmail();
        String course=searchNotesDto.getCourse();
        System.out.println(teacher_email);
        Optional<Teacher> teacher=teacherRepo.findById(teacher_email);
        if(teacher.isPresent()){
            ArrayList<DailyNotes> dailyNotes=dailyNotesRepi.getAllDailyNotes(teacher_email,course);
            return dailyNotes;
        }
        else{
            return null;
        }
    }
    public ArrayList<Notes> getAllSemesterNotes(String course,String teacher_email){
        ArrayList<Notes> notes=notesRepo.getAllSemesterNotes(course,teacher_email);
        return notes;
    }
}
