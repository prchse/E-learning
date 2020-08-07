<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<jsp:include page="masterpage_assistants/header.jsp"></jsp:include>
<div class="row">
    <div class="col-md-4"></div>
    <div class="col-md-4">  
        <div class="jumbotron"
        <h3 align="center"><strong><span class="label label-success">Select any Course to be Deleted</span></strong></h3>
                  <s:form theme="simple">
                      <select name="courseid"> 
                      <s:iterator value="list">
                 <option value="<s:property value="courseid"/>"> <s:property value="coursename"/></option>
                  </s:iterator>
                  </select>
                      <p align="center">
                      <s:submit value="Go" cssClass="btn btn-primary" action="courseEdit"></s:submit>  
                      <s:submit value="Cancel" cssClass="btn btn-info" action=""></s:submit></p>  
  
                  </s:form>
                       </div>
        </div>
<div class="col-md-4"></div>
</div>
<jsp:include page="masterpage_assistants/footer.jsp"></jsp:include>