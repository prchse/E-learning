

    
    
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
                         <th style="color:midnightblue"><strong>Course Name</strong></th>
                         <th style="color:midnightblue"><strong>Registration Date</strong></th>
                         <th style="color:midnightblue"><strong>Duration</strong></th>
                         <th style="color:midnightblue"><strong>Completion Time</strong></th>
                         <th style="color:midnightblue"><strong>DeadLine</strong></th>
                         <th style="color:midnightblue"><strong>Days Remaining</strong></th>
               
                
            </tr></thead>
<s:iterator value="course_list"> 
        
        <tr style="width: 50%">
               
                <td> <s:property value="course_name"/></td>
                <td> <s:property value="registration_time"/></td>    
                <td><s:property value="duration"/></td>
                <td><s:property value="course_completion_time"/></td>  
                <td><s:property value="deadline"/></td>
                <td><s:property value="time_remaining"/></td>
                
        </tr>
</s:iterator>
             </table>
        </div>
        </div>
<div class="col-md-2"></div>
</div>
<jsp:include page="masterpage_assistants/footer.jsp"></jsp:include>



