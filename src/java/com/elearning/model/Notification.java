/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elearning.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

public class Notification implements SessionAware {
    int notificationId,assignId;
     String content,studentId,sendingTime;
      SessionMap<String, Object> sessionmap;

    public int getAssignId() {
        return assignId;
    }

    public void setAssignId(int assignId) {
        this.assignId = assignId;
    }

     private ArrayList<Notification> NotificationList = new ArrayList<Notification>();

     DBConnect db=new DBConnect();

    public ArrayList<Notification> getNotificationList() {
        return NotificationList;
    }

    public void setNotificationList(ArrayList<Notification> NotificationList) {
        this.NotificationList = NotificationList;
    }

    public int getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getSendingTime() {
        return sendingTime;
    }

    public void setSendingTime(String sendingTime) {
        this.sendingTime = sendingTime;
    }
   
    public String notifyAdmin()
    {
      db.connect();
      String result=null;
      String query="select * from notification where send_to='admin'";
      try{
      PreparedStatement pstatement = db.con.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
      ResultSet rs=pstatement.executeQuery();
      if(rs.next())
      {
          
      rs.previous();
      while(rs.next())
      {
          Notification notify= new Notification();
          notify.setNotificationId(rs.getInt("notification_id"));
          notify.setContent(rs.getString("content"));
          notify.setSendingTime(String.valueOf(rs.getTimestamp("sending_time")));
          notify.setStudentId(rs.getString("student_id"));
          notify.setAssignId(rs.getInt("assignment_id"));
          NotificationList.add(notify);
         
          result="success";
      }
      }
      else
      {
          sessionmap.put("notification","no");
          result="nonotify";
          
      }
     
      
              
      }
      catch (SQLException  e) {
            e.printStackTrace();
            System.err.println(e);
        }
      return result;
    }
     public void setSession(Map map) {
        sessionmap = (SessionMap) map;
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     public String StudentNotify(){
         db.connect();
      String result=null;
      String query="select * from notification where send_to='student'";
      try{
      PreparedStatement pstatement = db.con.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
      ResultSet rs=pstatement.executeQuery();
      if(rs.next())
      {
          
      rs.previous();
      while(rs.next())
      {
          Notification notify= new Notification();
          notify.setNotificationId(rs.getInt("notification_id"));
          notify.setContent(rs.getString("content"));
          notify.setSendingTime(String.valueOf(rs.getTimestamp("sending_time")));
          notify.setStudentId(rs.getString("student_id"));
          notify.setAssignId(rs.getInt("assignment_id"));
          NotificationList.add(notify); 
          result="success";
      }
      }
      else
      {
          sessionmap.put("notification","no");
          result="nonotify";
          
      }
     
      
              
      }
      catch (SQLException  e) {
            e.printStackTrace();
            System.err.println(e);
        }
      return result;
    
}
}
