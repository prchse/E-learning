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
                else if(form.answer.selected=="select")
	{
		alert("Plz enter the answer");
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
         <s:if test="%{#session.queadded=='yes'}">
           
             
					<script>
                                            alert("this question has been added sucesfully to this quiz!"); 
                                             <% 
                                                
                session.setAttribute("queadded",null);
                                        %>
                                            
                                          
                                              
                                        </script>
                                       
                                        <s:set var="queadded" scope="session" value="null"></s:set>
                                        
                                       
                                          
</s:if>  
        <div class="jumbotron">
            <h2 align="center"> <strong><span class="label label-primary">Add Questions to The Quiz</span></strong></h2></br>
           <s:form action="question" theme="simple" onSubmit="return chkfrm(this)">  
               <s:label value="Question Name:"></s:label><s:textfield type="text" cssClass="form-control" placeholder="Enter The Question" name="questionname" maxLength="50" minlength="1" requiredLabel="true"></s:textfield>
               <s:label value="OptionA:"></s:label><s:textfield type="text" cssClass="form-control" placeholder="Enter OptionA" name="optionA" maxLength="50" minlength="1" requiredLabel="true"></s:textfield>
               <s:label value="OptionB:"></s:label><s:textfield type="text" cssClass="form-control" placeholder="Enter OptionB" name="optionB" label="OptionB" maxLength="50" minlength="1" requiredLabel="true"></s:textfield>
               <s:label value="OptionC:"></s:label><s:textfield type="text" cssClass="form-control" placeholder="Enter OptionC" name="optionC" maxLength="50" minlength="1" requiredLabel="true"></s:textfield>
               <s:label value="OptionD:"></s:label><s:textfield type="text" cssClass="form-control" placeholder="Enter OptionD" name="optionD" maxLength="50" minlength="1" requiredLabel="true"></s:textfield></br>
               <s:label value="Marks:"></s:label><s:textfield type="text" cssClass="form-control" placeholder="Enter Marks for This Question" name="marks" maxLength="1" minlength="1" requiredLabel="true" pattern="[1-9]{1}" ></s:textfield></br>
               <s:label value="Answer:"></s:label><select name="answer" list="Answer">
                <option value="b1" selected="selected">Select Answer</option>
                 <option value="A">A</option>
                 <option value="B">B</option>
                 <option value="C">C</option>
                 <option value="D">D</option>
               </select></br></br>
          <p align="center"><s:submit value="Add new Question" action="addque" cssClass="btn btn-primary"></s:submit>
          <s:submit value="Done" action="quedone" cssClass="btn btn-success"></s:submit>
          <s:submit value="Cancel" cssClass="btn btn-warning"></s:submit></p>    
           </s:form></div>
    </div>
<div class="col-md-4"></div>
</div>
<jsp:include page="masterpage_assistants/footer.jsp"></jsp:include>
    