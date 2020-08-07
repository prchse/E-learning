/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elearning.model;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


import com.opensymphony.xwork2.ActionSupport;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;

public class EmailerNext extends ActionSupport {

    private String to;

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
 
    static Properties properties = new Properties();

    public static Properties getProperties() {
        return properties;
    }

    public static void setProperties(Properties properties) {
        EmailerNext.properties = properties;
    }

    static {
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", "465");
    }

    /**
     *
     * @param messagebody
     * @param subjectString
     * @return
     */
    //  @Override
    public String sendMail(String messagebody, String subjectString,String emailaddress) {
        String ret = null;
        try {
            
            
            Session session = Session.getDefaultInstance(properties,
                    new javax.mail.Authenticator() {
                        @Override
                        protected PasswordAuthentication
                            getPasswordAuthentication() {
                                return new PasswordAuthentication("elearningatuoa@gmail.com", "elearning123");
                            }
                    });
            
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("elearningatuoa@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(emailaddress));
            message.setSubject(subjectString);
            message.setText(messagebody);
            Transport.send(message);
            return "success";
           
        } catch (MessagingException ex) {
            Logger.getLogger(EmailerNext.class.getName()).log(Level.SEVERE, null, ex);
        }
   
   
        return ret;
}
}
