package com.studentNotes.StudentsNotes.JwtUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;


@Getter
@Setter
@ToString
public class JwtRequest {
    private  String token;
    public JwtRequest(String token){
        this.token=token;
    }

}