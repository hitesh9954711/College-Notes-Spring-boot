package com.studentNotes.StudentsNotes.TeacherEntities;

import java.io.File;

public class DeleteFileAndImages {
    public Boolean delete(String path) {
        Boolean f = false;
        try {
            File file = new File(path);
            f = file.delete();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }
}
