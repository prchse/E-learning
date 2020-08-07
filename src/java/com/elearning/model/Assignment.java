
package com.elearning.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

public class Assignment implements SessionAware,ServletRequestAware {
    private String assignmentname,duration,title,assignmentcreationdate,assignment_content,assign_sub_date,verified,remarks;

    public String getAssign_sub_date() {
        return assign_sub_date;
    }

    public void setAssign_sub_date(String assign_sub_date) {
        this.assign_sub_date = assign_sub_date;
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

    public String getAssignment_content() {
        return assignment_content;
    }

    public void setAssignment_content(String assignment_content) {
        this.assignment_content = assignment_content;
    }
    int courseid,topicid,assignmentid;
     private ArrayList<Assignment> list_assign = new ArrayList<Assignment>();

    public ArrayList<Assignment> getList_assign() {
        return list_assign;
    }

    public void setList_assign(ArrayList<Assignment> list_assign) {
        this.list_assign = list_assign;
    }
    

    public int getAssignmentid() {
        return assignmentid;
    }

    public void setAssignmentid(int assignmentid) {
        this.assignmentid = assignmentid;
    }
      HttpServletRequest request;
      SessionMap<String, Object> sessionmap;
      public HttpServletRequest getServeletRequest() {
        return request;
    }

    @Override
     public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }

    public String getAssignmentcreationdate() {
        return assignmentcreationdate;
    }

    public void setAssignmentcreationdate(String assignmentcreationdate) {
        this.assignmentcreationdate = assignmentcreationdate;
    }

    

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAssignmentname() {
        return assignmentname;
    }

    public void setAssignmentname(String assignmentname) {
        this.assignmentname = assignmentname;
    }

   

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
    
    @Override
    public void setSession(Map map) {
        sessionmap = (SessionMap) map;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   public String deleteConfirm()
   {
       DBConnect db=new DBConnect();
       db.connect();
       String result=null;
       courseid=Integer.parseInt(String.valueOf(sessionmap.get("courseidselect")));
        System.out.println("print topicid");
        topicid=Integer.parseInt(String.valueOf(sessionmap.get("topicidquery")));
        String query="select * from course_topic where course_id="+courseid+" and topic_id="+topicid+"";
        try{
            PreparedStatement ps = db.con.prepareStatement(query);
           ResultSet rs1= ps.executeQuery();
           rs1.next();
           if(rs1.getInt("assignment_id")>0)
           {  
                assignmentid=rs1.getInt("assignment_id");
                sessionmap.put("assignmentid_session",assignmentid);
               System.out.println("print assignment id");
            
             String queryview="select * from assignment where assignment_id="+assignmentid+"";
           PreparedStatement ps_assign = db.con.prepareStatement(queryview);
           ResultSet rs_assign= ps_assign.executeQuery();
           if(rs_assign.next())
           {
              
               setTitle(rs_assign.getString("title"));
            setAssignmentname(rs_assign.getString("assignment_name"));
               setAssignmentcreationdate(String.valueOf(rs_assign.getDate("assignment_creation_date")));
              
           }
           result="success";
   }
        
        else{
                 sessionmap.put("checkassign","noassign");
               result="noassign";
                }
        }
         catch(Exception e)
   {
               System.out.println(e);
                e.printStackTrace();
                result="error";
    } 
        return result;
}
   
   public String delete()
   {
        DBConnect db=new DBConnect();
       db.connect();
       String result=null;
       courseid=Integer.parseInt(String.valueOf(sessionmap.get("courseidselect")));
        System.out.println("print topicid");
        topicid=Integer.parseInt(String.valueOf(sessionmap.get("topicidquery")));
        assignmentid=Integer.parseInt(String.valueOf(sessionmap.get("assignmentid_session")));
         String delete_query="delete from assignment where assignment_id="+assignmentid+"";
        try{
          
           
           
           String delq="update course_topic set assignment_id="+null+" where course_id="+courseid+" and topic_id="+topicid+"";
            PreparedStatement ps_cq = db.con.prepareStatement(delq);
           int j= ps_cq.executeUpdate();
           
            String dela="update student_assignment set assignment_id="+null+",assignment_submission_content="+null+",assignment_sub_date="+null+",verified="+null+",remarks="+null+" where assignment_id="+assignmentid+"";
            PreparedStatement ps_sq = db.con.prepareStatement(dela);
           int k= ps_sq.executeUpdate();
           
               PreparedStatement ps = db.con.prepareStatement(delete_query);
           int i= ps.executeUpdate();
            System.out.println(i+"jvale"+j+"kvalue"+k);
            if(i>0 && k>0)
            {    result="success";
                sessionmap.put("assigndeleted","yes");
            }
        
            else
            {
                result="error";
            }
    }
         catch(Exception e)
   {
               System.out.println(e);
                e.printStackTrace();
    } 
        return result;
   }
   public String edit()
   {
        DBConnect db=new DBConnect();
       db.connect();
       String result=null;
       courseid=Integer.parseInt(String.valueOf(sessionmap.get("courseidselect")));
        System.out.println("print topicid");
        topicid=Integer.parseInt(String.valueOf(sessionmap.get("topicidquery")));
         assignmentid=Integer.parseInt(String.valueOf(sessionmap.get("assignmentid_session")));
         String delete_query="update assignment set assignment_name='"+assignmentname+"',title='"+title+"' where assignment_id="+assignmentid+"";
        try{
        PreparedStatement ps = db.con.prepareStatement(delete_query);
           int i= ps.executeUpdate();
           if(i>0)
           {
               result="success";
               sessionmap.put("assignedited","yes");
           }
          }
         catch(Exception e)
        {
            result="error";
               System.out.println(e);
                e.printStackTrace();
          } 
        return result;
}  
   public String check(){
       DBConnect db=new DBConnect();
       db.connect();
       String result=null;
       courseid=Integer.parseInt(String.valueOf(sessionmap.get("courseidselect")));
        System.out.println("print topicid");
        topicid=Integer.parseInt(String.valueOf(sessionmap.get("topicidquery")));
        String query="select * from course_topic where course_id="+courseid+" and topic_id="+topicid+"";
        try{
            PreparedStatement ps = db.con.prepareStatement(query);
           ResultSet rs1= ps.executeQuery();
           rs1.next();
           if(rs1.getInt("assignment_id")>0)
           {  
                
           result="assign";
           sessionmap.put("checkassign","yes");
           }
        
        else{
                 
               result="success";
                }
        }
         catch(Exception e)
   {
               System.out.println(e);
                e.printStackTrace();
                result="error";
    } 
        return result;
   }
   ///AssignmentList has been added here//
   

    


   
    
  
     private ArrayList<Assignment> Assignmentlist = new ArrayList<Assignment>();

    public ArrayList<Assignment> getAssignmentlist() {
        return Assignmentlist;
    }

    public void setAssignmentlist(ArrayList<Assignment> Assignmentlist) {
        this.Assignmentlist = Assignmentlist;
    }

    public SessionMap<String, Object> getSessionmap() {
        return sessionmap;
    }

    public void setSessionmap(SessionMap<String, Object> sessionmap) {
        this.sessionmap = sessionmap;
    }
   
     public String assignList() {
        Statement stmt = null;
        String result=null;
        try {
            DBConnect db = new DBConnect();
            db.connect();
            if(sessionmap.get("assignmentid")!=null)
                    {
                         topicid=Integer.parseInt(String.valueOf(sessionmap.get("topicid")));
                    }
            if(getServeletRequest().getParameter("id")!=null)
            {
                
            topicid=Integer.parseInt(String.valueOf(getServeletRequest().getParameter("id")));
            }
            //String query="select * from topic inner join course_topic on topic.topic_id=course_topic.topic_id where course_topic.course_id="+courseid+"";
            String query="select * from assignment inner join course_topic on assignment.assignment_id=course_topic.assignment_id where course_topic.topic_id="+topicid+"";
           PreparedStatement ps_course=db.con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
         
          ResultSet rs=ps_course.executeQuery();
             //System.out.println("above while");*/
          if(rs.next())
          {
              rs.previous();
          
            while (rs.next()) {
                System.out.println(rs.getInt("assignment_id"));
                System.out.println(rs.getString(2));
                System.out.println("above while");
                Assignment ad=new Assignment();
                ad.setAssignmentid(rs.getInt(1));
                //ad.setTopicid(1);
                ad.setAssignmentname(rs.getString(2));
                //ad.setTopicname("hello");
                //ad.setCreationdate("sdsd");
               // ad.setTopicdescription("ssds");
                ad.setAssignmentcreationdate(String.valueOf(rs.getDate(3)));
                ad.setDuration(rs.getString(4));
                ad.setTitle(rs.getString(5));
                result="success";
                
               
                Assignmentlist.add(ad);

            }
          }
          else{
              result="norecord";
            sessionmap.put("noassignment","yes");
          }
        } catch (SQLException e) 
        {
            result="error";
            e.printStackTrace();
        }

        return result;

    }
     ///CheckAssignment class has been added///
     
    String result;
    public int getTopicid() {
        return topicid;
    }

    public void setTopicid(int topicid) {
        this.topicid = topicid;
    }
     public HttpServletRequest getServletRequest() {
        return request;
    }

   
    
public String checkAssign()
    {
        try
        {
        DBConnect db = new DBConnect();
            db.connect();
        if(getServletRequest().getParameter("id")!=null)
            {  topicid=Integer.parseInt(String.valueOf(getServletRequest().getParameter("id"))); 
               sessionmap.put("topicid",topicid);
            }
         topicid=Integer.parseInt(String.valueOf(sessionmap.get("topicid")));
            System.out.println(topicid);
             System.out.println("hi hello");
         String q1="select * from course_topic where topic_id="+topicid+"";
            PreparedStatement ps_check=db.con.prepareStatement(q1);
            ResultSet rs_check=ps_check.executeQuery();
           
          while(rs_check.next())
              {
                  System.out.println("in if");
                  if(rs_check.getInt("assignment_id")>0)
                  {
                      System.out.println(rs_check.getInt("assignment_id"));
                      System.out.println("in if 2");
                  
                   sessionmap.put("checkAssignment","abc");
                   result="assignexist";
                
                  }
                  else{
                    result="success";
                  }
                  
              }
               
              
          
    }
    
    catch(Exception e)
    {e.printStackTrace();}
        return result;
    }
  public String previousAttempt()
          {
              String stud_id,topic_id,result="error";
              try{
              DBConnect db= new DBConnect();
              db.connect();
               if(getServletRequest().getParameter("id")!=null)
            {  topicid=Integer.parseInt(String.valueOf(getServletRequest().getParameter("id"))); 
               sessionmap.put("topicid",topicid);
            }
         topicid=Integer.parseInt(String.valueOf(sessionmap.get("topic_id_sess")));
            System.out.println(topicid);
            stud_id=String.valueOf(sessionmap.get("login"));
            String assign="select * from course_topic where topic_id="+topicid+"";
              PreparedStatement ps_check=db.con.prepareStatement(assign);
            ResultSet rs_check=ps_check.executeQuery();
            System.out.println("below rs_check");
            rs_check.next();
            String list="select * from student_assignment where assignment_id="+rs_check.getInt("assignment_id")+" and student_id='"+stud_id+"'";
            PreparedStatement ps_assign=db.con.prepareStatement(list);
            
            ResultSet rs_assign=ps_assign.executeQuery();
              System.out.println("below rs_assign");
                  System.out.println(rs_check.getInt("assignment_id"));
                   System.out.println(stud_id);
                  
            while(rs_assign.next())
            {
                Assignment assi=new Assignment();
                assi.setAssignment_content(rs_assign.getString("assignment_submission_content"));
                assi.setAssign_sub_date(String.valueOf(rs_assign.getDate("assignment_sub_date")));
                assi.setVerified(rs_assign.getString("verified"));
                if(rs_assign.getString("remarks")!=null)
                assi.setRemarks(rs_assign.getString("remarks"));
                else
                    assi.setRemarks("N/A");
                list_assign.add(assi);
                System.out.println("beow in while loop");
                result="success";
            }
            
           
            
              
              
              
              }
               catch(Exception e)
    {e.printStackTrace();}
              
      return result;
  }

}
