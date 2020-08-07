package com.elearning.model;

import com.opensymphony.xwork2.ActionSupport;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

public class Login extends ActionSupport implements SessionAware {

    private String username, password;
    SessionMap<String, Object> sessionmap;
    
    HttpServletRequest request;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String execute() {

        switch (authenticate(username, password)) {
            case "user":
                sessionmap.put("login", username);
                return "user";
            case "admin":
                sessionmap.put("login", username);
                return "admin";
            case "error":

                return "error";
            case "errord":

                return "errord";
            default:
                return "error";

        }
    }

    public String authenticate(String username, String password) {

        String status = null;
        String query1 = "select * from Login where login_id=? and password=?";

        try {
            DBConnect db = new DBConnect();
            db.connect();
            String session_id = ServletActionContext.getRequest().getSession().getId();

            sessionmap.put("user_session_id", session_id);

            PreparedStatement ps = db.con.prepareStatement(query1);
          
            ps.setString(1, username);
            ps.setString(2, password);

            System.out.println(username);

            System.out.println(query1);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                if ((rs.getString("user_type")).equals("admin")) {

                    status = "admin";
                } else if ((rs.getString("status")).equals("activated") && rs.getString("user_type").equals("student")) {
                    String querylastlogin = "insert into student_logtime values('','" + rs.getString("login_id") + "','" + session_id + "',CURRENT_TIMESTAMP," + null + ")";

                    PreparedStatement ps2 = db.con.prepareStatement(querylastlogin);
                    ps2.executeUpdate();
                    //System.out.println("login="+r);
                    sessionmap.put("userid_session", username);
                    status = "user";

                } else {
                    sessionmap.put("deactivated", "yes");
                    status = "errord";

                }

            } else {
                sessionmap.put("authenticate", "no");
                status = "error";
            }

           // ps2.executeUpdate();
        } catch (SQLException e) {
            e.getStackTrace();
        }
        return status;
    }

    public void lastLogout(String username) {
        String sessionid = String.valueOf(sessionmap.get("user_session_id"));
        String user_id = String.valueOf(sessionmap.get("userid_session"));
        String quer = "update student_logtime set logout_time=CURRENT_TIMESTAMP where session_id='" + sessionid + "' and student_id='" + user_id + "'";
        try {

            DBConnect db = new DBConnect();
            db.connect();
            PreparedStatement ps3 = db.con.prepareStatement(quer);

            System.out.println(username);
            int i = ps3.executeUpdate();
            System.out.println(quer + i);

        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     *
     * @param map
     */
    @Override
    public void setSession(Map<String, Object> map) {
        sessionmap = (SessionMap) map;

    }

    /**
     *
     * @return @throws SQLException
     */
    public String logout() throws SQLException {

        lastLogout(sessionmap.get("login").toString());
        sessionmap.invalidate();
        return "success";
    }

}
