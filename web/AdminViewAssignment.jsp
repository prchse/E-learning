
        
    
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
        <h3 align="center"><span class="label label-primary">List of all Assignment of this Course</span> </h3>
              
              <div class="jumbotron">
                  <table style="width: 80%" class="table table-bordered" align="center">
                      <thead>
                   <tr>
                
                       <th><strong>Assignment Name</strong></th>
                       <th><strong>Assignment Creation Date</strong></th>
                       <th><strong>Duration</strong></th>
                       <th><strong>Assignment Title</strong></th>
                       <th style="color:blue"><strong>Back</strong></th>
                
                   </tr></thead>
<s:iterator value="Assignmentlist"> 
        
        <tr style="width: 50%">
               
                <td> <s:property value="assignmentname"/></td>
                <td> <s:property value="assignmentcreationdate"/></td>
                <td> <s:property value="duration"/></td>
                <td> <s:property value="title"/></td>
                <td><a href="AdminManageCourse.jsp">Back</a></td>
               
        </tr>
    </s:iterator>
                 </table>
                  </div>
        </div>
    <div class="col-md-2"></div>
</div>
<jsp:include page="masterpage_assistants/footer.jsp"></jsp:include>



