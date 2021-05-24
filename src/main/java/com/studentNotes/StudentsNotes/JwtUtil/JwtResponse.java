package com.studentNotes.StudentsNotes.JwtUtil;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;


@Setter
@Getter
@ToString
public class JwtResponse {
    private String token;
    private String name;
    private String email;
    private String role;
    public JwtResponse(String token,String name,String email,String role){
        this.token=token;
        this.name=name;
        this.email=email;
        this.role=role;
    }
}
