<jsp:include page="masterpage_assistants/header_loggedUser.jsp"></jsp:include>
<s:if test="%{#session.login == null}">

<% response.sendRedirect("login.jsp");%> 

</s:if>
<div class="jumbotron">
    
    You are not enrolled yet.
</div>

<jsp:include page="masterpage_assistants/header.jsp"></jsp:include>