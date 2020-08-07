

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<jsp:include page="masterpage_assistants/header_adminLoggedin.jsp"></jsp:include>
<div class="row">
    <div class="col-md-4"></div>
    <div class="col-md-4"> 
              <h3 align="center"><strong><span class="label label-primary">Select Any One Option</span></strong></h3>
              <s:if test="%{#session.coursedeleted=='yes'}">
           
             
					<script>
                                            alert("course is deleted!"); 
                                             <% 
                                                
                session.setAttribute("coursedeleted",null);
                                        %>
                                           location="AdminManageCourse.jsp";
                                              
                                        </script>
                                       
                                        <s:set var="coursedeleted" scope="session" value="null"></s:set>
                                        
                                       
                                          
</s:if>  
                 <html>
                     <ul>
                         <li><a href="AdminAddCourse.jsp">Create New Course </a></li>
                         <li><a href="Viewcourse">View all Course</a></li>
                         <li><a href="ModifyCourse">Modify Course Contents</a></li>
                         <li><a href="DeleteCourseSelect">Delete Course</a></li>
                           <li><a href="Viewcourse">Change the Status of the Course</a></li>
                         
                     </ul>
                 </html>
</div>
<div class="col-md-4"></div>
</div>
<jsp:include page="masterpage_assistants/footer.jsp"></jsp:include>