/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elearning.model;

import org.apache.struts2.dispatcher.SessionMap;

public class Course {
   private int courseid;
   private String coursename,Creationdate,description,duration,uploaded,enrollcheck;

    public int getCourseid() {
        return courseid;
    }

    public String getEnrollcheck() {
        return enrollcheck;
    }

    public void setEnrollcheck(String enrollcheck) {
        this.enrollcheck = enrollcheck;
    }

    public String getUploaded() {
        return uploaded;
    }

    public void setUploaded(String uploaded) {
        this.uploaded = uploaded;
    }

    public void setCourseid(int courseid) {
        this.courseid = courseid;
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    public String getCreationdate() {
        return Creationdate;
    }

    public void setCreationdate(String Creationdate) {
        this.Creationdate = Creationdate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
    //AddCourse Class has been added////
     private String  coursedescription;
     SessionMap<String, Object> sessionmap;
   

    public String getCoursedescription() {
        return coursedescription;
    }

    public void setCoursedescription(String coursedescription) {
        this.coursedescription = coursedescription;
    }

   

    
}
