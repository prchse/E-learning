
<%@taglib uri="/struts-tags" prefix="s" %>
<jsp:include page="masterpage_assistants/header_adminLoggedin.jsp"></jsp:include>

        <style>
            .button_s{
                position: left;
                color: green;
            }
        </style>
    
<div class="row">
    <div class="col-md-3"></div>
    <div class="col-md-6">  
        <h3 align="center"><strong><span class="label label-primary">Detail of This Student</span></strong></h1>

<%

//     ServletContext sc = request.getServletContext();
  //   sc.getAttribute("list_s");

%>

        
<%--<s:iterator value="#application.list_s">--%>
<div>
    <s:form theme="simple">
        
       
<s:iterator value="list_student">   
    
    <label>Student Id:</label> <s:textfield label="Student Id" value="%{studentid}" name="studentid" disabled="true" ></s:textfield></br>
   <label>First Name:</label>  <s:textfield label="First Name" value="%{fname}" name="fname" disabled="true" ></s:textfield></br>
    <label>Last Name:</label> <s:textfield label="Last Name" value="%{lname}" name="lname" disabled="true"></s:textfield></br>
    <label>Contact No:</label> <s:textfield label="ContactNo" value="%{contactno}" name="contactno" disabled="true"></s:textfield></br> 
    <label>Registration time:</label> <s:textfield label="Registration Time" value="%{registrationTime}" name="registrationTime" disabled="true"></s:textfield></br> 
    
               <a href="ChangeStatusNotify?id=<s:property value="studentid"/>" class="btn btn-success" role="button">Activate</a>
           
                <a href="Admin.jsp" class="btn btn-danger" role="button">Cancel</a>
</s:iterator>     
            
            
           </p>
    </s:form>
</div>
</div>
<div class="col-md-3"></div>
</div>

<jsp:include page="masterpage_assistants/footer.jsp"></jsp:include>



