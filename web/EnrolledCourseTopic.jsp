
       
   
<%@taglib uri="/struts-tags" prefix="s" %>
<jsp:include page="masterpage_assistants/header_loggedUser.jsp"></jsp:include>
 <style>
            table, th, td {
    border: 1px solid black;
    border-collapse: collapse;
}
th, td {
    padding: 5px;
}
            </style>
<div class="row">
    <div class="col-md-1"></div>
    <div class="col-md-10">  
        <s:if test="%{#session.assignUpload=='yes'}">
           
             
					<script>
                                            alert("assignment is uploaded will be verified and you will be notified !"); 
                                             <% 
                                                
                session.setAttribute("assignUpload",null);
                session.removeAttribute("assignUpload");
                                        %>
                                            location="loginsuccess.jsp";
                                          
                                              
                                        </script>
                                       
                                       
                                        
                                       
                                          
</s:if>  
                                        <s:if test="%{#session.exist=='yes'}">
           
             
					<script>
                                            alert("assignment is attempted and is verified can not attempt again !"); 
                                             <% 
                                                
                session.setAttribute("exist",null);
                session.removeAttribute("exist");
                                        %>
                                            location="loginsuccess.jsp";
                                          
                                              
                                        </script>
                                       
                                       
                                        
                                       
                                          
</s:if>  
                                         <s:if test="%{#session.quiz_exist=='yes'}">
           
             
					<script>
                                            alert("quiz is attempted can not attempt again !"); 
                                             <% 
                                                
                session.setAttribute("quiz_exist",null);
                session.removeAttribute("quiz_exist");
                                        %>
                                            location="loginsuccess.jsp";
                                          
                                              
                                        </script>
                                       
                                       
                                        
                                       
                                          
</s:if>  
                                        
                                        
        <h3 align="center"><strong><span class="label label-primary">List of all topic of this course </span></strong></h3>
              
              <div class="jumbotron">
                  <table style="width: 100%" class="table table-bordered" align="center">
                      <thead>
                   <tr>
                
                       <th><strong>Topic Name</strong></th>
                       <th><strong>Topic Description</strong></th>
                       <th><strong>View Contents</strong></th>
                       <th><strong>Assignment</strong></th>
                       <th><strong>Quiz</strong></th>
                
                
            
                
              
                
                   </tr></thead>
<s:iterator value="topiclist"> 
        
        <tr style="width: 50%">
               
               
                <td> <s:property value="topicname"/></td>
                <td><s:property value="topicdescription"/></td>
              <td> <a class="btn btn-primary" href='ViewTopicContents?id=<s:property value="topicid"/>'>View Contents</a></td>
               <td> <a class="btn btn-primary" href='CheckAssignmentAttempt?id=<s:property value="topicid"/>'>Assignment</a></td>
               <td> <a class="btn btn-primary" href='QuizRetrieve?id=<s:property value="topicid"/>'> Quiz</a></td>
               
                
                   
               
        </tr>
    </s:iterator>
                 </table>
                  </div>
        </div>
<div class="col-md-1"></div>
</div>
<jsp:include page="masterpage_assistants/footer.jsp"></jsp:include>



