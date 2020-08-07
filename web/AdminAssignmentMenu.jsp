<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<jsp:include page="masterpage_assistants/header_adminLoggedin.jsp"></jsp:include>
<s:if test="%{#session.login == null}">

<% response.sendRedirect("login.jsp");%> 

</s:if>
<div class="row">
    <div class="col-md-4"></div>
    <div class="col-md-4">  
<h3 align="center">Select any one option to do modification to the quiz of this topic </h3>
<%
    request.getParameter("id");
    if(request.getParameter("id")!=null)
    session.setAttribute("topicidquery",request.getParameter("id"));
    out.print(session.getAttribute("courseidselect"));
    out.print("to seperate");
    out.print(session.getAttribute("topicidquery"));
    %>
<s:if test="%{#session.checkassign=='noassign'}">
           
             
					<script>
                                            alert("no Assignment of this course is available!"); 
                                           location="AdminAssignmentMenu.jsp";
                                              
                                        </script>
                                        <% 
                                                
                session.setAttribute("checkassign",null);
                                        %>
                                        <s:set var="checkassign" scope="session" value="null"></s:set>
                                        
                                       
                                          
</s:if>
 <s:if test="%{#session.assigndeleted=='yes'}">
           
             
					<script>
                                            alert("assignment is deleted!"); 
                                             <% 
                                                
                session.setAttribute("assigndeleted",null);
                                        %>
                                           window.location="AdminAssignmentMenu.jsp";
                                              
                                        </script>
                                       
                                        <s:set var="assigndeleted" scope="session" value="null"></s:set>
                                        
                                       
                                          
</s:if>   
<s:if test="%{#session.assignedited=='yes'}">
           
             
					<script>
                                            alert("assignment is edited!"); 
                                             <% 
                                                
                session.setAttribute("assignedited",null);
                                        %>
                                           window.location="AdminAssignmentMenu.jsp";
                                              
                                        </script>
                                       
                                        <s:set var="assignedited" scope="session" value="null"></s:set>
                                        
                                       
                                          
</s:if>  
   <s:if test="%{#session.checkassign=='yes'}">
           
             
					<script>
                                            alert("Assignment of this course is already exist! can not insert any other assignment in one topic"); 
                                           location="AdminAssignmentMenu.jsp";
                                              
                                        </script>
                                        <% 
                                                
                session.setAttribute("checkassign",null);
                                        %>
                                        <s:set var="checkassign" scope="session" value="null"></s:set>
                                        
                                       
                                          
</s:if>                                     
    <ul>
          <li> <a href="CheckAssignment">Add Assignment</a></li>
          <li><a href="DeleteAssignmentView">Delete Assignment</a></li>
          <li><a href="EditAssignmentView">Edit Assignment</a></li>
    </ul>
    </div>
<div class="col-md-4"></div>
</div>
<jsp:include page="masterpage_assistants/footer.jsp"></jsp:include>
