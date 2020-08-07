<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="java.util.*;" %>

    <jsp:include page="masterpage_assistants/header_loggedUser.jsp"></jsp:include>
    <div class="row">
    <div class="col-md-3"></div>
    <div class="col-md-6"> 
    <div class="jumbotron">
        <h3 align="center"><strong>Your Current Profile</strong></h3>
<s:property value="#session.login"/>

<s:form action="updatesProfile" theme="simple">
<s:label value="Student Id:"></s:label><s:textfield value="%{userbean.studentId}" readonly="true" name="userbean.studentId" />
<s:label value="First Name:"></s:label><s:textfield value="%{userbean.fname}" name="userbean.fname"  />
<s:label value="Last Name:"></s:label><s:textfield value="%{userbean.lname}" name="userbean.lname" />
<s:label value="Email Id:"></s:label><s:textfield value="%{userbean.emailId}" readonly="true" name="userbean.emailId"/>
<s:label value="Contact No:"></s:label><s:textfield value="%{userbean.contactno}" name="userbean.contactno" />
 
<s:submit value="Update" />
 
</s:form>
    </div></div>
<div class="col-md-3"></div>
  
    </div>
    <jsp:include page="masterpage_assistants/footer.jsp"></jsp:include>
