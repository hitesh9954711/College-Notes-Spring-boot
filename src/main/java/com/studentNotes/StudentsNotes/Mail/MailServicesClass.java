package com.studentNotes.StudentsNotes.Mail;

import javax.mail.MessagingException;

public interface MailServicesClass {
    public void send(String toAddress,String fromAddress,String subject,String content) throws MessagingException;
}
