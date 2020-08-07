<%-- 
    Document   : AdminStudentQuiz
    Created on : 13 Apr, 2015, 2:52:49 AM
    Author     : PRAGYA
--%>

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
    <div class="col-md-2"></div>
    <div class="col-md-8"> 
          <h3 align="center"><strong><span class="label label-primary">Course of This Students</span></strong></h3>
        <div class="jumbotron">
                  <table style="width: 90%" class="jumbotron" align="center">
                    <thead>
                     <tr>
                         <th><strong>Quiz Name</strong></th>
                         <th><strong>Quiz Submission Date</strong></th>
                         <th><strong>Quiz Marks</strong></th>
                         
                           
               
                
            </tr></thead>
<s:iterator value="topiclist"> 
        
        <tr style="width: 50%">
               
                <td> <s:property value="quizname"/></td>
                <td> <s:property value="quizsubdate"/></td>    
                <td><s:property value="quizmarks"/></td>
                
                
        </tr>
</s:iterator>
                   </table>
        </div>
        </div>
<div class="col-md-2"></div>
<jsp:include page="masterpage_assistants/footer.jsp"></jsp:include>