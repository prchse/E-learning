<%@ taglib uri="/struts-tags" prefix="s" %>  
<jsp:include page="masterpage_assistants/header_adminLoggedin.jsp"></jsp:include>
<div class="row">
    <div class="col-md-4"></div>
    <div class="col-md-4"> 
        <script type="text/javascript">
            function chkfrm(form)
{
	if(form.questionname.value=="")
	{
		alert("Plz enter the question name");
		form.questionname.focus();
		return false;
	}
	else if(form.optionA.value=="")
	{
		alert("Plz enter the optionA");
	        form.optionA.focus();
		return false;
		}
                else if(form.optionB.value=="")
	{
		alert("Plz enter the optionB");
	        form.optionB.focus();
		return false;
		}
                else if(form.optionC.value=="")
	{
		alert("Plz enter the optionC");
	        form.optionC.focus();
		return false;
		}
                else if(form.optionD.value=="")
	{
		alert("Plz enter the optionD");
	        form.optionD.focus();
		return false;
		}
                else if(form.answer.value=="")
	{
		alert("Plz enter the answer");
	        form.answer.focus();
		return false;
		}
                else if(form.answer.value=="")
	{
		alert("Plz enter the marks");
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
        
        <h3 align="center"><span class="label label-primary"> Add Questions to The Quiz</span></h3>
           <s:form theme="simple" onSubmit="return chkfrm(this)">  
               <s:iterator value="Quizlist">
                   
                   <s:textfield cssClass="form-control" type="text" name="questionname" value= "%{questionname}" requiredLabel="true" minLength="1" maxLength="50"></s:textfield>
                   <s:textfield cssClass="form-control" placeholder="Enter OptionA" name="optionA" value="%{optionA}" requiredLabel="true"></s:textfield>
                   <s:textfield cssClass="form-control" placeholder="Enter OptionB" name="optionB" value="%{optionB}" requiredLabel="true"></s:textfield>
                   <s:textfield cssClass="form-control" placeholder="Enter OptionC" name="optionC" value="%{optionC}" requiredLabel="true"></s:textfield>
           <s:textfield cssClass="form-control" placeholder="Enter OptionD" name="optionD" value="%{optionD}" requiredLabel="true"></s:textfield>
           <s:textfield cssClass="form-control" placeholder="Enter Answer" name="answer" value="%{answer}" requiredLabel="true"></s:textfield>
            <s:textfield cssClass="form-control" placeholder="Enter Marks" name="answer" value="%{marks}" requiredLabel="true"></s:textfield>
               </s:iterator>
                   <p align="center"><s:submit value="Add new Question" action="addque" cssClass="btn btn-primary"></s:submit>
          <s:submit value="Save" action="editQuestionSave" cssClass="btn btn-success"></s:submit>
          <s:submit value="Cancel" cssClass="btn btn-warning"></s:submit> </p>   
          </s:form>
    </div>
<div class="col-md-4"></div>
</div>
<jsp:include page="masterpage_assistants/footer.jsp"></jsp:include>
    