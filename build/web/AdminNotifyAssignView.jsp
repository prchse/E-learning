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
                           
                         
                           
               
                
            </tr></thead>
                    
<s:iterator value="topiclist"> 
        
        <tr style="width: 50%">
               <td><s:property value="title"/></td>
               <td><s:property value="assignementname"/></td>
               
               <td><a href="<s:property value="assignmentContent"/>"  ><s:property value="assignmentContent"/></a></td>
                <td><s:property value="assignsubdate"/></td>
              
                  
         
                
                
        </tr>
</s:iterator>
       
                   </table>
               <s:label value="Change Status:"></s:label><select name="verified" list="verified">
                <option value="b1" selected="selected">Pending</option>
                 <option value="verified">Verified</option>
                 <option value="rejected">Rejected</option>
               </select></br>
              <s:label value="Remark:"></s:label> <s:textarea  type="text" cssClass="form-control" placeholder="Enter Remarks" name="remarks" maxLength="50" minLength="1" requiredLabel="true"></s:textarea>
               <s:submit value="Submit" cssClass="btn btn-success" action="ChangeStatusAssign"></s:submit>
               
 </s:form>
        </div>
        </div>
<div class="col-md-2"></div>
<jsp:include page="masterpage_assistants/footer.jsp"></jsp:include>