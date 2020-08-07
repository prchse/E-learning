<%@ taglib uri="/struts-tags" prefix="s" %>  

<jsp:include page="masterpage_assistants/header_adminLoggedin.jsp"></jsp:include>
    
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:if test="%{#session.login == null}">

<% response.sendRedirect("login.jsp");%> 

</s:if>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

   
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Insert title here</title>
  
    <body>
        
        <s:form action="UploadAudio" method="post" enctype="multipart/form-data">  
            <s:file name="userImage" label="File" ></s:file>
            <s:submit value="Upload" align="center" ></s:submit>  
        </s:form>  
    </body>

<jsp:include page="masterpage_assistants/footer.jsp"></jsp:include>