<%@page import="java.sql.PreparedStatement"%>
<%@page import="com.elearning.DBConnect"%>
<%@page import="com.elearning.QuizBean"%>
<!DOCTYPE html>

        <%@taglib prefix="s" uri="/struts-tags" %>
        <jsp:include page="masterpage_assistants/header_loggedUser.jsp"></jsp:include>
            <div class="row">
                <div class="col-md-3"></div>
                <div class="col-md-6">  
                    <h3 align="center"><strong>Records of All Quiz</strong></h3>
                    <div class="jumbotron">
                        <table style="width: 70%" class="jumbotron">
                            <thead>
                                </thead>
                            <%  /*
                                 <s:iterator value="Questionlist"> 


                                 <td><s:property value="questionId" /></td>
                                
                                 <td> <s:property value="questionName"/></td>
                                 <td> <s:property value="quizId"/></td>

                                
                                 <td><s:radio value='<s:property value="optionA"/>' name="ansR" /></td>
                                 <td> <s:property value="optionB"/></td>
                                 <td> <s:property value="optionC"/></td>
                                 <td> <s:property value="optionD"/></td>

                              
                                 </tr>
                                 </s:iterator>
                                 status="status"
                                    
                                 */

                                int i = 1;
                                QuizBean q= new QuizBean();
                                try{
                                    DBConnect db= new DBConnect();
                                    db.connect();
                                    String st= "select * from question where quiz_id=?";
                                    PreparedStatement ps = con.db
                                }
                            %>
                            <%
                                
                            String my=session.getAttribute("login").toString();
                           // String quizid=session.getAttribute("quiz_id").toString();
                                    
                            //System.out.println(my);
                            %>
                             <s:form method="post" action="QuizSubmit">
                          
                                <s:iterator value="Questionlist" >
                                      <s:form name="<s:property value="questionId"/>" method="post" action="QuizSubmit" theme="simple">
                                    <tr><td>  
                                <table border="3px" bgcolor="yellow">
                                    <tr bgcolor="white" >
                                        <td><%= i%><% i++;%><!--s:property value="questionId"/--></td>
                                        <td><s:property value="questionName"/></td>
                                   
                                    </tr>
                                    <tr>
                                        <td ><input type="radio" name="<s:property value="questionId"/>"  value="A" onclick="" /><s:property value="optionA"/></td>
                                    
                                        <td ><input type="radio" name="<s:property value="questionId"/>"  value="B" onclick="" /><s:property value="optionB"/></td>
                                    </tr>
                                    <tr>
                                        <td ><input type="radio" name="<s:property value="questionId"/>"  value="C" onclick="" /><s:property value="optionC"/></td>
                                        <td ><input type="radio" name="<s:property value="questionId"/>"  value="D" onclick="" /><s:property value="optionD"/></td>
                                        <td> <input type="hidden" value="<%=my%>" name="studentId" /></td>
                                        <td> <input type="hidden" value="<s:property value="answer"/>" name="answer" /></td>
                                    </tr>
                                </table></td></tr>
                                  <% 
                                    //<s:hidden name="answer" value="<s:property value="answer"/>" > </s:hidden>%>
                                      </s:form>
                            </s:iterator>
                            <!--:hidden id="answer" value="answer"/-->
                           
                            <s:submit name="submit" />
                        </s:form>

                    </table>
                </div>
            </div>
            <div class="col-md-3"></div>
        </div>

        <jsp:include page="masterpage_assistants/footer.jsp"></jsp:include>
   