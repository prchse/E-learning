<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib uri="/struts-tags" prefix="s" %>
<jsp:include page="masterpage_assistants/header_adminLoggedin.jsp"></jsp:include>
<s:if test="%{#session.login == null}">

<% response.sendRedirect("login.jsp");%> 

</s:if>
<div class="row">
    <div class="col-md-4"></div>
    <div class="col-md-4">  
        <h3 align="center"><strong><span class="label label-primary">Select any Course to be Deleted</span></strong></h3>
<s:form theme="simple">
    <select name="courseid"> 
        <s:iterator value="list ">
<option value="<s:property value="courseid"/>"> <s:property value="coursename"/></option>
 </s:iterator>
</select>
    
    <p align="center"><s:submit value="Delete" action="coursedelete" cssClass="btn btn-primary"></s:submit>  
        <s:submit value="Cancel" action="coursecancel" cssClass="btn btn-warning"></s:submit></p>  
  
    </s:form>
    </div>
<div class="col-md-4"></div>
</div>
<jsp:include page="masterpage_assistants/footer.jsp"></jsp:include>
