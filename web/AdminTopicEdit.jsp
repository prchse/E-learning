<%@ taglib uri="/struts-tags" prefix="s" %>  
<jsp:include page="masterpage_assistants/header_adminLoggedin.jsp"></jsp:include>
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
		alert("Plz enter the topic description");
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
        <div class="jumbotron">
            <p align="center"><strong><span class="label label-primary">Topic Details</span></strong></p> 
                    <s:form theme="simple" onSubmit="return chkfrm(this)">  
                   <s:iterator value="topicdetail">   

                       <s:textfield cssClass="form-control" placeholder="Enter Topic Name" name="topicname" value="%{topicname}" requiredLabel="true" maxLength="50" minLength="1"></s:textfield>
                       <s:textfield cssClass="form-control" placeholder="Enter Topic Description" name="topicdescription" value="%{topicdescription}" requiredLabel="true" minLength="1" maxLength="2000"></s:textfield></br>
     
                           <p align="center"><s:submit value="Save" action="topiceditsave" cssClass="btn btn-primary"></s:submit>  
                           <s:submit value="Cancel" action="topiceditcancel" cssClass="btn btn-warning"></s:submit></p>
                    </s:iterator>
    
                    </s:form></div>
    </div>
<div class="col-md-4"></div>
</div>
<jsp:include page="masterpage_assistants/footer.jsp"></jsp:include>