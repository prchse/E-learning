<%@ taglib uri="/struts-tags" prefix="s" %>  
<jsp:include page="masterpage_assistants/header_loggedUser.jsp"></jsp:include>
<s:if test="%{#session.login == null}">

<% response.sendRedirect("login.jsp");%> 

</s:if>
    <div class="row">
        <div class="col-md-4"></div>
        <div class="col-md-4">  
            
            <div class="jumbotron" align="center">
                <h3 align="center"><strong>Enter Course Details</strong></h3> 
            <s:form action="FileUploadAssignment" method="post" enctype="multipart/form-data">  
                <s:iterator value="assbeanlist">
                    <s:label value="Title:"></s:label><s:textfield  name="title" disabled="true" value="%{title}" ></s:textfield>
                    <s:label value="Question"></s:label><s:textfield cssClass="form-control"  disabled="true" name="assignmentname" value="%{assignmentname}"></s:textfield>
                   
                <s:label value="Write answers in text form and upload it in .txt format"></s:label>
                <s:file name="userImage" label="File" ></s:file>
                
             
                </s:iterator>
                <s:submit value="Upload" align="center" ></s:submit></br> 
                 <a href="PreviousAttempt" class="btn-info">Previous Attempts</a>
            </s:form>  


        </div>
        <div class="col-md-4"></div>
    </div>
    <jsp:include page="masterpage_assistants/footer.jsp"></jsp:include>