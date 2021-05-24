package com.studentNotes.StudentsNotes.JwtUtil;

import com.studentNotes.StudentsNotes.AdminService.AdminUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    AdminUserDetailService customUserDetailService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {


        String reqeustTokenHeader=httpServletRequest.getHeader("Authorization");

        String username=null;
        String jwtToken=null;
        if(reqeustTokenHeader!=null && reqeustTokenHeader.startsWith("Bearer ")){
            jwtToken=reqeustTokenHeader.substring(7);
            System.out.println(jwtToken);

            try{
                username=jwtUtil.getUsernameFromToken(jwtToken);
                if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null){
                    UserDetails userDetails =customUserDetailService.loadUserByUsername(username);
                    System.out.println(userDetails.getUsername()+"--------------"+userDetails.getPassword()+"------------"+userDetails.getAuthorities());
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                    System.out.println(usernamePasswordAuthenticationToken.getPrincipal()+"------"+usernamePasswordAuthenticationToken.getAuthorities());
                    System.out.println("done");
                }
                else{
                    System.out.println("Token is not valid");
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }

        }
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}
