<%@ taglib uri="/struts-tags" prefix="s" %>  
<jsp:include page="masterpage_assistants/header_adminLoggedin.jsp"></jsp:include>
<s:if test="%{#session.login == null}">

<% response.sendRedirect("login.jsp");%> 

</s:if>
<div class="row">
    <div class="col-md-4"></div>
    <div class="col-md-4">  
        <div class="jumbotron">
            <h3 align="center"><strong>Topic Details</strong></h3> 
                   <s:form theme="simple">  
                       <s:label value="Topic Name:"></s:label><s:textfield type="text" cssClass="form-control" placeholder="Enter Topic Name" name="topicname"></s:textfield>
                       <s:label value="Description:"></s:label><s:textfield type="text" cssClass="form-control" placeholder="Enter Topic Description" name="topicdescription"></s:textfield></br>
                   <p align="center"><s:submit value="Save" action="SaveNewTopicModify" cssClass="btn btn-primary"></s:submit>  
                  
                       <s:submit value="Cancel" action="Cancel" cssClass="btn btn-warning"></s:submit></p>
                   
                   </s:form></div>
    </div>
<div class="col-md-4"></div>
</div>
<jsp:include page="masterpage_assistants/footer.jsp"></jsp:include>