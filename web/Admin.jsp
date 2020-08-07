

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

    <jsp:include page="masterpage_assistants/header_adminLoggedin.jsp"></jsp:include>
    <%@taglib prefix="s" uri="/struts-tags" %>
<div class="row">
    <div class="col-md-4"></div>
    <div class="col-md-4"> 
        <s:if test="%{#session.login == null}">

<% response.sendRedirect("login.jsp");%> 

</s:if>
  
    <s:if test="%{#session.uploaded=='yes'}">
           
             
        <script>
                                            session.removeAttribute("uploaded");
                                            alert("course is uploaded and now available to the students!"); 
                                              
                                              session.setAttribute("uploaded",null);
                                             <% 
                                                
                session.setAttribute("uploaded",null);
                session.removeAttribute("uploaded");
                                        %>
                                           
                                              
                                        </script>
                                       
           <s:set var="uploaded" scope="session" value="null"></s:set>
                                        
                                                          
                                          
</s:if>  
                                        
                                        
   
        <h2 align="center"><strong>Hello Admin</strong></h2>
        <ul>
<!--            
            <li><a href="ManageStudents"><strong>Manage Students</strong></a></li>
            <li><a href="AdminManageCourse.jsp"><strong>Manage Course</strong></a></li>-->
        </ul>    
        <div class="container-fluid">
            <div class="jumbotron">
                Welcome to the Administrator console
            </div>
        </div>
   
<div class="col-md-4"></div>
</div>
<jsp:include page="masterpage_assistants/footer.jsp"></jsp:include>

