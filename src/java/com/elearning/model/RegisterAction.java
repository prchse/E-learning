
package com.elearning.model;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

public class RegisterAction implements SessionAware {
  SessionMap<String, Object> sessionmap;
HttpServletRequest request;
    protected String studentId, fname, lname, emailId, password;
    protected long contactno;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public long getContactno() {
        return contactno;
    }

    /**
     *
     * @param contactno
     */
    public void setContactno(long contactno) {
        this.contactno = contactno;
    }

    public String execute() {
        System.out.println(password);
        int i = RegisterDao.register(this);
        if (i > 0 && i!=3) {
             sessionmap.put("user_registered","yes");
            return "success";
           
               
        } else if(i==3){
             System.out.println("print error in if in register action");
            sessionmap.put("user_exist","yes");
            return "idexist";
        }
            {
            return "error";
        }
    }
    

    private void addFieldError(String name, String the_name_is_required) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
      public void setSession(Map map) {
        sessionmap = (SessionMap) map;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
