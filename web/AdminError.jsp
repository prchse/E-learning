<jsp:include page="masterpage_assistants/header_adminLoggedin.jsp"></jsp:include>
<%@taglib prefix="s" uri="/struts-tags" %>
<s:if test="%{#session.login == null}">

<% response.sendRedirect("login.jsp");%> 

</s:if>
<div class="row">
    <div class="col-md-4"></div>
    <div class="col-md-4"> 
              <h4 align="center">Your Operation is Unsuccessful</h4></div>
    <div class="col-md-4"></div>
    <div class="col-md-4"></div>
</div>
<jsp:include page="masterpage_assistants/footer.jsp"></jsp:include>
    