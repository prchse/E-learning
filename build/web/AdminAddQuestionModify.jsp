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
	if(form.questionname.value=="")
	      {
		alert("Plz enter question name");
		form.questionname.focus();
		return false;
	      }
	else if(form.optionA.value=="")
	        {
		alert("Plz enter optionA");
	         form.optionA.focus();
		return false;
		}
         else if(form.optionB.value=="")
	       {
		alert("Plz enter optionB");
	         form.optionB.focus();
		return false;
		}
                 else if(form.optionC.value=="")
	        {
		alert("Plz enter optionC");
	         form.optionC.focus();
		return false;
		}
                 else if(form.optionD.value=="")
	{
		alert("Plz enter optionD");
	         form.optionD.focus();
		return false;
		}
                 else if(form.answer.value=="")
	{
		alert("Plz enter answer");
	         form.answer.focus();
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
            <h3 align="center"><strong>Add Questions to the Quiz</strong></h3>
                <s:form action="question" theme="simple" method="post" onSubmit="return chkfrm1(this)">  
                    <s:label value="Question Name:"></s:label> <s:textfield type="text" cssClass="form-control" placeholder="Enter The Question" name="questionname" requiredLabel="true" maxLength="50" minLength="1"></s:textfield>
                     <s:label value="OptionA:"></s:label><s:textfield type="text" cssClass="form-control" placeholder="Enter OptionA" name="optionA" requiredLabel="true" maxLength="50" minLength="1"></s:textfield>
                      <s:label value="OptionB:"></s:label><s:textfield type="text" cssClass="form-control" placeholder="Enter OptionB" name="optionB" requiredLabel="true" maxLength="50" minLength="1"></s:textfield>
                         <s:label value="OptionC:"></s:label> <s:textfield type="text" cssClass="form-control" placeholder="Enter OptionC" name="optionC" requiredLabel="true" maxLength="50" minLength="1"></s:textfield>
                          <s:label value="OptionD:"></s:label><s:textfield type="text" cssClass="form-control" placeholder="Enter OptionD" name="optionD" requiredLabel="true" maxLength="50" minLength="1"></s:textfield>
                          <s:label value="Answer:"></s:label><s:textfield type="text" cssClass="form-control" placeholder="Enter Answer" name="answer" requiredLabel="true" maxLength="5" minLength="1"></s:textfield>
                          <s:label value="Marks:"></s:label><s:textfield cssClass="form-control" placeholder="Enter Marks" name="answer" value="%{answer}" requiredLabel="true" maxLength="5" minLength="1"></s:textfield></br>
                <p align="center"><s:submit value="Add new Question" action="addque" cssClass="btn btn-primary"></s:submit>
          <s:submit value="Done" action="quedone" cssClass="btn btn-success"></s:submit>
          <s:submit value="Cancel" cssClass="btn btn-warning"></s:submit></p>   
                </s:form></div>
    </div>
<div class="col-md-4"></div>
</div>
<jsp:include page="masterpage_assistants/footer.jsp"></jsp:include>
    