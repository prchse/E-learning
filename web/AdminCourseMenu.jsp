<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<jsp:include page="masterpage_assistants/header_adminLoggedin.jsp"></jsp:include>
<s:if test="%{#session.login == null}">

    <% response.sendRedirect("login.jsp");%> 

</s:if>
<div class="row">
    <div class="col-md-4"></div>
    <div class="col-md-4">  
        <h3 align="center"><strong><span class="label label-primary">Select any one option</span></strong></h3>
        <ul>
            <li> <a href="addcourse.jsp"><strong>Add Course<strong></a></li>
                            <li><a href="delcourse"><strong>Delete Course</strong></a></li>
                            <li><a href="quizmenu.jsp"><strong>Edit Course Details</strong></a></li>

                            </ul>
                            </div>
    <div class="col-md-4">
        
    </div>
                            </div>
                            <jsp:include page="masterpage_assistants/footer.jsp"></jsp:include>