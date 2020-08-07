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
	if(form.coursename.value=="")
	{
		alert("Plz enter course name");
		form.coursename.focus();
		return false;
	}
	else if(form.duration.value=="")
	{
		alert("Plz enter duration");
	         form.duration.focus();
		return false;
		}
         else if(form.coursedescription.value=="")
	{
		alert("Plz enter course description");
	         form.coursedescription.focus();
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
            <h3 align="center"><strong>Enter Course Details</strong></h3> 
              <s:form theme="simple"  requiredLabel="true" action="Addcourse" onSubmit="return chkfrm(this)" >  
              <s:label value="Course Name:"></s:label> <s:textfield  type="text" cssClass="form-control" placeholder="Enter Course Name" name="coursename" maxLength="50" minLength="1" requiredLabel="true"></s:textfield>
              <s:label value="Duration:"></s:label> <s:textfield  type="text" cssClass="form-control" placeholder="Enter Duration in weeks" name="duration" maxLength="4" minLength="1" requiredLabel="true" pattern="[1-9]+$"></s:textfield>
              <s:label value="Course Description:"></s:label> <s:textfield type="text" cssClass="form-control" placeholder="Enter Course Description" name="coursedescription" requiredLabel="true" minLength="1"></s:textfield></br>
              <p align="center"><s:submit action="Addcourse" value="Save and Next" cssClass="btn btn-primary" ></s:submit>  
               <s:reset cssClass="btn btn-danger"></s:reset> 
              <s:submit value="Cancel" cssClass="btn btn-warning" action="RedirecttoManagecourse"></s:submit>
    </s:form> </div> 
    </div>
<div class="col-md-4"></div>
</div>
<jsp:include page="masterpage_assistants/footer.jsp"></jsp:include>