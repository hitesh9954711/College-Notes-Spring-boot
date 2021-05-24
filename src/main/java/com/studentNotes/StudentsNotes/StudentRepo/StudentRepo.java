package com.studentNotes.StudentsNotes.StudentRepo;

import com.studentNotes.StudentsNotes.StudentEntities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepo extends JpaRepository<Student,String> {
}
