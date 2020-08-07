
package com.elearning.model;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.opensymphony.xwork2.ActionSupport;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Emailer extends ActionSupport {

   // private String from="elearningatuoa@gmail.com";
    //  private String password="elearning123";
    private String to;
    // private String subject="Your password at Elearning";
    private String retrievePass;

     String ifactivesubject="your password at elearning"; 
     String ifnotactivesubject="You are not active user right now";
     String subject=null;
     String yourlog="Your login id : ";
     String yourpass="\n Password :";
    static Properties properties = new Properties();

    static {
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", "465");
    }

    /**
     *
     * @return
     */
    @Override
    public String execute() {
        String ret = SUCCESS;
        try {
            DBConnect db = new DBConnect();
            db.connect();
            String qu = "select * from login where login_id=?";
            PreparedStatement ps = db.con.prepareStatement(qu);
            ps.setString(1, to);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if ((rs.getString("status")).equals("activated")) {
                    subject=ifactivesubject;
                    retrievePass = yourlog+to+yourpass+(String) (rs.getString("password"));
                } else {
                    subject =ifnotactivesubject;
                    retrievePass="Your account is deactivated";
                }
            }
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
                    InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(retrievePass);
            Transport.send(message);
        } catch (Exception e) {
            ret = ERROR;
            e.printStackTrace();
        }
        return ret;
    }
    /*
     public String getFrom() {
     return from;
     }

     public void setFrom(String from) {
     this.from = from;
     }

     public String getPassword() {
     return password;
     }

     public void setPassword(String password) {
     this.password = password;
     }
     */

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
    /*
     public String getSubject() {
     return subject;
     }

     public void setSubject(String subject) {
     this.subject = subject;
     }

     public String getBody() {
     return body;
     }

     public void setBody(String body) {
     this.body = body;
     }*/

    public static Properties getProperties() {
        return properties;
    }

    public static void setProperties(Properties properties) {
        Emailer.properties = properties;
    }
}
