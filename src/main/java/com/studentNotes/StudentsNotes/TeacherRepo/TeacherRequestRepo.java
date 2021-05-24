package com.studentNotes.StudentsNotes.TeacherRepo;

import com.studentNotes.StudentsNotes.TeacherEntities.TeacherRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRequestRepo extends JpaRepository<TeacherRequest,String> {
}
