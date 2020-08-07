/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elearning.model;

/**
 *
 * @author PRAGYA
 */
public class StudentCourse {
    private String course_name,registration_time,course_completion_time,deadline,duration,time_remaining;
    int courseid;

    public int getCourseid() {
        return courseid;
    }

    public void setCourseid(int courseid) {
        this.courseid = courseid;
    }

    public String getDeadline() {
        return deadline;
    }

    public String getTime_remaining() {
        return time_remaining;
    }

    public void setTime_remaining(String time_remaining) {
        this.time_remaining = time_remaining;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getRegistration_time() {
        return registration_time;
    }

    public void setRegistration_time(String registration_time) {
        this.registration_time = registration_time;
    }

    public String getCourse_completion_time() {
        return course_completion_time;
    }

    public void setCourse_completion_time(String course_completion_time) {
        this.course_completion_time = course_completion_time;
    }
    
    
}
