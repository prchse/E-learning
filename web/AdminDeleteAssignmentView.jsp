<%@ taglib uri="/struts-tags" prefix="s" %>  

<jsp:include page="masterpage_assistants/header_adminLoggedin.jsp"></jsp:include>
<s:if test="%{#session.login == null}">

<% response.sendRedirect("login.jsp");%> 

</s:if>
<div class="row">
    <div class="col-md-4"></div>
    <div class="col-md-4">  
                <h3 align="center"><strong><span class="label label-primary">Assignment Details</span></strong></h3> 
                     <s:form action="assignment" theme="simple">  
                          <s:if test="%{#session.checkassign=='noassign'}">
		
					<script>
                                            alert("Assignment does not exist!");
                                            window.location="AdminAssignmentMenu.jsp";
                                        
                                        </script>
                                        <s:set var="checkassign" scope="session" value="null"></s:set>
		
</s:if>

                    <s:else >
                        <s:label value="Title:"></s:label><s:textfield cssClass="form-control" disabled="true" name="title" requiredLabel="true"></s:textfield>
                        <s:label value="Assignment Name:"></s:label><s:textfield cssClass="form-control" disabled="true" name="assignmentname" requiredLabel="true"></s:textfield>
                        <s:label value="Assignment Creation Date:"></s:label><s:textfield cssClass="form-control" disabled="true" name="assignmentcreationdate" requiredLabel="true"></s:textfield></br>
                   
                            <p align="center"><s:submit value="Delete" action="DeleteAssignment" cssClass="btn btn-primary"></s:submit> 
                     
                            <s:submit value="Cancel" cssClass="btn btn-warning"></s:submit></p>
                       
                    </s:else>
                     </s:form>
                 </div>
<div class="col-md-4"></div>
</div>
<jsp:include page="masterpage_assistants/footer.jsp"></jsp:include>