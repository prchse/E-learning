<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib  prefix="s" uri="/struts-tags" %>
<s:if test="%{#session.login == null}">

<% response.sendRedirect("login.jsp");%> 

</s:if>
<s:else>
    <jsp:include page="masterpage_assistants/header_loggedUser.jsp"></jsp:include>
    <body>
         <s:if test="%{#session.notification=='no'}">
           
             
        <script>
                                            
                                            alert("no new notification are there!"); 
                                              
                                              session.setAttribute("notification",null);
                                             <% 
                                                
                session.setAttribute("notification",null);
                session.removeAttribute("notification");
                                        %>
                                           
                                              
                                        </script>
                                       
                                        
                                        
                                                          
                                          
</s:if>  
        <div class="container-fluid">
        <div class="jumbotron">
            
            
            <p>
                Welcome to the E-learning 
            </p>
        </div>
        </div>
    </s:else>
    <jsp:include page="masterpage_assistants/footer.jsp"></jsp:include>
           
