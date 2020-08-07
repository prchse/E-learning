package com.elearning.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author SIDDHARTH
 */
public class EnrollCourse implements ServletRequestAware,SessionAware{

    SessionMap<String, Object> sessionmap;
    // SessionMap<String,List> questionsession;
    HttpServletRequest request;
    int courseidsession;

    public HttpServletRequest getServletRequest() {
        return request;
    }

    /**
     *
     * @param request
     */
    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }
    /*private int studentCourseId, studentId, courseId;

    public int getStudentCourseId() {
        return studentCourseId;
    }

    public void setStudentCourseId(int studentCourseId) {
        this.studentCourseId = studentCourseId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
*/
    public String enrollToCourse() {

        String status = null;
        String co = "insert into student_course values('',?,?,CURRENT_TIMESTAMP,'')";
        String st = "select * from student_course where course_id=? and student_id=?";
        //String st2="select * from student_course wh";
        HttpSession session = ServletActionContext.getRequest().getSession(false);
        if (session == null || session.getAttribute("login") == null) {
            return "login";
        }
        String sess = String.valueOf(session.getAttribute("login"));

        DBConnect db = new DBConnect();
        db.connect();
        System.out.println(sess);
        try {
            if (getServletRequest().getParameter("courseid") != null) {
                courseidsession = Integer.parseInt(getServletRequest().getParameter("courseid"));

                sessionmap.put("course_id", courseidsession);
            }
            //courseidsession = sessionmap.get("course_id");
            System.out.println("check");
            System.out.println(request.getParameter("courseid"));
            System.out.println(sess);

            PreparedStatement ps1 = db.con.prepareStatement(st);
            ps1.setInt(1, courseidsession);
            ps1.setString(2, sess);
            ResultSet rs = ps1.executeQuery();

            PreparedStatement ps = db.con.prepareStatement(co);

            ps.setString(1, sess);
            ps.setInt(2, courseidsession);
            if (rs.next() == true) {

                // rs.getString("student_course_id");
               
                // rs.getString("student_course_id");
                sessionmap.put("already","yes");
                return "alreadyenrolled";
                

            } else {
                ps.executeUpdate();
            }
             sessionmap.put("enrolled","yes");
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;

    }

    public String disenrollToCourse() {

        String status = null;
        String co = "delete from student_course where student_id=? and course_id=?";
        String st = "select * from student_course where course_id=? and student_id=?";

        HttpSession session = ServletActionContext.getRequest().getSession(false);
        if (session == null || session.getAttribute("login") == null) {
            return "login";
        }
        String sess = String.valueOf(session.getAttribute("login"));

        DBConnect db = new DBConnect();
        db.connect();
        // System.out.println(sess);
        try {
            if (getServletRequest().getParameter("courseid") != null) {
                courseidsession = Integer.parseInt(getServletRequest().getParameter("courseid"));

                sessionmap.put("course_id", courseidsession);
            }
            //courseidsession = sessionmap.get("course_id");
            //   System.out.println("check");
            //  System.out.println(request.getParameter("courseid"));
            //   System.out.println(sess);

            PreparedStatement ps = db.con.prepareStatement(co);
            PreparedStatement ps1 = db.con.prepareStatement(st);

            ps1.setInt(1, courseidsession);
            ps1.setString(2, sess);
            ResultSet rs = ps1.executeQuery();
            ps.setString(1, sess);
            ps.setInt(2, courseidsession);
            if (rs.next() == true) {

                ps.executeUpdate();
            } else {
                return "notenrolledalready";
            }

            return "success";
        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;

    }
    

    public String getEnrolledCourses() {

        String status = null;
        String st = "select * from student_course where student_id=?";

        try {
            DBConnect db = new DBConnect();
            db.connect();
            PreparedStatement ps = db.con.prepareStatement(st);
            HttpSession session = ServletActionContext.getRequest().getSession(false);
            if (session == null || session.getAttribute("login") == null) {
                return "login";
            }
            String sess = String.valueOf(session.getAttribute("login"));
            ps.setString(1, sess);
            ResultSet rs= ps.executeQuery();
            while(rs.next()){
               // Course c= new Course();
                
            }

           
        } catch (SQLException ex) {
            Logger.getLogger(EnrollCourse.class.getName()).log(Level.SEVERE, null, ex);
        }
       return status;

    }
    @Override
    public void setSession(Map map) {
        sessionmap = (SessionMap) map;

    }

}
