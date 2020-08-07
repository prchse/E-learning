
       
   
<%@taglib uri="/struts-tags" prefix="s" %>
<jsp:include page="masterpage_assistants/header_adminLoggedin.jsp"></jsp:include>
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
        <h3 align="center"><strong><span class="label label-primary">List of all topic of this course </span></strong></h3>
              
              <div class="jumbotron">
                  <table style="width: 100%" class="jumbotron" align="center">
                      <thead>
                   <tr>
                
                       <th><strong>Topic Name</strong></th>
                       <th><strong>Topic Description</strong></th>
                       <th><strong>Completion Status</strong></th>
                       <th><strong>Topic Completion Date</strong></th>
                
                
            
                       <th><strong>Status of quiz</strong></th>
                       <th><strong>Status of Assignment</strong></th>
                
                       
                
              
                
                   </tr></thead>
<s:iterator value="topiclist"> 
        
        <tr style="width: 50%">
               
               
                <td> <s:property value="topicname"/></td>
                <td> <s:property value="topicdescription"/></td>
                <td> <s:property value="topiccompleted"/></td>
                <td> <s:property value="topicCompleteDate"/></td>
                <td> <a href='ViewStudentAssignment?assignidq=<s:property value="assignId"/>'>Assignment</td>
                  <td> <a href='ViewStudentQuiz?quizidq=<s:property value="quizId"/>'>Quiz</td>
               
                   
               
        </tr>
    </s:iterator>
                 </table>
                  </div>
        </div>
<div class="col-md-1"></div>
</div>
<jsp:include page="masterpage_assistants/footer.jsp"></jsp:include>



