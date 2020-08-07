package com.elearning.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import oracle.jdbc.OraclePreparedStatement;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

public class ManageCourse implements SessionAware, ServletRequestAware {

    DBConnect db = new DBConnect();
    SessionMap<String, Object> sessionmap;
    HttpServletRequest request;
    public String courseid, coursename, description, duration, uploaded;
    static int temp;

    public String getUploaded() {
        return uploaded;
    }

    public void setUploaded(String uploaded) {
        this.uploaded = uploaded;
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
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

    public String getCourseid() {
        return courseid;
    }

    public void setCourseid(String courseid) {
        this.courseid = courseid;
    }

    private ArrayList<Course> course_detail = new ArrayList<Course>();

    public ArrayList<Course> getCourse_detail() {
        return course_detail;
    }

    public void setCourse_detail(ArrayList<Course> course_detail) {
        this.course_detail = course_detail;
    }

    public String deleteTest() {

        db.connect();
        int i = 0, j = 0, k = 0, l = 0, m = 0, n = 0, o = 0, p = 0;

        String flag1 = "false", flag2 = "false";
        String result = null;

        String query_test1 = "select * from student_course where course_id=" + courseid + "";
        String query_test2 = "select * from course_topic where course_id=" + courseid + "";
        String query = "delete from course where course_id=" + courseid + "";

        try {
            PreparedStatement ps_sc = db.con.prepareStatement(query_test1);
            PreparedStatement ps_tc = db.con.prepareStatement(query_test2);

            ResultSet rs1 = ps_sc.executeQuery();
            ResultSet rs2 = ps_tc.executeQuery();
            while (rs1.next()) {
                String query_st = "delete from student_course where course_id=" + courseid + "";
                flag1 = "true";
                PreparedStatement ps1 = db.con.prepareStatement(query_st);
                i = ps1.executeUpdate();

            }
            while (rs2.next()) {
                int topicid, quizid, assignmenttid, question_id;
                topicid = rs2.getInt("topic_id");

                String query_cs = "delete from course_topic where course_id=" + courseid + "";
                flag2 = "true";
                PreparedStatement ps2 = db.con.prepareStatement(query_cs);
                j = ps2.executeUpdate();
                if (rs2.getInt("topic_id") > 0) {

                    String queryt = "delete from topic where topic_id=" + topicid + "";
                    PreparedStatement ps_t = db.con.prepareStatement(queryt);
                    p = ps_t.executeUpdate();
                }
                if (rs2.getInt("quiz_id") > 0) {
                    quizid = rs2.getInt("quiz_id");

                    String queryq = "delete from quiz where quiz_id=" + quizid + "";
                    String queryque = "delete from question1 where quizid=" + quizid + "";
                    PreparedStatement ps_que = db.con.prepareStatement(queryque);
                    m = ps_que.executeUpdate();
                    PreparedStatement ps_quiz = db.con.prepareStatement(queryq);
                    n = ps_quiz.executeUpdate();

                }
                if (rs2.getInt("assignment_id") > 0) {
                    assignmenttid = rs2.getInt("assignment_id");
                    System.out.println(assignmenttid);
                    String querya = "delete from assignment where assignment_id=" + assignmenttid + "";
                    PreparedStatement ps_assign = db.con.prepareStatement(querya);
                    o = ps_assign.executeUpdate();

                }

            }
            PreparedStatement ps = db.con.prepareStatement(query);
            k = ps.executeUpdate();
            if (flag1.equals("false") && flag2.equals("true")) {

                if (k > 0 && j > 0 && m > 0 && n > 0 && o > 0 && p > 0) {
                    result = "success";
                }
            } else if (flag1.equals("false") && flag2.equals("false")) {
                if (k > 0) {
                    result = "success";
                }
            } else if (flag1.equals("true") && flag2.equals("true")) {
                if (k > 0 && i > 0 && j > 0) {
                    result = "success";
                }
            } else {
                result = "error";
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
        return result;
    }

    public String EditShow() {
        db.connect();
        String query = "select * from course where course_id=" + courseid + "";
        temp = Integer.parseInt(courseid);

        try {

            PreparedStatement ps_sc = db.con.prepareStatement(query);
            ResultSet rs1 = ps_sc.executeQuery();
            while (rs1.next()) {
                Course cs = new Course();
                cs.setCourseid(rs1.getInt("course_id"));
                cs.setCoursename(rs1.getString("course_name"));
                cs.setCreationdate(String.valueOf(rs1.getDate("course_creation_date")));
                cs.setDuration(String.valueOf(rs1.getString("duration")));
                cs.setDescription(String.valueOf(rs1.getString("course_description")));
                course_detail.add(cs);
            }
        } catch (Exception e) {

            e.printStackTrace();
        }
        return "success";

    }

    public String Edit() {
        db.connect();

        String result = null;
        String Query_update = "update course set course_name=?,duration=?,course_description=? where course_id=" + temp + "";
        try {
            PreparedStatement ps_que = db.con.prepareStatement(Query_update);
            ps_que.setString(1, coursename);
            ps_que.setString(2, description);
            ps_que.setString(3, duration);
            int m = ps_que.executeUpdate();
            if (m > 0) {
                result = "success";
            } else {
                result = "error";
            }
        } catch (Exception e) {

            e.printStackTrace();
        }
        return result;

    }

    public String delete() {
        db.connect();
        int i = 0;
        String result = null;
        String query = "delete from course where course_id=" + courseid + "";
        try {
            PreparedStatement ps_que = db.con.prepareStatement(query);
            int j = ps_que.executeUpdate();
            if (j > 0) {
                result = "success";
                sessionmap.put("coursedeleted", "yes");

            } else {
                result = "error";
            }
        } catch (Exception e) {

            e.printStackTrace();
        }
        return result;
    }

    public String upload() {
        db.connect();
        String result = null;
        boolean content = false, exe = false;
        int courseid = Integer.parseInt(String.valueOf(sessionmap.get("courseidselect")));

        String query = "update course set uploaded='yes' where course_id=" + courseid + "";
        String upload_check = "select * from course_topic where course_id=" + courseid + "";
        try {
            PreparedStatement ps_que = db.con.prepareStatement(query);
            PreparedStatement ps_check = db.con.prepareStatement(upload_check, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = ps_check.executeQuery();
            if (rs.next()) {
                rs.previous();
                while (rs.next()) {
                    if (rs.getInt("topic_id") > 0 && (rs.getInt("assignment_id") > 0 || rs.getInt("quiz_id") > 0)) {
                        exe = true;
                        String check_content = "select * from topic_content where topic_id=" + rs.getInt("topic_id") + "";
                        PreparedStatement pst = db.con.prepareStatement(check_content);
                        ResultSet rs_t = pst.executeQuery();
                        if (rs_t.next()) {
                            content = true;
                        }

                    }
                }
            }
            if (content == true && exe == true) {
                int j = ps_que.executeUpdate();
                if (j > 0) {
                    sessionmap.put("uploaded", "yes");
                    result = "success";
                } else {
                    result = "error";
                }

            } else if (content == true && exe == false) {
                sessionmap.put("assign_quiz", "no");
                result = "noAssignQuiz";

            } else if (content == false && exe == true) {
                sessionmap.put("content", "no");
                result = "noContent";
            } else if (content == false && exe == false) {
                sessionmap.put("both", "no");
                result = "noBothThing";
            }

            //  if(j>0)
        } catch (Exception e) {

            result = "error";
            e.printStackTrace();
        }
        return result;
    }

    public String changeUpload() {
        db.connect();
        String result = null;
        boolean content = false, exe = false;
        String query = null;
        String param;
        if (getServletRequest().getParameter("id") != null) {
            param = getServletRequest().getParameter("id");
            sessionmap.put("course_id", param);
        }
        param = String.valueOf(sessionmap.get("course_id"));
        String upload_check = "select * from course_topic where course_id=" + param + "";

        try {

            String check_status = "select uploaded from course where course_id=" + param + "";
            PreparedStatement ps_check = db.con.prepareStatement(check_status);
            ResultSet rs = ps_check.executeQuery();
            while (rs.next()) {
                if (rs.getString("uploaded").equals("yes")) {
                    query = "update course set uploaded='no' where course_id=" + param + "";
                    PreparedStatement ps_que = db.con.prepareStatement(query);
                    int k = ps_que.executeUpdate();
                    sessionmap.put("changed", "yes");
                    result = "success";
                    System.out.println(query);

                } else if (rs.getString("uploaded").equals("no")) {
                    query = "update course set uploaded='yes' where course_id=" + param + "";

                    PreparedStatement ps_checku = db.con.prepareStatement(upload_check, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                    ResultSet rs2 = ps_checku.executeQuery();
                    if (rs2.next()) {
                        rs2.previous();
                        while (rs2.next()) {
                            if (rs2.getInt("topic_id") > 0 && (rs2.getInt("assignment_id") > 0 || rs2.getInt("quiz_id") > 0)) {
                                exe = true;
                                String check_content = "select * from topic_content where topic_id=" + rs2.getInt("topic_id") + "";
                                PreparedStatement pst = db.con.prepareStatement(check_content);
                                ResultSet rs_t = pst.executeQuery();
                                if (rs_t.next()) {
                                    content = true;
                                }

                            }
                        }
                        if (content == true && exe == true) {
                            PreparedStatement ps_que = db.con.prepareStatement(query);
                            int j = ps_que.executeUpdate();
                            if (j > 0) {
                                sessionmap.put("changed", "yes");
                                result = "success";
                            } else {
                                result = "error";
                            }

                        } else if (content == true && exe == false) {
                            sessionmap.put("assign_quiz", "no");
                            result = "noAssignQuiz";

                        } else if (content == false && exe == true) {
                            sessionmap.put("content", "no");
                            result = "noContent";
                        } else if (content == false && exe == false) {
                            sessionmap.put("both", "no");
                            result = "noBothThing";
                        }
                    } else {
                        sessionmap.put("notopic", "yes");
                        result = "notopic";

                    }
                }
            }

        } catch (SQLException e) {
            System.out.println(e);
            e.printStackTrace();
        }

        return result;
    }

    public void setSession(Map map) {
        sessionmap = (SessionMap) map;

    }

    public HttpServletRequest getServletRequest() {
        return request;
    }

    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }
    //CourseList has been Added  here
    private ArrayList<Course> list = new ArrayList<Course>();

    public ArrayList<Course> getList() {
        return list;
    }

    public void setList(ArrayList<Course> list) {
        this.list = list;
    }

    public String courseList() {
        try {
            DBConnect db = new DBConnect();
            db.connect();

            PreparedStatement ps = db.con.prepareStatement("select * from course");
            PreparedStatement ps_check = db.con.prepareStatement("select * from student_course where student_id='" + sessionmap.get("login") + "'", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            ResultSet rs = ps.executeQuery();
            ResultSet rs_check = ps_check.executeQuery();
            while (rs.next()) {
                Course cs = new Course();

                cs.setCourseid(rs.getInt(1));
                cs.setCoursename(rs.getString(2));
                cs.setCreationdate(String.valueOf(rs.getDate(3)));
                cs.setDescription(rs.getString(4));
                cs.setDuration(rs.getString(5));
                cs.setUploaded(rs.getString(6));

                while (rs_check.next()) {
                    if (rs_check.getInt("course_id") == rs.getInt("course_id")) {
                        cs.setEnrollcheck("enrolled");
                    } else {
                        cs.setEnrollcheck("unenrolled");
                    }

                }
                while (rs_check.previous()) {

                }

                list.add(cs);

            }
        } catch (SQLException e) {
        }

        return "success";

    }

     //Course has been added here//
    private String Creationdate;

    public String getCreationdate() {
        return Creationdate;
    }

    public void setCreationdate(String Creationdate) {
        this.Creationdate = Creationdate;
    }

    //AddCourse Class has been added////
    private String coursedescription;

    public String getCoursedescription() {
        return coursedescription;
    }

    public void setCoursedescription(String coursedescription) {
        this.coursedescription = coursedescription;
    }

    public String addCourse() {
        int i = save(this);
        if (i > 0) {

            sessionmap.put("courseadded", "yes");
            return "success";
        }
        return "error";
    }

    public int save(ManageCourse cs) {
        int status = 0;
        try {
            DBConnect db = new DBConnect();
            db.connect();
            String query = "insert into course values('',?,sysdate,?,?,'no') returning course_id into ?";

            OraclePreparedStatement ps;
            ps = (OraclePreparedStatement) db.con.prepareStatement(query);
            ps.setString(1, cs.getCoursename());
            ps.setString(2, cs.getDuration());
            ps.setString(3, cs.getCoursedescription());
            ps.registerReturnParameter(4, Types.INTEGER);
            status = ps.executeUpdate();
            ResultSet rs = ps.getReturnResultSet();
            rs.next();
            System.out.print("to check return");
            String courseid = rs.getString(1);

            System.out.println(courseid);
            sessionmap.put("courseidselect", courseid);

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return status;
    }
    //upload file..//
    private String fileName;
    private InputStream inputStream;
    private long fileSize;

    
    public String downloadFile() throws Exception {
        /*
         *This is a security hole begging to be exploited.
         *A user can submit "../../../../someImportantFile"
         *and potentially download arbitrary files from the server.
         *You really need to do some validation on the input!
         */
       // System.out.println("hghgvbhnbvhn");
        String param = getServletRequest().getParameter("fname");
        final File fileToDownload = new File(param);
        fileName = fileToDownload.getName();
        //contentLength = fileToDownload.length();
        fileSize = fileToDownload.length();
        inputStream = new FileInputStream(fileToDownload);
        return "downloadFile";
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getFileSize() {
        return fileSize;
    }

    public InputStream getFileToDownload() {
        return inputStream;
    }

    public String getContentDisposition() {
        return "attachment;filename=\"" + fileName + "\"";
    }

    public String getContentType() {
        return "text/plain";
    }
    
    public String courseListToEnroll() {
        try {
            DBConnect db = new DBConnect();
            db.connect();

           // PreparedStatement ps = db.con.prepareStatement("select * from course");
            PreparedStatement ps = db.con.prepareStatement("select * from course where not exists(select * from student_course where course.course_id=STUDENT_COURSE.COURSE_ID and student_course.student_id='"+sessionmap.get("login")+"')");
            PreparedStatement ps_check = db.con.prepareStatement("select * from student_course where student_id='" + sessionmap.get("login") + "'", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            ResultSet rs = ps.executeQuery();
            ResultSet rs_check = ps_check.executeQuery();
            while (rs.next()) {
                Course cs = new Course();

                cs.setCourseid(rs.getInt(1));
                cs.setCoursename(rs.getString(2));
                cs.setCreationdate(String.valueOf(rs.getDate(3)));
                cs.setDescription(rs.getString(4));
                cs.setDuration(rs.getString(5));
                cs.setUploaded(rs.getString(6));

                while (rs_check.next()) {
                    if (rs_check.getInt("course_id") == rs.getInt("course_id")) {
                        cs.setEnrollcheck("enrolled");
                    } else {
                        cs.setEnrollcheck("unenrolled");
                    }

                }
                while (rs_check.previous()) {

                }

                list.add(cs);

            }
        } catch (SQLException e) {
        }

        return "success";

    }
      public String courseListToDisenroll() {
        try {
            
            DBConnect db = new DBConnect();
            db.connect();

           // PreparedStatement ps = db.con.prepareStatement("select * from course");
            PreparedStatement ps = db.con.prepareStatement("select * from course where exists(select * from student_course where course.course_id=STUDENT_COURSE.COURSE_ID and student_course.student_id='"+sessionmap.get("login")+"')");
            PreparedStatement ps_check = db.con.prepareStatement("select * from student_course where student_id='" + sessionmap.get("login") + "'", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            ResultSet rs = ps.executeQuery();
            ResultSet rs_check = ps_check.executeQuery();
            while (rs.next()) {
                Course cs = new Course();

                cs.setCourseid(rs.getInt(1));
                cs.setCoursename(rs.getString(2));
                cs.setCreationdate(String.valueOf(rs.getDate(3)));
                cs.setDescription(rs.getString(4));
                cs.setDuration(rs.getString(5));
                cs.setUploaded(rs.getString(6));

                while (rs_check.next()) {
                    if (rs_check.getInt("course_id") == rs.getInt("course_id")) {
                        cs.setEnrollcheck("enrolled");
                    } else {
                        cs.setEnrollcheck("unenrolled");
                    }

                }
                while (rs_check.previous()) {

                }

                list.add(cs);

            }
        } catch (SQLException e) {
        }

        return "success";
        

    }


}
