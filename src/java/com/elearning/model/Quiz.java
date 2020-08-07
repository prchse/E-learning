
package com.elearning.model;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

public class Quiz  implements SessionAware,ServletRequestAware {
    private String quizname,quizcreationdate,duration;
    int courseid,topicid,quizid;
    HttpServletRequest request;

    public int getQuizid() {
        return quizid;
    }

    public void setQuizid(int quizid) {
        this.quizid = quizid;
    }
    DBConnect db=new DBConnect();
     private ArrayList<Quiz> Quizlist = new ArrayList<Quiz>();

    public ArrayList<Quiz> getQuizlist() {
        return Quizlist;
    }

    public void setQuizlist(ArrayList<Quiz> Quizlist) {
        this.Quizlist = Quizlist;
    }

    
 SessionMap<String, Object> sessionmap;
    public String getQuizname() {
        return quizname;
    }

    public void setQuizname(String quizname) {
        this.quizname = quizname;
    }

    public String getQuizcreationdate() {
        return quizcreationdate;
    }

    public void setQuizcreationdate(String quizcreationdate) {
        this.quizcreationdate = quizcreationdate;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
    public String deleteConfirm()
    {
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
           if(rs1.getInt("quiz_id")>0)
           {  
                quizid=rs1.getInt("quiz_id");
                sessionmap.put("quizid_session",quizid);
               System.out.println("print quiz id");
               System.out.println(quizid);
           
           String queryview="select * from quiz where quiz_id="+quizid+"";
           PreparedStatement ps_quiz = db.con.prepareStatement(queryview);
           ResultSet rs_quiz= ps_quiz.executeQuery();
           if(rs_quiz.next())
           {
            setQuizname(rs_quiz.getString("quiz_name"));
               setQuizcreationdate(String.valueOf(rs_quiz.getDate("quiz_creation_date")));
              
           }
           
           String queryquestion="select * from question where quiz_id="+quizid+"";
           PreparedStatement ps_que = db.con.prepareStatement(queryquestion, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
           ResultSet rs_que= ps_que.executeQuery();
           if(rs_que.next())
           {
                System.out.println("print question1");
               
               rs_que.previous();
           while(rs_que.next())
           {
                Quiz ad=new Quiz();
                ad.setQuestionid(rs_que.getInt(1));
                ad.setQuestionname(rs_que.getString(2));
                ad.setOptionA(rs_que.getString(3));
                ad.setOptionB(rs_que.getString(4));
                ad.setOptionC(rs_que.getString(5));
                ad.setOptionD(rs_que.getString(6));
                ad.setAnswer(rs_que.getString(7));
                System.out.println("print result set");
                System.out.println(rs_que.getString(2));
                System.out.println("print object");
                System.out.println(ad.getQuestionname());
                
                Quizlist.add(ad);
                 //result="success";
           }
           }
           else
           {
                  System.out.println("print question2");
               Quiz ad=new Quiz();
              // ad.setQuizid("n/a");
                ad.setQuestionname("N/A");
                ad.setOptionA("N/A");
                ad.setOptionB("N/A");
                ad.setOptionC("N/A");
                ad.setOptionD("N/A");
                ad.setAnswer("N/A");
                Quizlist.add(ad);
               
           }
           result="success";
           }
           else{
               sessionmap.put("checkquiz","noquiz");
               result="noquiz";
           }
           
           
            
        }
         catch(Exception e)
   {
               System.out.println(e);
                e.printStackTrace();
    } 
        return result;
    }
    public String delete() //Delete Quiz
    {
        db.connect();
        String result=null;
         courseid=Integer.parseInt(String.valueOf(sessionmap.get("courseidselect")));
        System.out.println("print topicid");
        topicid=Integer.parseInt(String.valueOf(sessionmap.get("topicidquery")));
        quizid=Integer.parseInt(String.valueOf(sessionmap.get("quizid_session")));
        String delete_query="delete from quiz where quiz_id="+quizid+"";
        try{
          
           
           
           String delq="update course_topic set quiz_id="+null+" where course_id="+courseid+" and topic_id="+topicid+"";
            PreparedStatement ps_cq = db.con.prepareStatement(delq);
           int j= ps_cq.executeUpdate();
           
            String dela="update student_assignment set quiz_id="+null+",quiz_marks="+null+",quiz_sub_date="+null+" where quiz_id="+quizid+"";
            PreparedStatement ps_sq = db.con.prepareStatement(dela);
           int k= ps_sq.executeUpdate();
           
               PreparedStatement ps = db.con.prepareStatement(delete_query);
           int i= ps.executeUpdate();
            System.out.println(i+"jvale"+j+"kvalue"+k);
            if(i>0 && k>0)
            {    result="success";
            sessionmap.put("quizdeleted","yes");
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
    public String editQuestion()
    {
        db.connect();
        String result=null;
        // courseid=Integer.parseInt(String.valueOf(sessionmap.get("courseidselect")));
        System.out.println("print question id");
      //  topicid=Integer.parseInt(String.valueOf(sessionmap.get("topicidquery")));
        //quizid=Integer.parseInt(String.valueOf(sessionmap.get("quizid_session")));
        int questionid= Integer.valueOf(String.valueOf(request.getParameter("id")));
        System.out.println(questionid);
        sessionmap.put("questionid_session",questionid);
        String que="select * from question where question_id="+questionid+"";
        try{
         PreparedStatement ps_cq = db.con.prepareStatement(que);
           ResultSet rs= ps_cq.executeQuery();
           if(rs.next())
           {
               Quiz ad=new Quiz();
                ad.setQuestionid(rs.getInt(1));
                ad.setQuestionname(rs.getString(2));
                ad.setOptionA(rs.getString(3));
                ad.setOptionB(rs.getString(4));
                ad.setOptionC(rs.getString(5));
                ad.setOptionD(rs.getString(6));
                ad.setAnswer(rs.getString(7));
                ad.setMarks(rs.getInt(8));
                 System.out.println("print result set");
                System.out.println(rs.getString(2));
                System.out.println("print object");
                System.out.println(ad.getQuestionname());
                  Quizlist.add(ad);
               result= "success";
           }
           else
           {
               result ="error";
           }
        }
        catch(Exception e)
        {
            System.out.println(e);
                e.printStackTrace();
        }
        
        return result;
    }
    public String editQuiz()
    {
        db.connect();
     String result=null;
        int quizid;
        courseid=Integer.parseInt(String.valueOf(sessionmap.get("courseidselect")));
        System.out.println("print topicid");
        topicid=Integer.parseInt(String.valueOf(sessionmap.get("topicidquery")));
        String query="select * from course_topic where course_id="+courseid+" and topic_id="+topicid+"";
        try{
             PreparedStatement ps = db.con.prepareStatement(query);
           ResultSet rs1= ps.executeQuery();
           rs1.next();
           if(rs1.getInt("quiz_id")>0)
           {  
                quizid=rs1.getInt("quiz_id");
                //System.out.println(quizid);
                String up="update quiz set quiz_name='"+quizname+"' where quiz_id="+quizid+"";
                 PreparedStatement ps_up = db.con.prepareStatement(up);
                 int j=ps_up.executeUpdate();
                
                result="success";
               System.out.println("print quiz id");
               System.out.println(quizid);
               sessionmap.put("quizedited","yes");
           }
           else{
               sessionmap.put("checkquiz","noquiz");
               result="noquiz";
           }
        }
           catch(Exception e)
        {
            System.out.println(e);
                e.printStackTrace();
                
        }
        return result;
    }
    public String execute() {
        int i = QuizInsertDao.save(this);
        if (i > 0) {

            return "success";
        }
        return "error";
    }
    
    @Override
    public void setSession(Map map) {
        sessionmap = (SessionMap) map;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public HttpServletRequest getServletRequest() {
        return request;
    }
    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request=request;
    }
    //Check Quiz is added here//
    
     String result;

    public int getTopicid() {
        return topicid;
    }

    public void setTopicid(int topicid) {
        this.topicid = topicid;
    }
    

    public SessionMap<String, Object> getSessionmap() {
        return sessionmap;
    }

    public void setSessionmap(SessionMap<String, Object> sessionmap) {
        this.sessionmap = sessionmap;
    }
    public String checkQuiz()
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
                  if(rs_check.getInt("quiz_id")>0)
                  {
                      System.out.println(rs_check.getInt("quiz_id"));
                      System.out.println("in if 2");
                  
                   sessionmap.put("checkQuiz","abc");
                   result="quizexist";
                
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
    //Question has been added here///
      private String questionname,optionA,optionB,optionC,optionD,answer;
private int questionid,marks;
 

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    public int getQuestionid() {
        return questionid;
    }

    public void setQuestionid(int questionid) {
        this.questionid = questionid;
    }
   

    public String getQuestionname() {
        return questionname;
    }

    public void setQuestionname(String questionname) {
        this.questionname = questionname;
    }

    public String getOptionA() {
        return optionA;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }

    public String getOptionD() {
        return optionD;
    }

    public void setOptionD(String optionD) {
        this.optionD = optionD;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    
   public String editQuestionSave(){
       String result=null;
       DBConnect db=new DBConnect();
       db.connect();
        int qid=Integer.parseInt(String.valueOf(sessionmap.get("questionid_session")));
        
        String que="update question set question_name='"+questionname+"',optiona='"+optionA+"',optionb='"+optionB+"',optionc='"+optionC+"',optiond='"+optionD+"',answer='"+answer+"' where question_id="+qid+"";
        System.out.println(que);
         try{
         PreparedStatement ps_cq = db.con.prepareStatement(que);
           int i= ps_cq.executeUpdate();
           if(i>0)
           {sessionmap.put("check_status","success");
            result= "success";
           
           }
              
          
           
           else
           {
               result ="error";
           }
         }
   
        
        catch(Exception e)
        {
            System.out.println(e);
                e.printStackTrace();
        }
        
       
        return result;
        
    }
   public String deleteQuestion()
   {
       
        DBConnect db=new DBConnect();
        db.connect();
        String result=null;
        int qid=0;
        if(sessionmap.get("questionid_session")!=null)
       qid=Integer.parseInt(String.valueOf(sessionmap.get("questionid_session")));
        if(request.getParameter("id")!=null)
        {
           qid= Integer.valueOf(String.valueOf(request.getParameter("id")));
        }
        
       String que="delete from question where question_id="+qid+"";
        System.out.println(que);
         try{
         PreparedStatement ps_cq = db.con.prepareStatement(que);
           int i= ps_cq.executeUpdate();
           if(i>0)
           {
               sessionmap.put("check_status_del","success");
            result= "success";
           
           }
         }
           catch(Exception e)
        {
            System.out.println(e);
                e.printStackTrace();
        }
         return result;
   }
    
    //QuestionList has been Added here//
     
          
   
    public HttpServletRequest getServeletRequest() {
        return request;
    }
  

    public String questionList() {
        Statement stmt = null;
        String result=null;
        try {
            DBConnect db = new DBConnect();
            db.connect();
            if(sessionmap.get("quizid")!=null)
                    {
                         topicid=Integer.parseInt(String.valueOf(sessionmap.get("topicid")));
                    }
            if(getServeletRequest().getParameter("id")!=null)
            {
                
            topicid=Integer.parseInt(String.valueOf(getServeletRequest().getParameter("id")));
            }
            //String query="select * from topic inner join course_topic on topic.topic_id=course_topic.topic_id where course_topic.course_id="+courseid+"";
            String query="select * from question inner join course_topic on question.quiz_id=course_topic.quiz_id where course_topic.topic_id="+topicid+"";
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
                Quiz ad=new Quiz();
                ad.setQuizid(rs.getInt("quiz_id"));
                ad.setQuestionname(rs.getString("question_name"));
                ad.setOptionA(rs.getString("optionA"));
                ad.setOptionB(rs.getString("optionB"));
                ad.setOptionC(rs.getString("optionC"));
                ad.setOptionD(rs.getString("optionD"));
                ad.setAnswer(rs.getString("answer"));
                ad.setMarks(rs.getInt("marks"));
                
               
               Quizlist.add(ad);
               result="success";

            }
          }
          else
          {
              result="norecord";
              sessionmap.put("noquiz","yes");
          }
        } catch (SQLException e) 
        {
            e.printStackTrace();
            result="error";
        }

        return result;

    }
    //QuizQuestionRetrieveDao//
    
  
int quizids;
    QuizBean qb = new QuizBean();

    public QuizBean getQb() {
        return qb;
    }

    public void setQb(QuizBean qb) {
        this.qb = qb;
    }

  
  
    private ArrayList<QuizBean> Questionlist = new ArrayList<QuizBean>();

    public ArrayList<QuizBean> getQuestionlist() {
        return Questionlist;
    }

    public void setQuestionlist(ArrayList<QuizBean> Questionlist) {
        this.Questionlist = Questionlist;
    }
    // List<QuizBean> useranswer = new ArrayList<QuizBean>();

    /*  public String getQuizQuestion() {

     String status = null;
     DBConnect db = new DBConnect();
     db.connect();
     try {
     if (getServletRequest().getParameter("quizid") != null) {
     quizids = Integer.parseInt(getServletRequest().getParameter("quizid"));
     sessionmap.put("quiz_id", quizids);
     }
     quizids = sessionmap.get("quiz_id");

     String query = "select * from question where quiz_id=?";

     PreparedStatement ps = db.con.prepareStatement(query);

     ps.setInt(1, quizids);
     //  ps.setInt(2, 8);

     ResultSet rs = ps.executeQuery();
     // System.out.println(ps.executeQuery().toString());
     // System.out.println(rs.getRow());
     if (rs.next() == false) {
     status = "quiznotavailable";
     } else {
     // System.out.println("beforeRs");
     while (rs.next()) {
     // QuizBean qb = new QuizBean();
     //System.out.println("after");
     qb.setQuestionId(rs.getInt("question_id"));
     qb.setQuestionName(rs.getString("question_name"));
     qb.setQuizId(rs.getInt("quiz_id"));
     qb.setOptionA(rs.getString("optionA"));
     qb.setOptionB(rs.getString("optionB"));
     qb.setOptionC(rs.getString("optionC"));
     qb.setOptionD(rs.getString("optionD"));
     qb.setAnswer(rs.getString("Answer"));
     qb.setMarks(rs.getInt("marks"));
     Questionlist.add(qb);

     }
     status = "success";
     }
     } catch (Exception e) {
     e.printStackTrace();
     }
     return status;
     }*/
    private int questionNo;

    public int getQuestionNo() {
        return questionNo;
    }

    public void setQuestionNo(int questionNo) {
        this.questionNo = questionNo;
    }

    public String getOneQuestion() {
        String status=null;
        try {
            DBConnect db = new DBConnect();
            db.connect();
            sessionmap.put("questionNo", 1);

            if (getServletRequest().getParameter("quizid") != null) {
                quizids = Integer.parseInt(getServletRequest().getParameter("quizid"));
                sessionmap.put("quiz_id", quizids);
            }
            String query_search = "select * from course_topic where topic_id=" + getServletRequest().getParameter("id") + "";
            PreparedStatement ps_quiz = db.con.prepareStatement(query_search);
            ResultSet rs_q = ps_quiz.executeQuery();
            if (rs_q.next()) {
                quizids = Integer.parseInt(rs_q.getString("quiz_id"));
                sessionmap.put("quiz_id", quizids);
                System.out.println("printing quiz id" + quizids);
            }
            String query = "select max(question_number) from question where quiz_id=?";
            //check quiz start//
            String check_quiz="select * from student_quiz where quiz_id="+quizids+" and student_id='"+sessionmap.get("login")+"'";
            PreparedStatement ps_check=db.con.prepareStatement(check_quiz);
            ResultSet rs_check=ps_check.executeQuery();
            if(rs_check.next())
            {
                sessionmap.put("quiz_exist","yes");
                status="exist";
            }
            else{
                    //check quiz end//
            PreparedStatement ps = db.con.prepareStatement(query);

            ps.setInt(1, quizids);
            ResultSet rs = ps.executeQuery();
            rs.next();

            sessionmap.put("maxQue", rs.getInt("max(question_number)"));

            String tablename = "tempQuiz" + quizids;
            //       System.out.println(tablename);
            String cretab = "create table " + tablename + "(question_number DECIMAL(22),user_answer varchar2(10),quiz_id DECIMAL(22),status varchar2(10))";
            Statement st = db.con.createStatement();
            st.executeUpdate(cretab);
            status="success";
            }
        } catch (SQLException ex) {
            Logger.getLogger(Quiz.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("calling set Question");
        setQuestion();
        return status;
    }

    public void setQuestion() {
        questionNo = Integer.parseInt(String.valueOf(sessionmap.get("questionNo")));
  System.out.print("printing quiz question11111");
        String status = null;
        DBConnect db = new DBConnect();
        db.connect();
        try {

            //    quizids = sessionmap.get("quiz_id");
            String query = "select * from question where quiz_id=? and question_number=?";

            PreparedStatement ps = db.con.prepareStatement(query);
            quizids = Integer.parseInt(String.valueOf(sessionmap.get("quiz_id")));
            System.out.println("priinting in set question");
            System.out.println(quizids);
            ps.setInt(1,quizids);
            ps.setInt(2, questionNo);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                
                qb.setQuestionId(rs.getInt("question_id"));
                qb.setQuestionName(rs.getString("question_name"));
                qb.setQuizId(rs.getInt("quiz_id"));
                qb.setOptionA(rs.getString("optionA"));
                qb.setOptionB(rs.getString("optionB"));
                qb.setOptionC(rs.getString("optionC"));
                qb.setOptionD(rs.getString("optionD"));
                qb.setAnswer(rs.getString("Answer"));
                 System.out.print("printing quiz question");
                System.out.println(qb.getQuestionName());
            }
            status = "success";
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private String userAnswer, correctAnswer;

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }

   

    

    public String loadNextQuestion() {

        String status = null;
        try {
            DBConnect db = new DBConnect();
            db.connect();
            int maxQue = Integer.parseInt(String.valueOf(sessionmap.get("maxQue")));
            questionNo = Integer.parseInt(String.valueOf(sessionmap.get("questionNo")));
            System.out.println(questionNo);
            if (questionNo < maxQue) {

                quizids = Integer.parseInt(String.valueOf(sessionmap.get("quiz_id")));

                String tempString = "insert into tempQuiz" + quizids + " values(?,?,?,?)";
                PreparedStatement ps2 = db.con.prepareStatement(tempString);
                ps2.setInt(1, questionNo);
                ps2.setString(2, userAnswer);
                ps2.setInt(3, quizids);
                if (userAnswer.equals(correctAnswer)) {
                    ps2.setString(4, "Correct");
                } else {
                    ps2.setString(4, "InCorrect");
                }
                System.out.println(correctAnswer + userAnswer);
                ps2.executeUpdate();

                sessionmap.put("questionNo", questionNo + 1);
                this.setQuestion();

                status = "success";

            } else {
                status = submitQuestion();
            }

            db.disconnect();

            return status;
        } catch (SQLException ex) {
            Logger.getLogger(Quiz.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status;
    }

    private int marksIncorrect;
    private int totalAttempted;

    public int getMarksIncorrect() {
        return marksIncorrect;
    }

    public void setMarksIncorrect(int marksIncorrect) {
        this.marksIncorrect = marksIncorrect;
    }

    public int getTotalAttempted() {
        return totalAttempted;
    }

    public void setTotalAttempted(int totalAttempted) {
        this.totalAttempted = totalAttempted;
    }

    public String submitQuestion() {
        String status = null;

        try {
            DBConnect db = new DBConnect();
            db.connect();

            quizids = Integer.parseInt(String.valueOf(sessionmap.get("quiz_id")));
            quizid=Integer.parseInt(String.valueOf(sessionmap.get("quiz_id")));
            sessionmap.put("quiz_id", quizids);

            String storeMarks = "insert into student_quiz(student_id,quiz_id,quiz_marks,quiz_sub_date) values(?,?,?,sysdate)";
            String tempQuery = "select count(question_number) from tempQuiz" + quizids + " where status='Correct'";
            String tempQuery1 = "select count(question_number) from tempQuiz" + quizids + " where status='InCorrect'";
            // String tempQuery2="select count(question_number) from tempQuiz"+quizids+"";
            String deltemp = "drop table tempQuiz" + quizids + "";
           
            PreparedStatement ps4 = db.con.prepareStatement(tempQuery);
            ResultSet rs = ps4.executeQuery();
            rs.next();
            marks = rs.getInt("count(question_number)");
            PreparedStatement ps6 = db.con.prepareStatement(tempQuery1);
            ResultSet rs1 = ps6.executeQuery();
            rs1.next();
            marksIncorrect = rs1.getInt("count(question_number)");

            //new added here
            String tempQueryTotalAttempt = "select count(question_number) from tempQuiz" + quizids + "";
            PreparedStatement pstotalAttempt = db.con.prepareStatement(tempQueryTotalAttempt);
            ResultSet rs2 = pstotalAttempt.executeQuery();
            rs2.next();
            totalAttempted = rs2.getInt("count(question_number)");
            ///ends here
            PreparedStatement psdel = db.con.prepareStatement(deltemp);
            psdel.executeUpdate();
            PreparedStatement psstoremarks = db.con.prepareStatement(storeMarks);

            psstoremarks.setString(1, String.valueOf(sessionmap.get("login")));
            psstoremarks.setInt(2, quizids);
            psstoremarks.setInt(3, marks);
          //  System.out.println(quizids);
            //  System.out.println(String.valueOf(sessionmap.get("login")));
            //System.out.println(marks);

            psstoremarks.executeUpdate();
           // System.out.println("Printing execution"+i);
            //*insert into topic completion start//
            int topicidc=0,assignid=0,courseidc=0;
            String comptime=null;
             String check_quizid="select * from course_topic where assignment_id="+quizid+"";
           
             PreparedStatement ps_qu = db.con.prepareStatement(check_quizid);
            ResultSet rsqu = ps_qu.executeQuery();
            if(rsqu.next())
            {
                topicidc=rsqu.getInt("topic_id");
                assignid=rsqu.getInt("assignment_id");
                courseidc=rsqu.getInt("course_id");
            }
            String check_quiz="select * from student_assignment where student_id='"+sessionmap.get("login")+"' and assignment_id="+assignid+" and verified='verified'";
            PreparedStatement ps_quiz = db.con.prepareStatement(check_quiz);
            ResultSet rsquiz = ps_quiz.executeQuery();
             System.out.println("above rsquiz if");
            if(rsquiz.next())
            {
                 System.out.println("in rsquiz if");
                comptime=String.valueOf(rsquiz.getDate("assignment_sub_date"));
                System.out.print("printing comtime"+comptime);
            }
            if(comptime!=null)
            {
                System.out.println("in comptime if");
                String ins_topic="insert into student_topic values('','"+sessionmap.get("login")+"',"+topicidc+",sysdate)";
                  PreparedStatement ps_topic = db.con.prepareStatement(ins_topic);
                  int i = ps_topic.executeUpdate();
                  //topic comparison//
                   String query_topic ="select count(topic_id) from (select * from course_topic where course_id="+courseidc+")";
                String query_comp="select count(topic_completion_time) from student_topic where topic_id IN(select topic_id from (select * from course_topic where course_id="+courseidc+")) and student_id='"+sessionmap.get("login")+"'";
                  PreparedStatement ps_ch = db.con.prepareStatement(query_topic);
                  PreparedStatement ps_tc = db.con.prepareStatement(query_comp);
                  ResultSet rs_topicq = ps_ch.executeQuery();
                  ResultSet rs_tc = ps_tc.executeQuery();
                  rs_topicq.next();
                  rs_tc.next();
                  System.out.println("to print the counts"+rs_topicq.getInt(1)+"second"+rs_tc.getInt(1));
                  if(rs_topicq.getInt("count(topic_id)")==rs_tc.getInt("count(topic_completion_time)"))
                  {
                      String max_date="select max(topic_completion_time) from student_topic where topic_id IN(select topic_id from (select * from course_topic where course_id="+courseidc+")) and student_id='"+sessionmap.get("login")+"'";
                      PreparedStatement ps_max = db.con.prepareStatement(max_date);
                  ResultSet rs_max = ps_max.executeQuery();
                  rs_max.next();
                  
                  /*  String up_course="update student_course set course_completion_time='"+Date.valueOf(String.valueOf(rs_max.getDate(1)))+"' where student_id='"+sessionmap.get("login")+"' and course_id="+courseidc+"";
                    PreparedStatement ps_up = db.con.prepareStatement(up_course);
                  int k = ps_up.executeUpdate();*/
                  }
                 /* String query_topic ="select topic_id from (select * from course_topic where course_id=122)";
                  PreparedStatement ps_ch = db.con.prepareStatement(query_topic);
                  ResultSet rs_topicq = ps_ch.executeQuery();*/
                                
            }
            //insert into topic completion end//

            return "successful";

        } catch (SQLException ex) {
            Logger.getLogger(Quiz.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status;
    }
   public String  quizAnswer(){
       Statement stmt = null;
        String result=null;
        int quizidc=0;
        try {
            DBConnect db = new DBConnect();
            db.connect();
            
            if(getServeletRequest().getParameter("id")!=null)
            {
                
            quizidc=Integer.parseInt(String.valueOf(getServeletRequest().getParameter("id")));
            }
            //String query="select * from topic inner join course_topic on topic.topic_id=course_topic.topic_id where course_topic.course_id="+courseid+"";
            String query="select * from question where quiz_id="+quizidc+"";
            PreparedStatement ps_course=db.con.prepareStatement(query);
         
          ResultSet rs=ps_course.executeQuery();
             //System.out.println("above while");*/
          
          
            while (rs.next()) {
               
                Quiz ad=new Quiz();
                ad.setQuizid(rs.getInt("quiz_id"));
                ad.setQuestionname(rs.getString("question_name"));
                ad.setOptionA(rs.getString("optionA"));
                ad.setOptionB(rs.getString("optionB"));
                ad.setOptionC(rs.getString("optionC"));
                ad.setOptionD(rs.getString("optionD"));
                ad.setAnswer(rs.getString("answer"));
                ad.setMarks(rs.getInt("marks"));
                
               
               Quizlist.add(ad);
               result="success";

            }
          
        
        } catch (SQLException e) 
        {
            e.printStackTrace();
            result="error";
        }

        return result;

       
   }

    
}
    

