<%@taglib  prefix="s" uri="/struts-tags" %>
<jsp:include page="masterpage_assistants/header_loggedUser.jsp" ></jsp:include>
<%
  
%>
<div class="jumbotron">
    <table> 
        <tr><td><s:label value="Total Marks:"></s:label><s:property value="marks"/><br></td></tr>>
        <tr><td><s:label value="Incorrect Questions:"></s:label><s:property value="marksIncorrect"/><br></td></tr>
        <tr><td><s:label value="Total Questions"></s:label><% out.println(session.getAttribute("maxQue"));%></td></tr><br>

            <tr><td> <a href='ViewQuizAnswer?id=<s:property value="quizid" />' role="button" class="btn btn-info">View Answer</a></td></tr>

</table>
</div>
<jsp:include page="masterpage_assistants/footer.jsp" ></jsp:include>