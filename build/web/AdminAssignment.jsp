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
	if(form.title.value=="")
	{
		alert("Plz enter the title");
		form.title.focus();
		return false;
	}
	else if(form.assignmentname.value=="")
	{
		alert("Plz enter the assignmentname");
	        form.assignmentname.focus();
		return false;
		}
                else if(form.duration.value=="")
	{
		alert("Plz enter the duration");
	        form.duration.focus();
		return false;
		}
		else
		{
			
			return true;
		}
		return true;
	}
        </script>
        <div class="jumbotron">
            <h3 align="center"><strong><span class="label label-primary">Assignment Details</span></strong></h3> 
                     <s:form action="assignment" theme="simple" onSubmit="return chkfrm(this)">  
                          <s:if test="%{#session.checkAssignment=='abc'}">
		
					<script>
                                            alert("Assignment already exist can modify the assignment!");
                                            window.location="AdminQuizMenu.jsp";
                                        
                                        </script>
                                        <s:set var="checkAssignment" scope="session" value="null"></s:set>
		
                           </s:if>

                    <s:else >
                        <s:label value="Title:"></s:label><s:textfield type="text" cssClass="form-control" placeholder="Enter Assignment Title" name="title" requiredLabel="true" maxLength="100" minLength="1"></s:textfield>
                        <s:label value="Assignment Question:"></s:label><s:textfield type="text" cssClass="form-control" placeholder="Enter Assignment/Question Description" name="assignementname" requiredLabel="true" maxLength="50" minLength="1"></s:textfield></br>
                           <p align="center"> <s:submit value="Save and Done" action="addassign" cssClass="btn btn-primary"></s:submit> 
                        <s:reset value="Reset" cssClass="btn btn-danger"></s:reset> 
                        <s:submit value="Cancel" cssClass="btn btn-warning"></s:submit></p>
                    </s:else>
                     </s:form></div>
                 </div>
<div class="col-md-4"></div>
</div>
<jsp:include page="masterpage_assistants/footer.jsp"></jsp:include>