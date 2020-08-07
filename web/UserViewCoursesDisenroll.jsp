

<%@taglib uri="/struts-tags" prefix="s" %>
<s:if test="%{#session.login == null}">

<% response.sendRedirect("login.jsp");%> 

</s:if>
<jsp:include page="masterpage_assistants/header_loggedUser.jsp"></jsp:include>
<div class="row">
    <div class="col-md-3"></div>
    <div class="col-md-6">  
                 <h3 align="center">Records of All Courses</h3>
                 <div class="container">
                <table style="width: 50%" class="jumbotron">
                    <thead>
            <tr>
                <!--th>Course Id</th-->
                <th>Course Name</th>
                <th>Course Creation Date</th>
               
                <th>Duration</th>
                <th>Description</th>
                
            </tr></thead>
<s:iterator value="list"> 
        
        <tr style="width: 70%">
             
                <td ><s:property value="coursename"/></td>
                <td> <s:property value="Creationdate"/></td>
                <td> <s:property value="description"/></td>
                <td> <s:property value="duration"/></td>
                
              
                <td><a class="btn btn-danger" href='disenrollcourse?courseid=<s:property value="courseid"/>'>Disenroll</a></td>
        </tr>
    </s:iterator>
                 </table>
                 </div>
        </div>
<div class="col-md-3"></div>
</div>
<jsp:include page="masterpage_assistants/footer.jsp"></jsp:include>
