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
                else if(form.assignmentcreationdate.value=="")
	{
		alert("Plz enter assignment creation date");
	        form.assignmentcreationdate.focus();
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
            <h2 align="center"><strong><span class="label label-primary">Assignment Details</span></strong></h2> 
                     <s:form action="assignment" theme="simple" onSubmit="return chkfrm1(this)">  
                          <s:if test="%{#session.checkassign=='noassign'}">
		
					<script>
                                            alert("Assignment does not exist!");
                                            window.location="AdminAssignmentMenu.jsp";
                                        
                                        </script>
                                        <s:set var="checkassign" scope="session" value="null"></s:set>
		
</s:if>

                    <s:else >
                       <s:label value="Title:"></s:label><s:textfield cssClass="form-control"  name="title" requiredLabel="true"></s:textfield>
                        <s:label value="Assignment Name"></s:label><s:textfield cssClass="form-control"  name="assignmentname" requiredLabel="true" maxLength="50" minLength="1"></s:textfield>
                        <s:label value="Assignment Creation Date"></s:label><s:textfield cssClass="form-control" disabled="true" name="assignmentcreationdate" requiredLabel="true"></s:textfield></br>
                   
                     <p align="center"><s:submit value="Save" action="EditSaveAssignment" cssClass="btn btn-primary"></s:submit> 
                     
                     <s:submit value="Cancel" cssClass="btn btn-warning"></s:submit></p>
                    </s:else>
                     </s:form>
                 </div>
<div class="col-md-4"></div>
</div>
<jsp:include page="masterpage_assistants/footer.jsp"></jsp:include>