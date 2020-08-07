

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
       <s:if test="%{#session.check_topic=='notopic'}">
           
					<script>
                                            alert("No Topic has been added to this Course!");
                                            window.location="Admin.jsp";
                                              
                                        
                                        </script>
                                       
                                        <s:set var="check_topic" scope="session" value="null"></s:set>
                                       
                                         
		
</s:if>
                                   
     
    
                                        <h3 align="center"><strong><span class="label label-primary">Course of This Students</span></strong></h3>
        <div class="jumbotron">
                  <table style="width: 90%" class="jumbotron" align="center">
                    <thead>
                     <tr>
                         <th><strong>Course Name</strong></th>
                         <th><strong>Registration Date</strong></th>
                         <th><strong>Duration(in weeks)</strong></th>
                         <th><strong>Completion Time</strong></th>
                         <th><strong>DeadLine</strong></th>
                         <th><strong>Days Remaining</strong></th>
                         <th><strong>Topics</strong></th>
                           
               
                
            </tr></thead>
<s:iterator value="course_list"> 
        
        <tr style="width: 50%">
               
                <td> <s:property value="course_name"/></td>
                <td> <s:property value="registration_time"/></td>    
                <td><s:property value="duration"/></td>
                <td><s:property value="course_completion_time"/></td>  
                <td><s:property value="deadline"/></td>
                <td><s:property value="time_remaining"/></td>
                 <td> <a href='ViewTopicStudent?cid=<s:property value="courseid"/>'>View Topic</td>
                
        </tr>
</s:iterator>
                   </table>
        </div>
        </div>
<div class="col-md-2"></div>
<jsp:include page="masterpage_assistants/footer.jsp"></jsp:include>

                            
    
