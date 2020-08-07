/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elearning.model;

import static com.opensymphony.xwork2.Action.SUCCESS;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.*;
import java.util.Map;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import org.apache.catalina.Session;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author PRAGYA
 */
public class ManageStudent implements SessionAware, ServletRequestAware {

    DBConnect db = new DBConnect();
    HttpServletRequest request;
    private ArrayList<StudentCourse> course_list = new ArrayList<StudentCourse>();
    private ArrayList<ManageTopic> topiclist = new ArrayList<ManageTopic>();

    public ArrayList<ManageTopic> getTopiclist() {
        return topiclist;
    }

    public void setTopiclist(ArrayList<ManageTopic> topiclist) {
        this.topiclist = topiclist;
    }

    public ArrayList<StudentCourse> getCourse_list() {
        return course_list;
    }

    public void setCourse_list(ArrayList<StudentCourse> course_list) {
        this.course_list = course_list;
    }

    public ArrayList<Student> getList() {
        return list;
    }

    public void setList(ArrayList<Student> list) {
        this.list = list;
    }
    private ArrayList<Student> list = new ArrayList<Student>();

    public HttpServletRequest getServletRequest() {
        return request;
    }

    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }
String statusCheck=null;
    SessionMap<String, Object> sessionmap;
    public Map<String, Object> session;

    public String status() {
        String status = "error", query = null;
        try {
            db.connect();
            String param;
            if (getServletRequest().getParameter("id") != null) {
                param = getServletRequest().getParameter("id");
                sessionmap.put("student_id", param);
                
            }
            param = String.valueOf(sessionmap.get("student_id"));
            // id=sessionmap.get("student_id");

            String check_status = "select status from login where login_id='" + param + "'";
            PreparedStatement ps_check = db.con.prepareStatement(check_status);
            ResultSet rs = ps_check.executeQuery();
            while (rs.next()) {
                if (rs.getString("status").equals("activated")) {
                    query = "update login set status='deactivated' where login_id='" + param + "'";
                    statusCheck="deactivated";
                    
                    System.out.println(query);
                } else if (rs.getString("status").equals("deactivated")) {
                    query = "update login set status='activated' where login_id='" + param + "'";
                    System.out.println(query);
                    statusCheck="activated";
                }
            }

            PreparedStatement ps = db.con.prepareStatement(query);
            int i = ps.executeUpdate();
            if (i > 0) {
                sessionmap.put("checkstaus", "checked");
                String check = studentList();
                if (check.equals("success")) {
                    status = "success";
                    //sessionmap.put("status_session",rs.getString("status"));
                    //enetr mail coding// to send email for the activation and detactivation
                    String messageToSend="Your account has been"+statusCheck;
                    System.out.println(messageToSend);
                    EmailerNext e= new EmailerNext();
                    e.sendMail(messageToSend, "Your Account Status is",param);
                    
                }

            } else {
                status = "error";
            }
        } catch (SQLException e) {
            System.out.println(e);
            e.printStackTrace();
        }

        return status;
    }
    // private  Event<Tick>

    public String studentList() {
        try {

            db.connect();
            String query;

            PreparedStatement ps1 = db.con.prepareStatement("select * from student inner join login on student.student_id=login.login_id");
            int i = 0;
            ResultSet rs = ps1.executeQuery();
            while (rs.next()) {
                Student stud = new Student();
                stud.setStudentid(rs.getString(1));

                stud.setFname(rs.getString(2));
                stud.setLname(rs.getString(3));
                stud.setEmailid(rs.getString(4));
                stud.setContactno(rs.getLong("contact_no"));
                stud.setStatus(rs.getString("status"));

                list.add(stud);

                //sessionmap.put("check_course",null);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }

        return "success";

    }

    public String StudentLog() {
        db.connect();
        String param, query, result = "error";

        if (getServletRequest().getParameter("id") != null) {
            param = getServletRequest().getParameter("id");
            sessionmap.put("student_id", param);
        }
        param = String.valueOf(sessionmap.get("student_id"));

        try {
            query = "select * from student_logtime where student_id='" + param + "'";
            PreparedStatement ps_check = db.con.prepareStatement(query);
            ResultSet rs = ps_check.executeQuery();
            while (rs.next()) {
                Student stud = new Student();
                stud.setLogin(String.valueOf(rs.getTimestamp("login_time")));
                if (rs.getTimestamp("logout_time") != null) {
                    stud.setLogout(String.valueOf(rs.getTimestamp("logout_time")));

                } else {
                    stud.setLogout("Not Available");
                }
                stud.setSeesionid(rs.getString("session_id"));

                list.add(stud);
            }

            result = "success";
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return result;

    }

    public String course() throws ParseException {

        db.connect();
        System.out.println("Session value");
        System.out.println(sessionmap.get("check_course"));
        String param, query, result = "error";
        if (getServletRequest().getParameter("id") != null) {
            param = getServletRequest().getParameter("id");
            sessionmap.put("student_id", param);
        }
        param = String.valueOf(sessionmap.get("student_id"));
        query = "select course.course_name,course.duration,student_course.registration_time,student_course.course_completion_time,course.course_id from course inner join student_course on course.course_id=student_course.course_id inner join student on student_course.student_id=student.student_id where student.student_id='" + param + "'";
        try {
            PreparedStatement ps_course = db.con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            ResultSet rs_course = ps_course.executeQuery();
            if (rs_course.next()) {
                rs_course.previous();
                while (rs_course.next()) {
                    StudentCourse sc = new StudentCourse();
                    sc.setCourse_name(rs_course.getString(1));
                    sc.setRegistration_time(String.valueOf(rs_course.getDate(3)));
                    sc.setDuration(rs_course.getString(2));
                    sc.setCourseid(rs_course.getInt(5));
                    Calendar cal = Calendar.getInstance();

                    cal.setTime((Date) rs_course.getDate(3));
                    int week = Integer.parseInt(rs_course.getString(2));
                    System.out.println("above week");
                    System.out.println(week);
                    System.out.println("below week");
                    int days = week * 7;
                    System.out.println(days);
                    cal.add(Calendar.DAY_OF_MONTH, days);

                    System.out.println(cal.getTime());
                    System.out.println("below time");
                    SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
                    String deadline = format1.format(cal.getTime());
                    sc.setDeadline(String.valueOf(deadline));

                    if (rs_course.getDate(4) != null) {
                        sc.setCourse_completion_time(String.valueOf(rs_course.getDate(4)));

                        sc.setTime_remaining("---");

                    } else {
                        sc.setCourse_completion_time("yet not completed");
                        Date date = new Date();
                        Date d1 = new SimpleDateFormat("yyyy-M-dd").parse((String) deadline);
                        Date d2 = new SimpleDateFormat("yyyy-M-dd").parse(format1.format(date));
                        if(d1.compareTo(d2)>0)
                        {
                        //  Date  d2 = new SimpleDateFormat("yyyy-M-dd").parse(rs_course.getDate(param));
                        long diff = Math.abs(d1.getTime() - d2.getTime());
                        System.out.print("differece od date"+diff);
                        long diffDays = diff / (24 * 60 * 60 * 1000);
                        System.out.println("Differece of the days");
                        System.out.println(date);
                        System.out.println(diffDays);
                        sc.setTime_remaining(String.valueOf(diffDays));
                        }
                        else{
                            sc.setTime_remaining("Deadline has been crossed");
                        }

                    }

                    course_list.add(sc);
                }
                result = "success";
                out.print("<script> alert ('added successfully!') </ script>");
         // sessionmap.put("check_course",null);

            } else {
                sessionmap.put("check_course", "nocourse");
                /*StudentCourse sc=new StudentCourse();
                 sc.setCourse_name("have not registered for any course");
                 sc.setRegistration_time("not registered");
                 sc.setCourse_completion_time("--");
                 course_list.add(sc);*/
                studentList();
                /*   System.out.println("value of session in else");
                 System.out.println(sessionmap.get("check_course"));
                 out.print("<script> alert ('added successfully!') </ script>"); 
                 try{
                 HttpServletResponse response = ServletActionContext.getResponse ();
                 response.setCharacterEncoding ("UTF-8");
                 response.setContentType ("text / html; charset = utf-8");
                 PrintWriter out = response.getWriter ();
                 out.print ("<script> alert ('123 ');</script> ");
                 }   catch (IOException ex) {
                 ex.printStackTrace();
                 }*/

                result = "successNoCourse";

            }

        } catch (SQLException e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return result;
    }

    public String topic() //To view List of topic
    {

        db.connect();
        String param, query, result = "error",studid;
        boolean assign_exist=false,assign_completed=false,quiz_exist=false,quiz_completed=false;
        int courseid = 0,topicidcomp=0;
        ResultSet rs_assign=null,rs_quiz=null;

        param = String.valueOf(sessionmap.get("student_id"));
         studid = String.valueOf(sessionmap.get("student_id"));
        if (getServletRequest().getParameter("cid") != null) {
            courseid = Integer.parseInt(getServletRequest().getParameter("cid"));
            //sessionmap.put("course_id", param);
        }
        query = "select * from topic inner join course_topic on topic.topic_id=course_topic.topic_id where course_topic.course_id=" + courseid + "";
        try {
            PreparedStatement ps_topic = db.con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            ResultSet rs_topic = ps_topic.executeQuery();

            while (rs_topic.next()) {
                ManageTopic ad = new ManageTopic();
                System.out.println("print id" + param);

              
                if (rs_topic.getInt("quiz_id") > 0) {
                    quiz_exist=true;
                   ad.setQuizId(rs_topic.getInt("quiz_id"));
                    String q_quiz = "select * from student_quiz where quiz_id=" + rs_topic.getInt("quiz_id") + " and student_id='" + param + "'";
                    PreparedStatement ps_quiz = db.con.prepareStatement(q_quiz);
                     rs_quiz = ps_quiz.executeQuery();
                   
                   
                    if (rs_quiz.next()) {
                        
                        if (rs_quiz.getDate("quiz_sub_date") != null) {
                            quiz_completed=true;
                            ad.setQuizsubdate(String.valueOf(rs_quiz.getDate("quiz_sub_date")));
                          
                        }

                    } else {
                        quiz_completed=false;
                      
                        
                    }
                } else {
                    ad.setQuizsubdate("quiz of this topic is N/A");
                    ad.setQuizmarks("N/A");
                    System.out.println("below quiz id in else");
                }
                if (rs_topic.getInt("assignment_id") > 0) {
                    assign_exist=true;
                     ad.setAssignId(rs_topic.getInt("assignment_id"));
                    String q_assign = "select * from student_assignment where assignment_id=" + rs_topic.getInt("assignment_id") + " and student_id='" + param + "'";
                     PreparedStatement ps_assign = db.con.prepareStatement(q_assign);
                  
                     rs_assign = ps_assign.executeQuery();
                    
                    
                    if (rs_assign.next()) {
                       
                        
                        if(rs_assign.getString("verified").equals("verified"))
                        {
                            assign_completed=true;
                            ad.setAssignsubdate(String.valueOf(rs_assign.getDate("assignment_sub_date")));
                                                       
                            
                        }
                       
                        
                                   }
                    
                    
                  else {
                        assign_completed=false;
                       
                    }
                    
                }
                
                else {
                   
                    ad.setAssignsubdate(" assignment of this topic is N/A");
                   

                }

                ad.setTopicname(rs_topic.getString("topic_name"));
                ad.setTopicdescription(rs_topic.getString("topic_description"));
                System.out.println("below are query");
                System.out.println(rs_topic.getInt("topic_id"));
                String topicc="select * from student_topic where student_id='"+param+"' and topic_id="+rs_topic.getInt("topic_id")+"";
                 PreparedStatement ps_topicc = db.con.prepareStatement(topicc);
                 ResultSet rs_topicc= ps_topicc.executeQuery();
                 if(rs_topicc.next())
                 {
                   ad.setTopiccompleted("yes");  
                   ad.setTopicCompleteDate(rs_topicc.getDate("topic_completion_time").toString());
                 }
                 else{
                     ad.setTopiccompleted("no");
                   ad.setTopicCompleteDate("N/A");
                 }
              /* if(quiz_exist==true && quiz_completed==true && assign_exist==true && assign_completed==true)
               {
                   ad.setTopiccompleted("yes");
                   if(rs_assign.getDate("assignment_sub_date").after(rs_quiz.getDate("quiz_sub_date")))
                   {
                       ad.setTopicCompleteDate(rs_assign.getDate("assignment_sub_date").toString());
                   }
                   else{
                        ad.setTopicCompleteDate(rs_quiz.getDate("quiz_sub_date").toString());
                   }
                   
               }
               else if(quiz_exist==true && quiz_completed==true && assign_exist==false)
               {
                    ad.setTopiccompleted("yes");
                     ad.setTopicCompleteDate(rs_quiz.getDate("quiz_sub_date").toString());
                   
               }
               else if(assign_exist==true && assign_completed==true && quiz_exist==false)
               {
                    ad.setTopiccompleted("yes");
                    ad.setTopicCompleteDate(rs_assign.getDate("assignment_sub_date").toString());
               }
               else{
                   ad.setTopiccompleted("no");
                   ad.setTopicCompleteDate("N/A");
               } */
               
                topiclist.add(ad);

            }

        } catch (SQLException e) {
            System.out.println(e);
            e.printStackTrace();
        }

        return "success";

    }
    ArrayList<Assignment> assbeanlist = new ArrayList<Assignment>();

    public ArrayList<Assignment> getAssbeanlist() {
        return assbeanlist;
    }

    public void setAssbeanlist(ArrayList<Assignment> assbeanlist) {
        this.assbeanlist = assbeanlist;
    }

    public String assignmentAttempt() {
        String status = null;
       boolean exist=false;
        int topicIdSessAssi;
        int assignmentIdTemp = 0;
        String strAssignAttempt = "select * from course_topic where topic_id=?";
        String strAssignmentAttempt = "select * from assignment where assignment_id=?";
        String checkAsssign="select * from student_assignment where assignment_id=? and student_id=? and verified='verified'";

        if (getServletRequest().getParameter("id") != null) {
            topicIdSessAssi = Integer.parseInt(getServletRequest().getParameter("id"));
            sessionmap.put("topic_id_sess", topicIdSessAssi);
        }
        topicIdSessAssi = Integer.parseInt(String.valueOf(sessionmap.get("topic_id_sess")));
        System.out.println(topicIdSessAssi);
        DBConnect db = new DBConnect();
        db.connect();
//        System.out.println("After db con");
        try {
          
            PreparedStatement ps = db.con.prepareStatement(strAssignAttempt);
          
            ps.setInt(1, topicIdSessAssi);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                assignmentIdTemp = rs.getInt("assignment_id");
            }

            PreparedStatement psAttempt = db.con.prepareStatement(strAssignmentAttempt);
            psAttempt.setInt(1, assignmentIdTemp);

            ResultSet rs1 = psAttempt.executeQuery();
            while (rs1.next()) {
                Assignment asb = new Assignment();
                asb.setAssignmentid(rs1.getInt("assignment_id"));
                  //check start//
              PreparedStatement ps_check=db.con.prepareStatement(checkAsssign);
            ps_check.setInt(1,rs1.getInt("assignment_id"));
            ps_check.setString(2,String.valueOf(sessionmap.get("login")));
            ResultSet rs_check=ps_check.executeQuery();
            if(rs_check.next())
            {
                exist=true;
                status="exist";
                sessionmap.put("exist","yes");
                
            }
            else{
            
            //check end//
//                 System.out.println("assign");
                asb.setAssignmentname(rs1.getString("assignment_name"));
                //  asb.setAssignmentcreationdate(String.valueOf(rs.getDate("assignment_creation_date")));
                //   asb.setStudentId(rs.getInt("student_id"));
                //  asb.set(rs.getString("verified"));
                //    asb.set(rs.getString("remarks"));
                asb.setTitle(rs1.getString("title"));

                assbeanlist.add(asb);
//                System.out.println(assbeanlist.size());

                sessionmap.put("assignIdSess", rs.getString("assignment_id"));
                status = "success";
            }
            }
        

        } catch (SQLException ex) {
            Logger.getLogger(ManageStudent.class.getName()).log(Level.SEVERE, null, ex);
        }

        // db.disconnect();
        return status;

    }
    private static final long serialVersionUID = 1L;
    private File userImage;
    private String userImageContentType;

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public File getUserImage() {
        return userImage;
    }

    public void setUserImage(File userImage) {
        this.userImage = userImage;
    }

    public String getUserImageContentType() {
        return userImageContentType;
    }

    public void setUserImageContentType(String userImageContentType) {
        this.userImageContentType = userImageContentType;
    }

    public String getUserImageFileName() {
        return userImageFileName;
    }

    public void setUserImageFileName(String userImageFileName) {
        this.userImageFileName = userImageFileName;
    }
    private String userImageFileName;

    private HttpServletRequest servletRequest;

    public String uploadAssignmentFile() {
          int topicIdSessAssi1=0;
        int assignmentIdTemp1 = 0;
        String qu = "insert into student_assignment(student_id,assignment_id,assignment_submission_content,assignment_sub_date,verified) values(?,?,?,CURRENT_TIMESTAMP,?)";
        String strAssignAttempt1 = "select * from course_topic where topic_id=?";
     
         System.out.println("printing for test1");
        
        topicIdSessAssi1 = Integer.parseInt(String.valueOf(sessionmap.get("topic_id_sess")));
        System.out.println("topic id sess"+topicIdSessAssi1);
        DBConnect db = new DBConnect();
        db.connect();
        
        try {
            PreparedStatement ps = db.con.prepareStatement(strAssignAttempt1);
            ps.setInt(1, topicIdSessAssi1);
            ResultSet rs = ps.executeQuery();
            String notify_assign=null;
            if (rs.next()) {
                assignmentIdTemp1 = rs.getInt("assignment_id");
                    notify_assign="insert into notification values('','"+String.valueOf(sessionmap.get("login"))+"','student has submitted the assignment',CURRENT_TIMESTAMP,'admin',"+assignmentIdTemp1+")";
            }
              PreparedStatement psnotify = db.con.prepareStatement(notify_assign);
              int k=psnotify.executeUpdate();
            PreparedStatement pstorun = db.con.prepareStatement(qu);
            String filePath = "D:/uploadContent/Doc_Content/";
           
            System.out.println("Location:" + filePath); //see the server console for actual location  
            File fileToCreate = new File(filePath, this.userImageFileName);
            FileUtils.copyFile(this.userImage, fileToCreate);//copying image in the new file  

            pstorun.setString(1, String.valueOf(sessionmap.get("login")));
            pstorun.setInt(2, assignmentIdTemp1);
            pstorun.setString(3, filePath + userImageFileName);
            pstorun.setString(4, "pending");
            
            pstorun.executeUpdate();
            // db.disconnect();
            sessionmap.put("assignUpload","yes");
            return "success";
        } catch (Exception e) {
            System.err.println(e);
            return "error";

        }
        
    }

    public String enrolledCourse() throws ParseException {
        db.connect();
       // System.out.println("Session value");
       // System.out.println(sessionmap.get("check_course"));
        String param, query, result = "error";
        if (getServletRequest().getParameter("id") != null) {
            param = getServletRequest().getParameter("id");
            sessionmap.put("student_id", param);
        }
        param = String.valueOf(sessionmap.get("login"));
        query = "select course.course_name,course.duration,student_course.registration_time,student_course.course_completion_time,course.course_id from course inner join student_course on course.course_id=student_course.course_id inner join student on student_course.student_id=student.student_id where student.student_id='" + param + "'";
        try {
            PreparedStatement ps_course = db.con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            ResultSet rs_course = ps_course.executeQuery();
            if (rs_course.next()) {
                rs_course.previous();
                while (rs_course.next()) {
                    StudentCourse sc = new StudentCourse();
                    sc.setCourse_name(rs_course.getString(1));

                    sc.setRegistration_time(String.valueOf(rs_course.getDate(3)));
                    //Calander c=new Calender();

                    sc.setDuration(rs_course.getString(2));
                    sc.setCourseid(rs_course.getInt(5));
                    Calendar cal = Calendar.getInstance();

                    cal.setTime((Date) rs_course.getDate(3));
                    int week = Integer.parseInt(rs_course.getString(2));
                    System.out.println("above week");
                    System.out.println(week);
                    System.out.println("below week");
                    int days = week * 7;
                    System.out.println(days);
                    cal.add(Calendar.DAY_OF_MONTH, days);

                 
                    SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
                    String deadline = format1.format(cal.getTime());
                    sc.setDeadline(String.valueOf(deadline));

                    if (rs_course.getDate(4) != null) {
                        sc.setCourse_completion_time(String.valueOf(rs_course.getDate(4)));

                        sc.setTime_remaining("---");

                    } else {
                        sc.setCourse_completion_time("yet not completed");
                        Date date = new Date();
                        Date d1 = new SimpleDateFormat("yyyy-M-dd").parse((String) deadline);
                        Date d2 = new SimpleDateFormat("yyyy-M-dd").parse(format1.format(date));
                        //  Date  d2 = new SimpleDateFormat("yyyy-M-dd").parse(rs_course.getDate(param));
                        long diff = Math.abs(d1.getTime() - d2.getTime());
                        long diffDays = diff / (24 * 60 * 60 * 1000);
                        System.out.println("Differece of the days");
                        System.out.println(date);
                        System.out.println(diffDays);
                        sc.setTime_remaining(String.valueOf(diffDays));

                    }

                    course_list.add(sc);
                }
                result = "success";
                out.print("<script> alert ('added successfully!') </ script>");
                // sessionmap.put("check_course",null);

            } else {
                sessionmap.put("check_course", "nocourse");
                /*StudentCourse sc=new StudentCourse();
                 sc.setCourse_name("have not registered for any course");
                 sc.setRegistration_time("not registered");
                 sc.setCourse_completion_time("--");
                 course_list.add(sc);*/
                studentList();

                result = "successNoCourse";

            }

        } catch (SQLException e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return result;
    }

    

    public String enrolledCourseTopic() {
        db.connect();
        String param, query, result = "error";
        int courseid = 0;

        param = String.valueOf(sessionmap.get("student_id"));
        if (getServletRequest().getParameter("cid") != null) {
            courseid = Integer.parseInt(getServletRequest().getParameter("cid"));
            //sessionmap.put("course_id", param);
        }
        query = "select * from topic inner join course_topic on topic.topic_id=course_topic.topic_id where course_topic.course_id=" + courseid + "";
        try {
            PreparedStatement ps_topic = db.con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            ResultSet rs_topic = ps_topic.executeQuery();

            while (rs_topic.next()) {
                ManageTopic ad = new ManageTopic();
                ad.setTopicid(rs_topic.getInt("topic_id"));
                ad.setTopicname(rs_topic.getString("topic_name"));
                ad.setTopicdescription(rs_topic.getString("topic_description"));
                topiclist.add(ad);

            }
        } catch (SQLException e) {
            System.out.println(e);
            e.printStackTrace();
        }

        return "success";

    }
   

    @Override
    public void setSession(Map map) {
        sessionmap = (SessionMap) map;

    }
    //StudentDetail had been added here///
    

 String param;

private ArrayList<Student> list_student=new ArrayList<Student>();
    

    public ArrayList<Student> getList_student() {
        return list_student;
    }

    public void setList_student(ArrayList<Student> list_student) {
        this.list_student = list_student;
    }


    

   
   
    

    public String studentDetail()  {
        
      // ServletContext ctx=ServletActionContext.getServletContext();  
       //list_student =(ArrayList)ctx.getAttribute("list_s");  
        System.out.println("in studentDetail");
        DBConnect db=new DBConnect();
        db.connect();
        try
        {
           //StudentList sl=new StudentList();
       //String st= sl.execute();
        //    System.out.println("in studentDetail in try");
            if(getServletRequest().getParameter("id")!=null)
            {  param=getServletRequest().getParameter("id"); 
               sessionmap.put("student_id", param);
            }
         param=String.valueOf(sessionmap.get("student_id"));
        PreparedStatement ps_course=db.con.prepareStatement("select course.course_name,student_course.registration_time,student_course.course_completion_time from course inner join student_course on course.course_id=student_course.course_id inner join student on student_course.student_id=student.student_id where student.student_id='"+param+"'");
        PreparedStatement ps_student=db.con.prepareStatement("select * from student where student_id='"+param+"'");
        //System.out.println("in studentDetail below ps");
      ResultSet rs_course=  ps_course.executeQuery();
      //System.out.println("in studentDetail below course");
      ResultSet rs_student=ps_student.executeQuery();
      //System.out.println("in studentDetail below student");
      
      if(rs_course.next())
      {
          StudentCourse sc=new StudentCourse();        
              sc.setCourse_name(rs_course.getString(1));
          
          sc.setRegistration_time(String.valueOf(rs_course.getDate(2)));
          
       
          if(rs_course.getDate(3)!=null)
          
          {
              sc.setCourse_completion_time(String.valueOf(rs_course.getDate(3)));
              
          }
          else
          {
              sc.setCourse_completion_time("yet not completed");
          }
          
      
          course_list.add(sc);
        
      }
      else
      {
           StudentCourse sc=new StudentCourse();
                    sc.setCourse_name("have not registered for any course");
                    sc.setRegistration_time("not registered");
                    sc.setCourse_completion_time("--");
                    course_list.add(sc);
      }
      
      while(rs_student.next())
      {
          Student stud=new Student();
          stud.setStudentid(rs_student.getString(1));
                stud.setFname(rs_student.getString(2));
                stud.setLname(rs_student.getString(3));
                stud.setEmailid(rs_student.getString(4));
                stud.setContactno(rs_student.getLong("contact_no"));
                stud.setStatus(rs_student.getString(6));
                System.out.println("hello1" + rs_student.getString(2));
                list_student.add(stud);
      }
        
        System.out.print(param);
        }
        catch(SQLException e)
        {
            System.out.println(e);
            e.printStackTrace();
        }
        return "success";
                

                
                
    }
   public String changeStatusNotify()
   {
       
    String result=null;
    int notid=0;
        DBConnect db=new DBConnect();
        db.connect();
      
     
       
        try
        {
           
            if(getServletRequest().getParameter("id")!=null)
            {  param=getServletRequest().getParameter("id"); 
               sessionmap.put("student_id", param);
            }
            if(getServletRequest().getParameter("notid")!=null)
            {
                 notid=Integer.parseInt(String.valueOf(getServletRequest().getParameter("notid"))); 
               sessionmap.put("notification_idsess",notid);
            }
            
         param=String.valueOf(sessionmap.get("student_id"));
         String status="select * from student where student_id='"+param+"'";
         String delete="delete from notification where notification_id="+notid+"";
          PreparedStatement ps_student=db.con.prepareStatement(status);
           PreparedStatement ps_del=db.con.prepareStatement(delete);
           int i=ps_del.executeUpdate();
            System.out.println("printing notification id"+i);
          ResultSet rs_student= ps_student.executeQuery();
         
         if(rs_student.next())
      {
          Student stud=new Student();
          stud.setStudentid(rs_student.getString(1));
                stud.setFname(rs_student.getString(2));
                stud.setLname(rs_student.getString(3));
                stud.setEmailid(rs_student.getString(4));
                stud.setContactno(rs_student.getLong("contact_no"));
                
                stud.setRegistrationTime(String.valueOf(rs_student.getTimestamp("registration_time")));
                //stud.setStatus(rs_student.getString(6));
                System.out.println("hello1" + rs_student.getString(2));
                list_student.add(stud);
                result= "success";
      }
         else{
             result="error";
         }
         
   }
         catch(SQLException e)
        {
            System.out.println(e);
            e.printStackTrace();
            
        }
        return result;
   }
//StudentList is here//
    
    

    public String studentListExtract() {
        try {
            DBConnect db = new DBConnect();
            db.connect();
            String query;
  // String query="select * from student";
            //Statement ps=db.con.createStatement();
            PreparedStatement ps1 = db.con.prepareStatement("select * from student inner join login on student.student_id=login.login_id");
            int i=0;
            ResultSet rs = ps1.executeQuery();
            while (rs.next()) {
                Student stud = new Student();
                stud.setStudentid(rs.getString(1));
               /* query="select status from login where login_id="+rs.getString(1)+"";
                PreparedStatement ps2 = db.con.prepareStatement(query);
                ResultSet rs1 = ps2.executeQuery();
                if(rs1.next())
                {
                    stud.setStatus(rs1.getString(1));
                }*/
                stud.setFname(rs.getString(2));
                stud.setLname(rs.getString(3));
                stud.setEmailid(rs.getString(4));
                stud.setContactno(rs.getLong("contact_no"));
               stud.setStatus(rs.getString("status"));
               
                list.add(stud);
                System.out.println("Print list");
                System.out.println(">"+list.get(i++).getFname());
              
              
                

            }
        } catch (SQLException e) {
            
            e.printStackTrace();
        }

        return "success";

    }
//StudentUpdate is here//
    
    
    public String studentUpdate()
    { 
        
        db.connect();
       String id= String.valueOf(sessionmap.get("student_id"));
       return "success";
        
    }

    //Update Profile ///
   

    RegisterAction userbean = new RegisterAction();
   
    String stu_id;
    
   // ArrayList<RegisterAction> UserDetailsList= new ArrayList<RegisterAction>();

    

    public RegisterAction getUserbean() {
        return userbean;
    }

    public void setUserbean(RegisterAction userbean) {
        this.userbean = userbean;
    }

    public String loadProfile() {

        HttpSession session= ServletActionContext.getRequest().getSession(false);
        if(session==null|| session.getAttribute("login")==null)
        {
            return "login";
        }
        
        DBConnect db = new DBConnect();
        db.connect();
        String s = "select * from student where student_id=?";
        try {
            String sess= String.valueOf(session.getAttribute("login"));
            //System.out.println(sess);
            PreparedStatement ps = db.con.prepareStatement(s);
            ps.setString(1, sess);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                userbean.setStudentId(rs.getString("student_id"));
                userbean.setFname(rs.getString("f_name"));
                userbean.setLname(rs.getString("l_name"));
                userbean.setEmailId(rs.getString("email_id"));
                userbean.setContactno(rs.getLong("contact_no"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return SUCCESS;
    }

    public String updateProfile() {

        HttpSession session= ServletActionContext.getRequest().getSession(false);
        if(session==null|| session.getAttribute("login")==null)
        {
            return "login";
        }
        try {
            String sess= String.valueOf(session.getAttribute("login"));
            DBConnect db = new DBConnect();
            db.connect();
            String s = "update student set f_name=?,l_name=?,contact_no=? where student_id=?";
            PreparedStatement ps = db.con.prepareStatement(s);
            ps.setString(1, userbean.fname);
            ps.setString(2, userbean.lname);

            ps.setLong(3, userbean.contactno);
            ps.setString(4, sess);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return SUCCESS;

    }
    public String quizStatus()
    {
        db.connect();
        String status=null;
        String studId = String.valueOf(sessionmap.get("student_id"));
        int quizid=Integer.parseInt(getServletRequest().getParameter("quizidq"));
        System.out.println("print student id"+studId);
        String query="select * from student_quiz where quiz_id="+quizid+" and student_id='"+studId+"'";
        String quiz_name="select * from quiz where quiz_id="+quizid+"";
       
        try{
              PreparedStatement psq = db.con.prepareStatement(quiz_name);
         PreparedStatement ps = db.con.prepareStatement(query);
        ResultSet rs_quiz=ps.executeQuery();
        ResultSet rsq=psq.executeQuery();
        rsq.next();
        ManageTopic mt= new ManageTopic();
         mt.setQuizname(rsq.getString("quiz_name"));
        if(rs_quiz.next())
        {
            
           
            mt.setQuizmarks(String.valueOf(rs_quiz.getInt("quiz_marks")));
            mt.setQuizsubdate(rs_quiz.getDate("quiz_sub_date").toString());
            
            topiclist.add(mt);
        }
        else
        {
            mt.setQuizmarks("N/A");
            mt.setQuizsubdate("quiz is not submitted");
            
        }
        status="success";
        }
         catch (Exception e) {
            e.printStackTrace();
            status="error";
        }
        
        
        return status;
    }
     public String assignStatus()
     {
         db.connect();
        String status=null;
        String studId = String.valueOf(sessionmap.get("student_id"));
        int assignid=Integer.parseInt(getServletRequest().getParameter("assignidq"));
        System.out.println("print student id"+studId);
        String query="select * from student_assignment where assignment_id="+assignid+" and student_id='"+studId+"'";
        String quiz_name="select * from assignment where assignment_id="+assignid+"";
          try{
              PreparedStatement psq = db.con.prepareStatement(quiz_name);
         PreparedStatement ps = db.con.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        ResultSet rs_quiz=ps.executeQuery();
        ResultSet rsq=psq.executeQuery();
        rsq.next();
        ManageTopic mt= new ManageTopic();
        mt.setAssignementname(rsq.getString("assignment_name"));
        mt.setTitle(rsq.getString("title"));
        if(rs_quiz.next())
        {
            rs_quiz.previous();
            while(rs_quiz.next())
            {
                      ManageTopic mt1= new ManageTopic();   
                       mt1.setAssignementname(rsq.getString("assignment_name"));
               mt1.setTitle(rsq.getString("title"));
                mt1.setAssignmentContent(rs_quiz.getString("assignment_submission_content"));
                mt1.setAssignsubdate(rs_quiz.getDate("assignment_sub_date").toString());
                mt1.setVerified(rs_quiz.getString("verified"));
                mt1.setRemarks(rs_quiz.getString("remarks"));
                topiclist.add(mt1);
            }
            status="success";
        }
        else
        {
            mt.setAssignmentContent("N/A");
            mt.setAssignsubdate("not submitted");
            mt.setVerified("N/A");
            mt.setRemarks("N/A");
            topiclist.add(mt);
            status="success";
        }
          }
          catch (Exception e) {
            e.printStackTrace();
            status="error";
        }
          return status;
          
          
}
     public String notifyAssignView()
       {
       
           db.connect();
           String studid;
           String status=null;
           int assignid= Integer.parseInt(getServletRequest().getParameter("assignid"));
           int notid=Integer.parseInt(getServletRequest().getParameter("notid"));
           studid=getServletRequest().getParameter("studid");
           String que="select * from assignment where assignment_id="+assignid+"";
           String assign="select * from student_assignment where assignment_id="+assignid+" and student_id='"+studid+"'";
           String notific="delete from notification where notification_id="+notid+"";
           try{
               PreparedStatement psq = db.con.prepareStatement(que);
               PreparedStatement psqass = db.con.prepareStatement(assign);
               PreparedStatement notify = db.con.prepareStatement(notific);
               ResultSet rs_que=psq.executeQuery();
               ResultSet rs_studa=psqass.executeQuery();
              
               rs_que.next();
               rs_studa.next();
               sessionmap.put("studsession", rs_studa.getString("student_assignment_id"));
               ManageTopic mt=new ManageTopic();
               mt.setAssignementname(rs_que.getString("assignment_name"));
               mt.setTitle(rs_que.getString("title"));
               mt.setAssignmentContent(rs_studa.getString("assignment_submission_content"));
               mt.setAssignsubdate(rs_studa.getDate("assignment_sub_date").toString());
               mt.setVerified(rs_studa.getString("verified"));
               mt.setRemarks(rs_studa.getString("remarks"));
               topiclist.add(mt);
               status="success";
                int i=notify.executeUpdate();
           }
            catch (Exception e) {
            e.printStackTrace();
            status="error";
        }
    
 return status;
}
     
}
