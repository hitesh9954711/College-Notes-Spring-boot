package com.studentNotes.StudentsNotes.AdminRepo;

import com.studentNotes.StudentsNotes.AdminEntities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepo extends JpaRepository<Admin,String> {
}
