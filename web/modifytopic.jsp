<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<jsp:include page="masterpage_assistants/header.jsp"></jsp:include>
<div class="row">
    <div class="col-md-4"></div>
    <div class="col-md-4">
<h3 align="center">Select any Course to be Deleted</h3>
<html>
               <s:form theme="simple">
                 <select name="courseid"> 
                <s:iterator value="list ">
               <option value="<s:property value="courseid"/>"> <s:property value="coursename"/></option>
 </s:iterator>
</select>
    
   <s:submit value="Go" cssClass="btn btn-primary" action="topiclist"></s:submit>  
   <s:submit value="Cancel" cssClass="btn btn-danger" action="fgfgf"></s:submit>  
  
    </s:form>
</html>
</div>
        </div>
<div class="col-md-3"></div>
</div>
<jsp:include page="masterpage_assistants/footer.jsp"></jsp:include>