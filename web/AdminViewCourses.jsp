
        
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
        <s:if test="%{#session.notopic=='yes'}">
           
             
					<script>
                                            alert("no topic is added to this course!"); 
                                             <% 
                                                
                session.setAttribute("notopic",null);
                                        %>
                                           location="Viewcourse";
                                              
                                        </script>
                                       
                                        <s:set var="notopic" scope="session" value="null"></s:set>
                                        
                                       
                                          
</s:if>  
                                         <s:if test="%{#session.changed=='yes'}">
           
             
					<script>
                                            alert("course uploaded status has been changed!"); 
                                             <% 
                                                
                session.setAttribute("changed",null);
                session.removeAttribute("changed");
                                        %>
                                           location="Viewcourse";
                                              
                                        </script>
                                       
                                        <s:set var="changed" scope="session" value="null"></s:set>
                                        
                                       
                                          
</s:if>  
                                        <s:if test="%{#session.assign_quiz=='no'}">
           
             
					<script>
                                            alert("no assignment/quiz are added to the topics of the course so uploading is not possible!"); 
                                             <% 
                                                
                session.setAttribute("assign_quiz",null);
                session.removeAttribute("assign_quiz");
                                        %>
                                           location="Viewcourse";
                                              
                                        </script>
                                       
                                        <s:set var="assign_quiz" scope="session" value="null"></s:set>
                                        
                                       
                                          
</s:if>  
                                         <s:if test="%{#session.content=='no'}">
           
             
					<script>
                                            alert("no assignment/quiz are added to the topics of the course so uploading is not possible!"); 
                                             <% 
                                                
                session.setAttribute("content",null);
                session.removeAttribute("content");
                                        %>
                                           location="Viewcourse";
                                              
                                        </script>
                                       
                                        <s:set var="content" scope="session" value="null"></s:set>
                                        
                                       
                                          
</s:if>  
                          <s:if test="%{#session.both=='no'}">
           
             
					<script>
                                            alert("assignment/quiz and contents both are unavailable"); 
                                             <% 
                                                
                session.setAttribute("both",null);
                session.removeAttribute("both");
                                        %>
                                           location="Viewcourse";
                                              
                                        </script>
                                       
                                        <s:set var="both" scope="session" value="null"></s:set>
                                        
                                       
                                        
                                        
                                          
</s:if>                 
                                        
        <h3 align="center"><span class="label label-primary">Records of All Courses</span></h3>
                 <div class="jumbotron">
                     <table style="width: 90%" class="table table-bordered" align="center">
                    <thead>
            <tr>
             
                <th style="color:midnightblue"><strong>Course Name</strong></th>
                <th style="color:midnightblue"><strong>Course Creation Date</strong></th>
                <th style="color:midnightblue"><strong>Duration(in weeks)</strong></th>
                <th style="color:midnightblue"><strong>Description</strong></th>
                 
                  <th style="color:midnightblue"><strong>Uploaded</strong></th>
                  <th style="color:midnightblue"><strong>Topic</strong></th>
            </tr></thead>
<s:iterator value="list"> 
        
        <tr style="width: 50%">
            
                <td> <s:property value="coursename"/></td>
                <td> <s:property value="Creationdate"/></td>
                <td> <s:property value="description"/></td>
                <td> <s:property value="duration(in weeks)"/></td>
                <s:if test="%{uploaded=='no'}">
                <td> <a href='ChangeUpload?id=<s:property value="courseid"/>' class="btn btn-danger" role="button"><s:property value="uploaded"/></a></td>
                </s:if>
                <s:else>
                <td> <a href='ChangeUpload?id=<s:property value="courseid"/>' class="btn btn-success" role="button"><s:property value="uploaded"/></a></td>
                </s:else>
                <td> <a href='ViewTopic?id=<s:property value="courseid" />' role="button" class="btn btn-info">View Topic</td>
               
        </tr>
    </s:iterator>
                 </table>
                 </div>
        </div>
<div class="col-md-2"></div>
</div>
<jsp:include page="masterpage_assistants/footer.jsp"></jsp:include>

