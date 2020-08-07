

        
    
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
         <s:if test="%{#session.check_course=='nocourse'}">
           
             
					<script>
                                            alert("no course has been enrolled by this user!"); 
                                           location="ManageStudents.action";
                                              
                                        </script>
                                        <% 
                                                
                session.setAttribute("check_course",null);
                                        %>
                                        <s:set var="check_course" scope="session" value="null"></s:set>
                                        
                                       
                                          
</s:if>
    <s:else>
         
        <h3 align="center"><strong><span class="label label-primary">Records Of All Students</span></strong></h3>
        <div class="jumbotron">
                  <table style="width:100%" class="table jumbotron" align="center">
                    <thead>
                     <tr>
                         <th style="color:midnightblue"><strong>Student Id</strong></th>
                         <th style="color:midnightblue"><strong>First Name</strong></th>
                         <th style="color:midnightblue"><strong>Last name</strong></th>
                         <th style="color:midnightblue"><strong>Contact No</strong></th>
                         <th style="color:midnightblue"><strong>Email Id</strong></th>
                         <th style="color:midnightblue"><strong>Status</strong> </th>
                         <th style="color:midnightblue"><strong>Track the Logs</strong></th>
                         <th style="color:midnightblue"><strong>Course Wise Tracking</strong></th>
                         <th style="color:midnightblue"><strong>Topic Wise Tracking</strong></th>
                
            </tr></thead>
<s:iterator value="list"> 
        
        <tr style="width: 50%">
                <td><a href='Studentdetail?id=<s:property value="studentid" />'><s:property value="studentid" /></a></td>
                <td> <s:property value="fname"/></td>
                <td> <s:property value="lname"/></td>    
                <td><s:property value="contactno"/></td>
                <td><s:property value="emailid"/></td>  
                <td><a href='ChangeStatus?id=<s:property value="studentid"/>'><s:property value="status"/></a></td>        
                <td><a href='StudentLog?id=<s:property value="studentid"/>'>Logs</a></td>
                <td><a href='StudentCourse?id=<s:property value="studentid"/>'>course</a></td>
                <td><a href='StudentTopic?id=<s:property value="studentid"/>'>Topic</td>
                
        </tr>
</s:iterator>
                   </table>
             <% 
                                        session.removeAttribute("check_course");
                                           %>
        </div>
        </div>
<div class="col-md-1"></div>
</div>
<jsp:include page="masterpage_assistants/footer.jsp"></jsp:include>
    </s:else>

