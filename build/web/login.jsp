<%@ taglib uri="/struts-tags" prefix="s" %>  
<!DOCTYPE html>

   
    
        <jsp:include page="masterpage_assistants/header.jsp"></jsp:include>
             <head>
        
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="css/bootstrap.min.css">

        <!-- Optional theme -->
        <link rel="stylesheet" href="css/bootstrap-theme.min.css">

        <!-- Latest compiled and minified JavaScript -->
        <script src="js/bootstrap.min.js"></script>

    </head>
<s:if test="%{#session.deactivated=='yes'}">
           
             
					<script>
                                            alert("your account is not activated till now..under the review of E-learning team"); 
                                             <% 
                                                
                session.setAttribute("deactivated",null);
                                        %>
                                            
                                          
                                              
                                        </script>
                                       
                                        <s:set var="deactivated" scope="session" value="null"></s:set>
                                        
                                       
                                          
</s:if>  
        <s:else>
        
            <!--center-->
            <div class="row">
                <div class="col-md-4">
                </div>

                <div class="col-md-4">
                    <script type="text/javascript">
function chkfrm1(form)
{
	if(form.username.value=="")
	{
		alert("Plz enter the email");
		form.username.focus();
		return false;
	}
	else if(form.password.value=="")
	{
		alert("Plz enter the password");
	    form.password.focus();
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
    <h3 align="center"><strong>Login</strong></h3>
                      
                    
                    <s:form theme="simple" action="loginprocess" method="post" onSubmit="return chkfrm1(this)">  
                        <s:label value="User Name:"></s:label><s:textfield type="text" cssClass="form-control" name="username"  placeholder="Email Address" requiredLabel="true" maxLength="50" minLength="1"></s:textfield> </br> 
                            
                        <s:label value="Password:"></s:label><s:password type="password" cssClass="form-control" name="password" placeholder="Password" requiredLabel="true" maxLength="50" minLength="1"></s:password> </br> 
                            
                        <s:a href="forgotpass" >Forgot your password?</s:a>
                        <s:submit cssClass="btn btn-primary" value="login"></s:submit>
                        <s:reset cssClass="btn btn-danger" ></s:reset>
                    </s:form> 
                    
    </div>
            </div>
            <div class="col-md-4">
            </div>    
        </div>
        <!--/center-->
        <jsp:include page="masterpage_assistants/footer.jsp"></jsp:include>
        </s:else>>

    </body>
</html>