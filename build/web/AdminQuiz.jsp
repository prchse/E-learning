<%@page import="com.elearning.model.DBConnect"%>
<%@page import="java.sql.PreparedStatement"%>
<%@ taglib uri="/struts-tags" prefix="s" %> 
   <html>
       <head>
              <script type="text/javascript" src="bootStrap/js/jquery-2.1.3.min.js"></script>
       </head>
       <jsp:include page="masterpage_assistants/header_adminLoggedin.jsp"></jsp:include>
     
     
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
  
                  <div class="jumbotron">
            <h3 align="center"><strong><span class="label label-primary">Quiz Details</span></strong></h3> 
                    <s:form theme="simple" onSubmit="return chkfrm(this)">  
                         <%
      //  String id=request.getQueryString();
                             
                         /* if(request.getQueryString() != null)
                                out.println(request.getQueryString());
                                  session.setAttribute("topicidq",request.getQueryString());*/
                            
                             
                          %>
                          <s:if test="%{#session.checkQuiz=='abc'}">
		
					<script>
                                            alert("quiz already exist can modify the quiz!");
                                            <%
                                                session.setAttribute("checkQuiz",null);
                                                %>
                                            location="test";
                                        
                                        </script>
                                        <s:set var="checkQuiz" scope="session" value="null"></s:set>
		
</s:if>

                    <s:else >
         
                <s:label value="Quiz Name:"></s:label><s:textfield type="text" cssClass="form-control" placeholder="Enter Quiz Name" name="quizname" requiredLabel="true" maxLength="50" minLength="1"></s:textfield>
                        </br> 
                        <p align="center"><s:submit value="Save and Next" action="addquiz" cssClass="btn btn-primary" ></s:submit>
                     <s:submit value="Cancel" cssClass="btn btn-warning"></s:submit> </p>
                 </s:else>
                        </s:form></div>
                 
   
                                        
                   
                    </div>
<div class="col-md-4"></div>
</div>
             
                          
<jsp:include page="masterpage_assistants/footer.jsp"></jsp:include>
     