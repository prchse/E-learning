
       
    
<%@taglib uri="/struts-tags" prefix="s" %>
<jsp:include page="masterpage_assistants/header.jsp"></jsp:include>
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
    <div class="col-md-3"></div>
    <div class="col-md-6">  
        <h3 align="center"><strong>Records of All Courses</strong></h3>
                 <div class="jumbotron">
                <table style="width: 50%" class="jumbotron" align="center">
                    <thead>
            <tr>
                <!--th>Course Id</th-->
                <th>Course Name</th>
                <th>Course Creation Date</th>
                <th>Duration</th>
                <th>Description</th>
                
            </tr></thead>
<s:iterator value="list"> 
        
        <tr style="width: 50%">
             
                <td> <s:property value="coursename"/></td>
                <td> <s:property value="Creationdate"/></td>
                <td> <s:property value="description"/></td>
                <td> <s:property value="duration"/></td>
                
        </tr>
    </s:iterator>
                 </table>
                 </div>
        </div>
<div class="col-md-3"></div>
</div>
<jsp:include page="masterpage_assistants/footer.jsp"></jsp:include>


