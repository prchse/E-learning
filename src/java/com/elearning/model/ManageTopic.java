/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elearning.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import oracle.jdbc.OraclePreparedStatement;
import org.apache.catalina.Session;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import sun.font.Script;

/**
 *
 * @author PRAGYA
 */
public class ManageTopic extends Quiz implements ServletRequestAware, SessionAware {

    DBConnect db = new DBConnect();
    HttpServletRequest request;
    SessionMap<String, Object> sessionmap;
    String topicname, topicdescription, quizname, duration, title, assignementname, topicCompleteDate;
    int quizId, assignId;

    public int getQuizId() {

        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

    public int getAssignId() {
        return assignId;
    }

    public void setAssignId(int assignId) {
        this.assignId = assignId;
    }

    public String getTopicCompleteDate() {
        return topicCompleteDate;
    }

    public void setTopicCompleteDate(String topicCompleteDate) {
        this.topicCompleteDate = topicCompleteDate;
    }
    Quiz q = new Quiz();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAssignementname() {
        return assignementname;
    }

    public void setAssignementname(String assignementname) {
        this.assignementname = assignementname;
    }

    public String getQuizname() {
        return quizname;
    }

    public void setQuizname(String quizname) {
        this.quizname = quizname;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getTopicname() {
        return topicname;
    }

    public void setTopicname(String topicname) {
        this.topicname = topicname;
    }

    public String getTopicdescription() {
        return topicdescription;
    }

    public void setTopicdescription(String topicdescription) {
        this.topicdescription = topicdescription;
    }
    private ArrayList<ManageTopic> topicdetail = new ArrayList<ManageTopic>();

    public ArrayList<ManageTopic> getTopicdetail() {
        return topicdetail;
    }

    public void setTopicdetail(ArrayList<ManageTopic> topicdetail) {
        this.topicdetail = topicdetail;
    }

    public HttpServletRequest getServletRequest() {
        return request;
    }

    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
//To change body of generated methods, choose Tools | Templates.
    }

    public String delete() {
        String status = "error";
        db.connect();
        int topicid = Integer.parseInt(getServletRequest().getParameter("id"));

        String query = "delete from course_topic where topic_id=" + topicid + "";

        try {
            PreparedStatement ps = db.con.prepareStatement(query);
            int i = ps.executeUpdate();
            if (i > 0) {
                status = "success";
                sessionmap.put("topicdeleted", "yes");
            } else {
                status = "error";
            }
        } catch (SQLException e) {
            System.out.println(e);
            e.printStackTrace();
        }

        return status;
    }

    private InputStream fileInputStream;

    public InputStream getFileInputStream() {
        return fileInputStream;
    }

    public void setFileInputStream(InputStream fileInputStream) {
        this.fileInputStream = fileInputStream;
    }
    
    public String editshow() {
        
        String status = "error";
        //String downstr;
        db.connect();
        int topicid;
        if (getServletRequest().getParameter("id") != null) {
            topicid = Integer.parseInt(String.valueOf(getServletRequest().getParameter("id")));
            sessionmap.put("topicid", topicid);
        }
        topicid = Integer.parseInt(String.valueOf(sessionmap.get("topicid")));

        String query = "select * from topic where topic_id='" + topicid + "'";
        try {
            PreparedStatement ps_topic = db.con.prepareStatement(query);
            ResultSet rs = ps_topic.executeQuery();
            while (rs.next()) {

                ManageTopic ad = new ManageTopic();

                ad.setTopicid(rs.getInt(1));
                ad.setTopicname(rs.getString(2));
                ad.setCreationdate(String.valueOf(rs.getDate(3)));
                
                
                ad.setTopicdescription(rs.getString(4));
              //  fileInputStream = new FileInputStream(new File());
              

                topicdetail.add(ad);

            }
        } catch (SQLException e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return "success";
    }

    public String editsave() {
        String status = "error";
        db.connect();
        int topicid = Integer.parseInt(String.valueOf(sessionmap.get("topicid")));

        String query = "update topic set topic_name='" + topicname + "',topic_description='" + topicdescription + "' where topic_id=" + topicid + "";

        try {
            PreparedStatement ps_topic = db.con.prepareStatement(query);
            int i = ps_topic.executeUpdate();
            if (i > 0) {
                sessionmap.put("topicedited", "yes");
                if (sessionmap.get("checkfromModify") != null) {
                    status = "checkfromModify";
                    sessionmap.put("topicedited", "yes");
                } else {
                    status = "success";
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

    public String addquiz() {
        db.connect();
        String result = null;
        int status = 0;
        int topicid;
        if (request.getSession().getAttribute("topicidq") != null) {
            topicid = Integer.parseInt(String.valueOf(request.getSession().getAttribute("topicidq")));
        } else {
            topicid = Integer.parseInt(String.valueOf(sessionmap.get("topicid")));
        }
    // Quiz quiz=new Quiz();

        try {

            String query = "insert into quiz values('',?,sysdate) returning quiz_id into ?";
            OraclePreparedStatement ps;

            ps = (OraclePreparedStatement) db.con.prepareStatement(query);

            ps.setString(1, quizname);
            // ps.setString(2,duration);
            ps.registerReturnParameter(2, Types.INTEGER);

            status = ps.executeUpdate();

            ResultSet rs = ps.getReturnResultSet();
            rs.next();

            sessionmap.put("quizid", rs.getInt(1));
            int quizid, courseid;
            quizid = rs.getInt(1);
            if (sessionmap.get("courseid") != null) {
                courseid = Integer.parseInt(String.valueOf(sessionmap.get("courseid")));
            }

            String queryct = "update course_topic set quiz_id =" + quizid + " where topic_id=" + topicid + "";
            PreparedStatement ps_ct = db.con.prepareStatement(queryct);

            int j = ps_ct.executeUpdate();

            if (status > 0 && j > 0) {
                result = "success";
                sessionmap.put("quizadded", "yes");
            } else {
                result = "error";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public void setSession(Map map) {
        sessionmap = (SessionMap) map;
        //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String addque() {
        String result = null;
        int quizid = 0;
        if (sessionmap.get("quizid") != null) {
            quizid = Integer.parseInt(String.valueOf(sessionmap.get("quizid")));
        }
        if (sessionmap.get("quizid_session") != null) {
            quizid = Integer.parseInt(String.valueOf(sessionmap.get("quizid_session")));
        }
        try {
            db.connect();

            String query = "insert into question values('',?,?,?,?,?,?,?,?,1)";
            PreparedStatement ps = db.con.prepareStatement(query);
            ps.setString(1, getQuestionname());
            ps.setInt(2, quizid);
            ps.setString(3, getOptionA());
            ps.setString(4, getOptionB());
            ps.setString(5, getOptionC());
            ps.setString(6, getOptionD());
            ps.setString(7, getAnswer());
            ps.setInt(8, getMarks());
            int i = ps.executeUpdate();
            if (i > 0) {
                result = "success";
                sessionmap.put("queadded", "yes");
            } else {
                result = "error";
            }
        } catch (SQLException e) {
            // System.out.println(e);
            e.printStackTrace();
        }
        return result;
    }

    public String addAssign() {
        int status = 0;
        String result = null;
        db.connect();
        int topicid = Integer.parseInt(String.valueOf(sessionmap.get("topicid")));
        try {

            String query = "insert into assignment values('',?,sysdate,?) returning assignment_id into ?";
            OraclePreparedStatement ps;

            ps = (OraclePreparedStatement) db.con.prepareStatement(query);
            System.out.println(assignementname);
            System.out.println(title);
            ps.setString(1, assignementname);
            //ps.setString(2,duration);
            ps.setString(2, title);
            ps.registerReturnParameter(3, Types.INTEGER);

            status = ps.executeUpdate();

            ResultSet rs = ps.getReturnResultSet();
            rs.next();

            int assignid, courseid;
            assignid = rs.getInt(1);
              // courseid=Integer.parseInt(String.valueOf(sessionmap.get("courseid")));

            String queryct = "update course_topic set assignment_id =" + assignid + " where topic_id=" + topicid + "";
            PreparedStatement ps_ct = db.con.prepareStatement(queryct);

            int j = ps_ct.executeUpdate();

            if (status > 0 && j > 0) {
                result = "success";
                sessionmap.put("assignmentadded", "yes");
            } else {
                result = "error";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;

    }

    //Add Topic has been Added///
    private String creationdate, assignmentsubmitted, quizsubmitted, quizmarks, verified, remarks, topiccompleted, quizsubdate, assignsubdate, assignmentContent;

    public String getAssignmentContent() {
        return assignmentContent;
    }

    public void setAssignmentContent(String assignmentContent) {
        this.assignmentContent = assignmentContent;
    }

    public String getAssignmentsubmitted() {
        return assignmentsubmitted;
    }

    public void setAssignmentsubmitted(String assignmentsubmitted) {
        this.assignmentsubmitted = assignmentsubmitted;
    }

    public String getQuizsubmitted() {
        return quizsubmitted;
    }

    public void setQuizsubmitted(String quizsubmitted) {
        this.quizsubmitted = quizsubmitted;
    }

    public String getQuizmarks() {
        return quizmarks;
    }

    public void setQuizmarks(String quizmarks) {
        this.quizmarks = quizmarks;
    }

    public String getVerified() {
        return verified;
    }

    public void setVerified(String verified) {
        this.verified = verified;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getTopiccompleted() {
        return topiccompleted;
    }

    public void setTopiccompleted(String topiccompleted) {
        this.topiccompleted = topiccompleted;
    }

    public String getQuizsubdate() {
        return quizsubdate;
    }

    public void setQuizsubdate(String quizsubdate) {
        this.quizsubdate = quizsubdate;
    }

    public String getAssignsubdate() {
        return assignsubdate;
    }

    public void setAssignsubdate(String assignsubdate) {
        this.assignsubdate = assignsubdate;
    }
    int topicid;

    public int getTopicid() {
        return topicid;
    }

    public void setTopicid(int topicid) {
        this.topicid = topicid;
    }

    public String getCreationdate() {
        return creationdate;
    }

    public void setCreationdate(String creationdate) {
        this.creationdate = creationdate;
    }

    public String addTopic() {
        int i = save(this);
        if (i > 0) {

            return "success";
        }
        return "error";
    }

    public int save(ManageTopic topic) {
        int status = 0, i = 0, j = 0;
        try {
            DBConnect db = new DBConnect();
            db.connect();
            String query = "insert into topic values('',?,sysdate,?) returning topic_id into ?";
            //System.out.println(cid);

            OraclePreparedStatement ps;

            ps = (OraclePreparedStatement) db.con.prepareStatement(query);
            ps.setString(1, topic.getTopicname());
            ps.setString(2, topic.getTopicdescription());
            ps.registerReturnParameter(3, Types.INTEGER);
            i = ps.executeUpdate();

            ResultSet rs = ps.getReturnResultSet();
            rs.next();

            String idtopic = String.valueOf(rs.getInt(1));
            String courseid = String.valueOf(sessionmap.get("courseidselect"));

            String queryct = "insert into course_topic(course_topic_id,course_id,topic_id) values('',?,?)";
            PreparedStatement ps_ct = db.con.prepareStatement(queryct);
            ps_ct.setInt(1, Integer.valueOf(courseid));
            ps_ct.setInt(2, Integer.valueOf(idtopic));
            j = ps_ct.executeUpdate();

            /*String query="insert into topic values('',?,sysdate,?)returning topic_id into ?";
             String query_ct="insert into course_topic(course_topic_id,course_id,topic_id) values('',?,?)";
             PreparedStatement ps=db.con.prepareStatement(query);
             PreparedStatement ps_ct=db.con.prepareStatement(query_ct);
             ps.setString(1,r.getTopicname())
             ps.setString(2,r.getTopicdescription());
             ps_ct.setString(1, query);
             status=ps.executeUpdate(); 
             */
            if (i == 1 && j == 1) {
                status = 1;
                sessionmap.put("topicadded", "yes");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    //TopicList is here//
    String courseid;

    public String getCourseid() {
        return courseid;
    }

    public void setCourseid(String courseid) {
        this.courseid = courseid;
    }

    public HttpServletRequest getServeletRequest() {
        return request;
    }

    private ArrayList<ManageTopic> topiclist = new ArrayList<ManageTopic>();

    public ArrayList<ManageTopic> getTopiclist() {
        return topiclist;
    }

    public void setTopiclist(ArrayList<ManageTopic> topiclist) {
        this.topiclist = topiclist;
    }

    public String topicList() {
        String result = null;
        Statement stmt = null;
        try {
            DBConnect db = new DBConnect();
            db.connect();
            if (sessionmap.get("courseid") != null) {
                courseid = String.valueOf(sessionmap.get("courseid"));
                sessionmap.put("courseidselect", courseid);
            } else if (getServeletRequest().getParameter("id") != null) {
                courseid = String.valueOf(getServeletRequest().getParameter("id"));
                sessionmap.put("courseidselect", courseid);
            } else if (courseid != null) {
                sessionmap.put("courseidselect", courseid);
            }
            courseid = String.valueOf(sessionmap.get("courseidselect"));
            System.out.print("hiu");
            System.out.println(sessionmap.get("courseidselect"));
            System.out.println(courseid);
            System.out.println("below csid");
            int csid = Integer.parseInt(courseid);

            String query = "select * from topic inner join course_topic on topic.topic_id=course_topic.topic_id where course_topic.course_id=" + csid + "";
            PreparedStatement ps_course = db.con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            ResultSet rs = ps_course.executeQuery();
            if (rs.next()) {
                rs.previous();

                while (rs.next()) {
                    System.out.println(rs.getInt("topic_id"));
                    System.out.println(rs.getString(2));
                    System.out.println("above while");
                    ManageTopic ad = new ManageTopic();

                    ad.setTopicid(rs.getInt(1));
                    ad.setTopicname(rs.getString(2));
                    ad.setCreationdate(String.valueOf(rs.getDate(3)));
                    ad.setTopicdescription(rs.getString(4));

                    topiclist.add(ad);
                    result = "success";

                }
            } else {
                sessionmap.put("notopic", "yes");
                result = "norecord";
            }
        } catch (SQLException e) {
            result = "error";
            e.printStackTrace();
        }

        return result;
    }

    public String ChangeStatusAssign() {
        db.connect();
        int assignid=0,quizid=0,topicidc=0,courseidc=0;
        String studid=null,comptime=null;
        String status = null;
        int studassign = Integer.parseInt(sessionmap.get("studsession").toString());
        System.out.println("printinf for testing");
        System.out.println(verified + "" + remarks);
        System.out.println(studassign);
       
       
        String assign = "update student_assignment set verified='" + verified + "',remarks='" + remarks + "' where student_assignment_id=" + studassign + "";
        String assign_stud = "select * from student_assignment where student_assignment_id=" + studassign + "";
        
        System.out.println(assign);
        try {
            PreparedStatement ps_ct = db.con.prepareStatement(assign);
            PreparedStatement ps_as = db.con.prepareStatement(assign_stud);
            ResultSet rs = ps_as.executeQuery();
            
            if (rs.next()) {
                assignid=rs.getInt("assignment_id");
                studid=rs.getString("student_id");
                String stud_c = "insert into notification values('','" + rs.getString("student_id") + "','your assignment is reviewed',CURRENT_TIMESTAMP,'student'," + rs.getInt("assignment_id") + ")";
                PreparedStatement ps_notify = db.con.prepareStatement(stud_c);
                int k = ps_notify.executeUpdate();
                System.out.println(k);

            }
            //* for topic completion * /
            String check_quizid="select * from course_topic where assignment_id="+assignid+"";
           
             PreparedStatement ps_qu = db.con.prepareStatement(check_quizid);
            ResultSet rsqu = ps_qu.executeQuery();
            if(rsqu.next())
            {
                topicidc=rsqu.getInt("topic_id");
                quizid=rsqu.getInt("quiz_id");
                courseidc=rsqu.getInt("course_id");
            }
            String check_quiz="select * from student_quiz where student_id='"+studid+"' and quiz_id="+quizid+"";
            PreparedStatement ps_quiz = db.con.prepareStatement(check_quiz);
            ResultSet rsquiz = ps_quiz.executeQuery();
            if(rsquiz.next())
            {
                comptime=String.valueOf(rsquiz.getDate("quiz_sub_date"));
                System.out.print("printing comtime"+comptime);
            }
            if(verified.equals("verified")&& comptime!=null)
            {
                String ins_topic="insert into student_topic values('','"+studid+"',"+topicidc+",sysdate)";
                  PreparedStatement ps_topic = db.con.prepareStatement(ins_topic);
                  int i = ps_topic.executeUpdate();
                  // topic comparison//
                String query_topic ="select count(topic_id) from (select * from course_topic where course_id="+courseidc+")";
                String query_comp="select count(topic_completion_time) from student_topic where topic_id IN(select topic_id from (select * from course_topic where course_id="+courseidc+")) and student_id='"+studid+"'";
                  PreparedStatement ps_ch = db.con.prepareStatement(query_topic);
                  PreparedStatement ps_tc = db.con.prepareStatement(query_comp);
                  ResultSet rs_topicq = ps_ch.executeQuery();
                  ResultSet rs_tc = ps_tc.executeQuery();
                  rs_topicq.next();
                  rs_tc.next();
                  if(rs_topicq.getInt("count(topic_id)")==rs_tc.getInt("count(topic_completion_time)"))
                  {
                      String max_date="select max(topic_completion_time) from student_topic where topic_id IN(select topic_id from (select * from course_topic where course_id="+courseidc+")) and student_id='"+studid+"'";
                      PreparedStatement ps_max = db.con.prepareStatement(max_date);
                  ResultSet rs_max = ps_max.executeQuery();
                  rs_max.next();
                  
                    String up_course="update student_course where student_id='"+studid+"' and course_id="+courseidc+" set course_completion_time="+rs_max.getDate("topic_completion_time")+"";
                    PreparedStatement ps_up = db.con.prepareStatement(up_course);
                  int k = ps_up.executeUpdate();
                  }
                                
            }
            //*for topic completion end */
            int i = ps_ct.executeUpdate();
            if (i > 0) {
                status = "success";
                sessionmap.put("changed", "yes");
            } else {
                status = "error";
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return status;

    }
    ArrayList<TopicContentBean> tcblst = new ArrayList<TopicContentBean>();

    public ArrayList<TopicContentBean> getTcblst() {
        return tcblst;
    }

    public void setTcblst(ArrayList<TopicContentBean> tcblst) {
        this.tcblst = tcblst;
    }

    public String viewTopicContent() {
        String status = null;
        String sttopic = "select * from topic_content where topic_id=?";
        int topicIdSession;
        if (getServletRequest().getParameter("id") != null) {
            topicIdSession = Integer.parseInt(String.valueOf(getServletRequest().getParameter("id")));
            sessionmap.put("topicidsess", topicIdSession);
        }
        topicIdSession = Integer.parseInt(String.valueOf(sessionmap.get("topicidsess")));
        System.out.println("topic id to view" + topicIdSession);
        DBConnect db = new DBConnect();
        db.connect();
        try {

            PreparedStatement ps = db.con.prepareStatement(sttopic);
            ps.setInt(1, topicIdSession);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                TopicContentBean tcb = new TopicContentBean();
                tcb.setTopicId(rs.getInt("topic_id"));
                tcb.setAudioContent(rs.getString("audio_content"));
                tcb.setVideoContent(rs.getString("video_content"));
                tcb.setDocContent(rs.getString("doc_content"));
                tcblst.add(tcb);
                System.out.println("list values" + tcblst.size());

            }
            status= "success";

            //   }
        } catch (SQLException ex) {
            Logger.getLogger(ManageTopic.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status;
    }

}
