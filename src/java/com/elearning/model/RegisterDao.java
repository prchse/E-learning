package com.elearning.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.websocket.Session;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

public class RegisterDao {
SessionMap<String, Object> sessionmap;
HttpServletRequest request;
    public static int register(RegisterAction ra) {//(String studentId,String fname,String lname,String emailId,String contactno ) throws SQLException{

        int status = 0;
        int status1 = 0;
        int finalstatus = 0;
        String st = "insert into student values(?,?,?,?,?,CURRENT_TIMESTAMP)";
        String st2 = "insert into login values(?,?,?,?)";
        String select="select * from login where login_id='"+ra.getStudentId()+"'";
        String notify="insert into notification values('',?,'this user is newly registered',CURRENT_TIMESTAMP,'admin',"+null+")";
       
        try {
            //System.out.println("hello2");
            DBConnect db = new DBConnect();
            db.connect();
            PreparedStatement ps=db.con.prepareStatement(select);
            ResultSet rs=ps.executeQuery();
            if(rs.next())
            {
                 System.out.println("print error in if");
                return 3;
               
            }
            else{
            PreparedStatement pstatement = db.con.prepareStatement(st);
            PreparedStatement pstatement2 = db.con.prepareStatement(st2);
             PreparedStatement ps_n = db.con.prepareStatement(notify);
            //for student table
           // pstatement.setString(1, ra.getEmailId());
             ps_n.setString(1,ra.getStudentId());
            pstatement.setString(1, ra.getStudentId());
            pstatement.setString(2, ra.getFname());
            pstatement.setString(3, ra.getLname());
            pstatement.setString(4, ra.getEmailId());
            pstatement.setLong(5, ra.getContactno());
            //for login table
          
            pstatement2.setString(1, ra.getEmailId());
            pstatement2.setString(2, ra.getPassword());
            pstatement2.setString(3, "student");
            pstatement2.setString(4, "deactivated");

            status = pstatement.executeUpdate();
            System.out.println(status);
            status1 = pstatement2.executeUpdate();
            int no=ps_n.executeUpdate();
                System.out.println("printing for notification"+no);
            if (status > 0 && status1 > 0) {
                return 1;
                
                
            } else {
                return 0;
            }
            }

        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
            System.err.println(e);
        }
        return finalstatus;

    }
    public void setSession(Map map) {
        sessionmap = (SessionMap) map;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
