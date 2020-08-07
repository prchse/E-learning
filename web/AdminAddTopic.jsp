<%@ taglib uri="/struts-tags" prefix="s" %>  
<jsp:include page="masterpage_assistants/header_adminLoggedin.jsp"></jsp:include>
<s:if test="%{#session.login == null}">

<% response.sendRedirect("login.jsp");%> 

</s:if>
<div class="row">
    <div class="col-md-4"></div>
    <div class="col-md-4">  
        <script type="text/javascript">
            function chkfrm(form)
{
	if(form.topicname.value=="")
	{
		alert("Plz enter the topic name");
		form.topicname.focus();
		return false;
	}
	else if(form.topicdescription.value=="")
	{
		alert("Plz enter the telephone");
	        form.topicdescription.focus();
		return false;
		}
		else
		{
			
			return true;
		}
		return true;
	}
        </script>
        <s:if test="%{#session.courseadded=='yes'}">
           
             
					<script>
                                            alert("course description is added!"); 
                                             <% 
                                                
                session.setAttribute("courseadded",null);
                session.removeAttribute("courseadded");
                                        %>
                                          
                                              
                                        </script>
                                       
                                       
                                        
                                       
                                          
</s:if>  
                                         <s:if test="%{#session.topicadded=='yes'}">
           
             
					<script>
                                            alert("This topic details are added to this course!"); 
                                             <% 
                                                
                session.setAttribute("topicadded",null);
                                        %>
                                          
                                              
                                        </script>
                                       
                                        <s:set var="topicadded" scope="session" value="null"></s:set>
                                        
                                       
                                          
</s:if>  
               <div class="jumbotron"
              <legend align="center"><strong>Insert Topic Details </strong></legend> </br></br>
                            <s:form action="topic" method="post" onSubmit="return chkfrm(this)" theme="simple">  
                                <s:label value="Topic Name:"></s:label><s:textfield type="text" placeholder="Like(Computer)" name="topicname" requiredLabel="true" maxLength="50" minLength="1"></s:textfield></br></br>
                                <s:label value="Description:"></s:label> <s:textfield type="text" placeholder="Description" name="topicdescription" requiredLabel="true" maxLength="2000" minLength="1"></s:textfield></br></br>
                            <s:submit type="submit" value="Save and Next" cssClass="btn btn-success" action="addtopicnext"></s:submit>
                             <s:submit type="submit" value="Save and Add New Topic" cssClass="btn btn-primary" action="addtopicnew" ></s:submit> 
                               <a href="Admin.jsp" class="btn btn-info" role="button">Cancel</a>
                        <s:reset type="reset" value="Reset" cssClass="btn btn-info"></s:reset> 
                       <a href="Admin.jsp" class="btn btn-info" role="button">Add Later</a>
                      </s:form>
         </div></div>
<div class="col-md-4"></div>
</div>
<jsp:include page="masterpage_assistants/footer.jsp"></jsp:include>