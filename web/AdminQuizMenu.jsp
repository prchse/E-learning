<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<jsp:include page="masterpage_assistants/header_adminLoggedin.jsp"></jsp:include>
<div class="row">
    <div class="col-md-4"></div>
    <div class="col-md-4">  
   <h3 align="center"><strong><span class="label label-success">Select any one option to do modification to the quiz of this topic</span><strong> </h3>
<%
    request.getParameter("id");
    if(request.getParameter("id")!=null)
    session.setAttribute("topicidquery",request.getParameter("id"));
   
    %>
<s:if test="%{#session.checkquiz=='noquiz'}">
           
             
					<script>
                                            alert("no quiz of this course is available!"); 
                                           location="AdminQuizMenu.jsp";
                                              
                                        </script>
                                        <% 
                                                
                session.setAttribute("checkquiz",null);
                                        %>
                                        <s:set var="checkquiz" scope="session" value="null"></s:set>
                                        
                                       
                                          
</s:if>
 <s:if test="%{#session.quizdeleted=='yes'}">
           
             
					<script>
                                            alert("quiz is deleted!"); 
                                             <% 
                                                
                session.setAttribute("quizdeleted",null);
                                        %>
                                           window.location="AdminQuizMenu.jsp";
                                              
                                        </script>
                                       
                                        <s:set var="quizdeleted" scope="session" value="null"></s:set>
                                        
                                       
                                          
</s:if>   
<s:if test="%{#session.quizedited=='yes'}">
           
             
					<script>
                                            alert("quiz is edited!"); 
                                             <% 
                                                
                session.setAttribute("quizedited",null);
                                        %>
                                           window.location="AdminQuizMenu.jsp";
                                              
                                        </script>
                                       
                                        <s:set var="quizedited" scope="session" value="null"></s:set>
                                        
                                       
                                          
</s:if>      
    <ul>
          <li> <a href="CheckQuiz">Add Quiz</a></li>
          <li><a href="DeleteQuizView">Delete Quiz</li>
          <li><a href="ModifyQuestionView">Modify Question to Quiz</a></li>
          <li><a href="EditQuizDetail">Edit Quiz</li>
    </ul>
    </div>
<div class="col-md-4"></div>
</div>
<jsp:include page="masterpage_assistants/footer.jsp"></jsp:include>
