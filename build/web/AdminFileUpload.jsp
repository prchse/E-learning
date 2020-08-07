<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<jsp:include page="masterpage_assistants/header_adminLoggedin.jsp"></jsp:include>
<div class="row">
    <div class="col-md-4"></div>
    <div class="col-md-4"> 
              <h3 align="center"><strong><span class="label label-primary">Select File to Upload</span></strong></h3>
<%
    
    session.setAttribute("topicid_session", request.getParameter("id"));
%> 
              
              <s:if test="%{#session.login == null}">

            <% response.sendRedirect("login.jsp");%> 
            

</s:if>
              <s:if test="%{#session.uploaded=='yes'}">

               <script>
                                            alert("content has been added successfully!"); 
                                             <% 
                                                
                session.setAttribute("uploaded",null);
                session.removeAttribute("uploaded");
                                        %>
                                          
                                              
                                        </script>
                                        <s:set var="coursedeleted" scope="session" value="null"></s:set>

</s:if>
<s:form action="FileUpload" method="post" enctype="multipart/form-data">  
    <s:file name="userImage" label="File" ></s:file>
    <s:submit value="Upload" align="center" ></s:submit>  
</s:form>  
</div>
<div class="col-md-4"></div>
</div>
<jsp:include page="masterpage_assistants/footer.jsp"></jsp:include>