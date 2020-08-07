
       
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
        <s:if test="%{#session.noassignment=='yes'}">
           
             
					<script>
                                            alert("no assignment is added to this topic!!!!"); 
                                             <% 
                                                
                session.setAttribute("noassignment",null);
                                        %>
                                           location="ViewTopic";
                                              
                                        </script>
                                       
                                        <s:set var="noassignment" scope="session" value="null"></s:set>
                                        
                                       
                                          
</s:if>  
                                        <s:if test="%{#session.noquiz=='yes'}">
           
             
					<script>
                                            alert("no question or quiz has been added to this topic!!!!"); 
                                             <% 
                                                
                session.setAttribute("noquiz",null);
                                        %>
                                           location="ViewTopic";
                                              
                                        </script>
                                       
                                        <s:set var="noquiz" scope="session" value="null"></s:set>
                                        
                                       
                                          
</s:if>  
        <h3 align="center"<strong><span class="label label-primary">List of all topic of this course</span></strong> </h3>
              
              <div class="jumbotron">
                  <table style="width: 80%" class="table table-bordered" align="center">
                      <thead>
                   <tr>
                
                       <th style="color:midnightblue"><strong>Topic Name</strong></th>
                       <th style="color:midnightblue"><strong>Topic Creation Date</strong></th>
                       <th style="color:midnightblue"><strong>Description</strong></th>
                       <th style="color:midnightblue"><strong>View Assignment</strong></th>
                       <th style="color:midnightblue"><strong>View Quiz</strong></th>
                
                   </tr></thead>
<s:iterator value="topiclist"> 
        
        <tr style="width: 50%">
               
                <td> <s:property value="topicname"/></td>
                <td> <s:property value="creationdate"/></td>
                <td> <s:property value="topicdescription"/></td>
               <td> <a href='ViewAssignment?id=<s:property value="topicid"/>'>Assignment</td>
               <td> <a href='ViewQuiz?id=<s:property value="topicid"/>'>Quiz</td>
               
        </tr>
    </s:iterator>
                 </table>
                  </div>
        </div>
<div class="col-md-3"></div>
</div>
<jsp:include page="masterpage_assistants/footer.jsp"></jsp:include>



