
<%@ taglib uri="/struts-tags" prefix="s" %> 
  
<jsp:include page="masterpage_assistants/header_adminLoggedin.jsp"></jsp:include>
      <script type="text/javascript" src="bootStrap/js/jquery-2.1.3.min.js"></script>
<div class="row">
    <div class="col-md-4"></div>
    <div class="col-md-4">  
  <script type="text/javascript">
            function chkfrm(form)
{
	if(form.quizname.value=="")
	{
		alert("Plz enter the quiz name");
		form.quizname.focus();
		return false;
	}
	
		else
		{
			
			return true;
		}
		return true;
	}
        </script>
        <h3 align="center"><strong><span class="label label-primary">Quiz Details</span></strong></h3> 
                    <s:form theme="simple"  onSubmit="return chkfrm(this)">  
                    
         
                        <s:label value="Quiz Name:"></s:label><s:textfield cssClass="form-control" placeholder="Enter Quiz Name" name="quizname" requiredLabel="true" maxLength="50" minLength="1"></s:textfield></br>
                
                                <p align="center"> <s:submit value="Save" action="QuizEditModify" cssClass="btn btn-primary" ></s:submit>
                            <s:submit value="Cancel" cssClass="btn btn-warning"></s:submit> </p>
                  
                 </s:form>
   
                                        
                     </html>
                    </div>
<div class="col-md-4"></div>
</div>
<jsp:include page="masterpage_assistants/footer.jsp"></jsp:include>
     