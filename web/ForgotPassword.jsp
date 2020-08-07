<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">

    <jsp:include page="masterpage_assistants/header.jsp"></jsp:include>
    <div class="row">
                <div class="col-md-4"></div>
                <div class="col-md-4">  
                    <div class="jumbotron">
    <em><h3 align="center"><strong>Enter Your Registered Email</strong></h3>
   </em>
   <!--form action="emailer" method="post">
   <label for="from">From</label><br/>
   <input type="text" name="from"/><br/>
   <label for="password">Password</label><br/>
   <input type="password" name="password"/><br/>
   <label for="to">To</label><br/>
   <input type="text" name="to"/><br/>
   <label for="subject">Subject</label><br/>
   <input type="text" name="subject"/><br/>
   <label for="body">Body</label><br/>
   <input type="text" name="body"/><br/>
   <input type="submit" value="Send Email"/>
   </form-->
    <s:form action="emailer" theme="simple" align="center">
         <s:textfield name="to" label="to"></s:textfield>
       
         
         <s:submit value="mail" cssClass="btn btn-success"></s:submit>
   </s:form>
                    </div>
</div>
            <div class="col-md-4"></div>
        </div>

        <jsp:include page="masterpage_assistants/footer.jsp"></jsp:include>