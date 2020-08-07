
       
        
        
<%@taglib uri="/struts-tags" prefix="s" %>
<jsp:include page="masterpage_assistants/header_adminLoggedin.jsp"></jsp:include>
 <style>
            .button_s{
                position: left;
                color: green;
            }
        </style>
<div class="row">
    <div class="col-md-2"></div>
    <div class="col-md-8">  
        <h2 align="center"><strong><span class="label label-primary">Details of This Student</span></strong></h2>

<%

//     ServletContext sc = request.getServletContext();
  //   sc.getAttribute("list_s");

%>

        
<%--<s:iterator value="#application.list_s">--%>
<div>
    <div class="jumbotron">
    <s:form theme="simple">
        
       
<s:iterator value="list_student">   
   
    <s:textfield label="Student Id" value="%{studentid}" disabled="true"></s:textfield></br>
    <s:textfield label="First Name" value="%{fname}" disabled="true"></s:textfield></br>
    <s:textfield label="Last Name" value="%{lname}" disabled="true"></s:textfield></br>
    <s:textfield name="status" label="Status" value="%{status}" disabled="true"></s:textfield></br> 
    <s:iterator value="course_list">   
    <s:textfield label="Course " value="%{course_name}" disabled="true"></s:textfield></br>
    <s:textfield label="Registration Time" value="%{registration_time}" disabled="true"></s:textfield></br>
    <s:textfield label="Course Completion Time" value="%{course_completion_time}" disabled="true"></s:textfield>
    </s:iterator>
     <s:if test="status=='deactivate'">
        <s:submit value="Activate" class="button_s" action="activate"></s:submit> 
    </s:if>
     <s:elseif test="status=='activate'">
        <s:submit value="Deactivate" class="button_s" action="deactivate"></s:submit> 
    </s:elseif> 
    </s:iterator>
      <s:submit value="Edit" class="button_s" action="edit" ></s:submit>
    </s:form></div>
</div>
  </div>
<div class="col-md-2"></div>
</div>

<jsp:include page="masterpage_assistants/footer.jsp"></jsp:include>



