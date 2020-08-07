package com.elearning.model;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.interceptor.ServletRequestAware;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import java.sql.PreparedStatement;
import java.util.Map;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

public class UploadFile extends ActionSupport implements ServletRequestAware,SessionAware {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private File userImage;
    private String userImageContentType;
    private String userImageFileName;

    private HttpServletRequest servletRequest;
    SessionMap<String, Object> sessionmap;

    
    public String uploadFile() {
        String qu = "insert into topic_content(topic_content_id,doc_content,topic_id) values('',?,?)";
        try {

            DBConnect db = new DBConnect();
            db.connect();
            PreparedStatement ps = db.con.prepareStatement(qu);
            System.out.println(servletRequest.getSession().getAttribute("topicid_session"));
            int topicid= Integer.parseInt(String.valueOf(servletRequest.getSession().getAttribute("topicid_session")));
            System.out.println(topicid);
                    

            //String filePath = servletRequest.getSession().getServletContext().getRealPath("/").concat("userimages");  
            String filePath = "D:/uploadContent/Doc_Content/";

            System.out.println("Location:" + filePath);//see the server console for actual location  
            File fileToCreate = new File(filePath, this.userImageFileName);
            FileUtils.copyFile(this.userImage, fileToCreate);//copying image in the new file  
            
            ps.setString(1, filePath + userImageFileName);
            //ps.setInt(2, 3);
            ps.setInt(2, topicid);
            ps.executeUpdate();
            db.disconnect();
            servletRequest.getSession().setAttribute("contentadded","yes");
            sessionmap.put("uploaded","yes");
            return "success";
            
            
            
        } catch (Exception e) {
            System.err.println(e);
            return "error";

        }
    }
     public String uploadAudio() {
        String qu = "insert into topic_content(topic_content_id,audio_content,topic_id) values('',?,?)";
        try {

            DBConnect db = new DBConnect();
            db.connect();
            PreparedStatement ps = db.con.prepareStatement(qu);
            System.out.println(servletRequest.getSession().getAttribute("topicid_session"));
            int topicid= Integer.parseInt(String.valueOf(servletRequest.getSession().getAttribute("topicid_session")));
            System.out.println(topicid);
                    

            //String filePath = servletRequest.getSession().getServletContext().getRealPath("/").concat("userimages");  
            String filePath = "D:/uploadContent/Audio_Content/";

            System.out.println("Location:" + filePath);//see the server console for actual location  
            File fileToCreate = new File(filePath, this.userImageFileName);
            FileUtils.copyFile(this.userImage, fileToCreate);//copying image in the new file  
            
            ps.setString(1, filePath + userImageFileName);
            //ps.setInt(2, 3);
            ps.setInt(2, topicid);
          //  ps.executeUpdate();
            db.disconnect();
            return "success";
        } catch (Exception e) {
            System.err.println(e);
            return "error";

        }
    }
      public String uploadVideo() {
       
          String qu = "insert into topic_content(topic_content_id,video_content,topic_id) values('',?,?)";
        try {

            DBConnect db = new DBConnect();
            db.connect();
            PreparedStatement ps = db.con.prepareStatement(qu);
            System.out.println(servletRequest.getSession().getAttribute("topicid_session"));
            int topicid= Integer.parseInt(String.valueOf(servletRequest.getSession().getAttribute("topicid_session")));
            System.out.println(topicid);
                    

            //String filePath = servletRequest.getSession().getServletContext().getRealPath("/").concat("userimages");  
            String filePath = "D:/uploadContent/Video_Content/";

            System.out.println("Location:" + filePath);//see the server console for actual location  
            File fileToCreate = new File(filePath, this.userImageFileName);
            FileUtils.copyFile(this.userImage, fileToCreate);//copying image in the new file  
            
            ps.setString(1, filePath + userImageFileName);
            //ps.setInt(2, 3);
            ps.setInt(2, topicid);
          //  ps.executeUpdate();
            db.disconnect();
            
            return "success";
        } catch (Exception e) {
            System.err.println(e);
            return "error";

        }
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

    @Override
    public void setServletRequest(HttpServletRequest servletRequest) {
        this.servletRequest = servletRequest;

    }
    public void setSession(Map map) {
        sessionmap = (SessionMap) map;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
