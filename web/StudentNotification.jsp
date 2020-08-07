
<%@taglib uri="/struts-tags" prefix="s" %>
<jsp:include page="masterpage_assistants/header_loggedUser.jsp"></jsp:include>
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
                                           window.location="Home.jsp";
                                              
                                        </script>
                                      
                                                                               
</s:if>  
                                        
                                      
                                        
                                     
 <h3 align="center"><strong><span class="label label-primary">Notifications</span> </strong></h3>
              
              <div class="jumbotron">
                  <table style="width: 100%" class="table table-bordered" align="center">
                      <thead>
                   <tr>
                
                       <th style="color:midnightblue"><strong>Content</strong></th>
                       <th style="color:midnightblue"><strong>Sending Time</strong></th>
                     
                       <th style="color:midnightblue"><strong>Assignment</strong></th>
                
                   </tr></thead>
<s:iterator value="NotificationList"> 
        
        <tr style="width: 50%">
               
            
                <td > <s:property value="content"/></td>
                <td> <s:property value="sendingTime"/></td>
               
                 
                <td> <a href='NotifyAssignStud?assignid=<s:property value="assignId"/>&notid=<s:property value="notificationId"/>&studid=<s:property value="studentId"/>'>Assignment</a></td>
                
                  
                
              
        </tr>
    </s:iterator>
                 </table>
                   
                  </div>
        </div>
<div class="col-md-1"></div>
</div>
<jsp:include page="masterpage_assistants/footer.jsp"></jsp:include>
