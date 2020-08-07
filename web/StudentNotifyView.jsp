<%-- 
    Document   : AdminStudentQuiz
    Created on : 13 Apr, 2015, 2:52:49 AM
    Author     : PRAGYA
--%>

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
    <div class="col-md-2"></div>
    <div class="col-md-8"> 
          <h3 align="center"><strong><span class="label label-primary">Assignment Detail</span></strong></h3>
            <s:form theme="simple"  requiredLabel="true"  >  
        <div class="jumbotron">
                  <table style="width: 90%" class="jumbotron" align="center">
                    <thead>
                     <tr>
                         <th><strong>Assignment title</strong></th>
                         <th><strong>Assignment Question</strong></th>
                         <th><strong>Assignment Content</strong></th>
                          <th><strong>Submission Date</strong></th>
                          <th><strong>Verified Status</strong></th>
                          <th><strong>Remarks</strong></th>
                           
                         
                           
               
                
            </tr></thead>
                    
<s:iterator value="topiclist"> 
        
        <tr style="width: 50%">
               <td><s:property value="title"/></td>
               <td><s:property value="assignementname"/></td>
               
               <td><a href="<s:property value="assignmentContent"/>"  ><s:property value="assignmentContent"/></a></td>
                <td><s:property value="assignsubdate"/></td>
               <td><s:property value="verified"/></td>
                 <td><s:property value="remarks"/></td>
         
                
                
        </tr>
</s:iterator>
       
                   </table>
               
               
 </s:form>
        </div>
        </div>
<div class="col-md-2"></div>
<jsp:include page="masterpage_assistants/footer.jsp"></jsp:include>