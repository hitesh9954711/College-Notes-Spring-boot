package com.studentNotes.StudentsNotes.AdminService;


import com.studentNotes.StudentsNotes.AdminEntities.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class AdminUserDetail implements UserDetails {

        @Autowired
        private Admin admin;
        private String name;
        private String role;
        public  AdminUserDetail(Admin admin,String name,String role){
            this.admin=admin;this.name=name;
            this.role=role;
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            SimpleGrantedAuthority simpleGrantedAuthority=new SimpleGrantedAuthority(admin.getAdminRole());
            return List.of(simpleGrantedAuthority);
        }

        @Override
        public String getPassword() {
            return admin.getAdminPassword();
        }

        @Override
        public String getUsername() {
            return admin.getAdminEmail();
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }
    }

