  
<%@ taglib uri="/struts-tags" prefix="s" %>  

  <s:if test="%{#session.login == null}">

<% response.sendRedirect("login.jsp");%> 

</s:if>
Welcome to Profile, <s:property value="#session.name"/>  