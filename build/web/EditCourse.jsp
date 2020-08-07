<%@ taglib uri="/struts-tags" prefix="s" %> 
  <jsp:include page="masterpage_assistants/header.jsp"></jsp:include>
<div class="row">
    <div class="col-md-4"></div>
    <div class="col-md-4"> 
        <script type="text/javascript">
            function chkfrm(form)
{
	if(form.coursename.value=="")
	{
		alert("Plz enter the course name");
		form.coursename.focus();
		return false;
	}
	if else(form.duration.value=="")
	{
		alert("Plz enter the duration");
		form.duration.focus();
		return false;
	}
        if else(form.description.value=="")
	{
		alert("Plz enter the description");
		form.description.focus();
		return false;
	}
        if else(form.coursecreation.value=="")
	{
		alert("Plz enter course creation");
		form.coursecreation.focus();
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
            <h3 align="center"><strong> Enter Course Details/span></strong></h3> 
                <s:form action="Addcourse" theme="simple" onSubmit="return chkfrm(this)">  
                  <s:iterator value="course_detail">
                      <s:textfield type="text" name="coursename" cssClass="form-control" placeholder="Enter course Name" value="%{coursename}" requiredLabel="true" minLength="1" maxLength="50"> </s:textfield>
                      <s:textfield type="text" name="duration" cssClass="form-control" placeholder="Enter Duration" value="%{duration}" requiredLabel="true" minLength="1" maxLength="50"></s:textfield>
                      <s:textfield type="text" name="description" cssClass="form-control" placeholder="Enter Course Description" value="%{description}" requiredLabel="true" maxLength="4000" minLength="1"></s:textfield>
                   <s:textfield name="coursecreation" cssClass="form-control" placeholder="Enter Course Creation Date" value="%{Creationdate}" disabled="true"></s:textfield>
 
                       <p align="center"> <s:submit value="Save" cssClass="btn btn-primary" action="EditcourseSave"></s:submit>  
                       <s:submit value="Cancel" cssClass="btn btn-info"></s:submit></p>
  </s:iterator>
    
</s:form>
              </div>
        </div>
<div class="col-md-4"></div>
</div>
<jsp:include page="masterpage_assistants/footer.jsp"></jsp:include>