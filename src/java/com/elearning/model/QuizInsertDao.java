/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elearning.model;

import java.sql.PreparedStatement;

/**
 *
 * @author Suman
 */
import java.sql.*;
import java.sql.Date;
public class QuizInsertDao {
    public static int save(Quiz r)
    {
        int status=0;
        try
        {
            DBConnect db=new DBConnect();
            db.connect();
           
            String query="insert into quiz values(quiz_seq.nextval,?,?,?)";
            PreparedStatement ps=db.con.prepareStatement(query);
            ps.setString(1,r.getQuizname());
        
         //    java.util.Date cd=new java.util.Date(r.getQuizcreationdate());
        //     Date creation_d=new Date(cd.getTime());
           //   ps.setDate(2, creation_d);
              ps.setString(3,r.getDuration());
               status=ps.executeUpdate(); 
        }
        catch(Exception e){
                e.printStackTrace();
               }
    return status;
    }
}
