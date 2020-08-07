<
<%@taglib uri="/struts-tags" prefix="s" %>
<jsp:include page="masterpage_assistants/header_adminLoggedin.jsp"></jsp:include>
<head>
        <style>
            table, th, td {
    border: 1px solid black;
    border-collapse: collapse;
}
th, td {
    padding: 5px;
}
            </style>
    </head>
<div class="row">
  <div class="col-md-1"></div>
    <div class="col-md-10">  
         <s:if test="%{#session.notification=='no'}">
           
             
					<script>
                                            alert("no new notifications are there!"); 
                                             <% 
                                                
                session.removeAttribute("notification");
                                        %>
                                           window.location="Admin.jsp";
                                              
                                        </script>
                                      
                                                                               
</s:if>  
                                        
                                        <s:if test="%{#session.checkstaus=='checked'}">
           
             
        <script>
                                            session.removeAttribute("checkstaus");
                                            alert("now this student is activated!"); 
                                              
                                              session.setAttribute("checkstaus",null);
                                              location="NotificationAdmin.action";
                                              
                                             <% 
                                                
                session.setAttribute("checkstaus",null);
                session.removeAttribute("checkstaus");
                                        %>
                                           
                                              
                                        </script>
                                       
                                       
                                        
                                                          
                                          
</s:if>   
                                         <s:if test="%{#session.changed=='yes'}">
           
             
        <script>
                                          //  session.removeAttribute("changed");
                                            alert("now assignment status is changed!"); 
                                              
                                              session.setAttribute("changed",null);
                                               location="NotificationAdmin.action";
                                             <% 
                                                
                session.setAttribute("changed",null);
                session.removeAttribute("changed");
                                        %>
                                           
                                              
                                        </script>
                                       
                                        <s:set var="changed" scope="session" value="null"></s:set>
                                        
                                                          
                                          
</s:if> 
 <h3 align="center"><strong><span class="label label-primary">Notifications</span> </strong></h3>
              
              <div class="jumbotron">
                  <table style="width: 100%" class="table table-bordered" align="center">
                      <thead>
                   <tr>
                
                       <th style="color:midnightblue"><strong>Content</strong></th>
                       <th style="color:midnightblue"><strong>Sending Time</strong></th>
                       <th style="color:midnightblue"><strong>Student Id</strong></th>
                       <th style="color:midnightblue"><strong>Assignment</strong></th>
                
                   </tr></thead>
<s:iterator value="NotificationList"> 
        
        <tr style="width: 50%">
               
            
                <td > <s:property value="content"/></td>
                <td> <s:property value="sendingTime"/></td>
                <s:if test="%{assignId!=null}">
                 <td > <s:property value="studentId"/></td>   
                <td> <a href='NotifyAssign?assignid=<s:property value="assignId"/>&notid=<s:property value="notificationId"/>&studid=<s:property value="studentId"/>'>Assignment</a></td>
                </s:if>
                <s:else>
                    <td> <a href='NotifyChangeUser?id=<s:property value="studentId"/>&notid=<s:property value="notificationId"/>'><s:property value="studentId"/></a></td>
                <td>N/A</td>
                </s:else>
              
        </tr>
    </s:iterator>
                 </table>
                   
                  </div>
        </div>
<div class="col-md-1"></div>
</div>
<jsp:include page="masterpage_assistants/footer.jsp"></jsp:include>
