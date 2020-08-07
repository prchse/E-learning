
        
    

            <jsp:include page="masterpage_assistants/header_adminLoggedin.jsp"></jsp:include>
            <%@taglib uri="/struts-tags" prefix="s" %>
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
        <s:if test="%{#session.topicdeleted=='yes'}">
           
             
					<script>
                                            alert("topic is deleted!"); 
                                             <% 
                                                
                session.setAttribute("topicdeleted",null);
                                        %>
                                           location="ModifyCourseTopicList";
                                              
                                        </script>
                                       
                                        <s:set var="topicdeleted" scope="session" value="null"></s:set>
                                        
                                       
                                          
</s:if>  
                                        <s:if test="%{#session.topicadded=='yes'}">
           
             
					<script>
                                            alert("topic is added!"); 
                                             <% 
                                                
                session.setAttribute("topicadded",null);
                
                                        %>
                                           location="ModifyCourseTopicList";
                                              
                                        </script>
                                       
                                        <s:set var="topicadded" scope="session" value="null"></s:set>
                                        
                                       
                                          
</s:if>  
 
                                        <s:if test="%{#session.topicedited=='yes'}">
           
             
					<script>
                                            alert("topic is edited!"); 
                                             <% 
                                                
                session.setAttribute("topicedited",null);
                                        %>
                                           location="ModifyCourseTopicList";
                                              
                                        </script>
                                       
                                        <s:set var="topicedited" scope="session" value="null"></s:set>
                                        
                                       
                                          
</s:if>                     
    <% session.setAttribute("checkfromModify","yes");
    %><h3 align="center"><span class="label label-primary">List of all Topic of this Course</strong></h3>
              <h5 align="center">Click on the buttons to do particular task  </h5>
              <div class="jumbotron">
                  <table style="width: 100%" class="jumbotron" align="center">
                      <thead>
                   <tr>
                
                       <th style="color:midnightblue"><strong>Topic Name</strong></th>
                       <th style="color:midnightblue"><strong>Topic Creation Date</strong></th>
                       <th style="color:midnightblue"><strong>Description</strong></th>
                       <th style="color:midnightblue"><strong>Edit the Topic Details</strong></th>
                       <th style="color:midnightblue"><strong>Modify Content</strong></th>
                       <th style="color:midnightblue"><strong>Modify Quiz</strong> </th>
                       <th style="color:midnightblue"><strong>Modify Assignment</strong></th>
                       <th style="color:midnightblue"><strong>Modify Topic</strong></th>
                
                   </tr></thead>
<s:iterator value="topiclist"> 
        
        <tr style="width: 50%">
               
                <td> <s:property value="topicname"/></td>
                <td> <s:property value="creationdate"/></td>
                <td> <s:property value="topicdescription"/></td>
                <td> <a href='manageTopicedit?id=<s:property value="topicid"/>'>Edit</td>
                <td> <a href='AdminFileUpload.jsp?id=<s:property value="topicid"/>'>Modify Content</td>
                <td> <a href='AdminQuizMenu.jsp?id=<s:property value="topicid"/>'>Modify Quiz</td>
                <td> <a href='AdminAssignmentMenu.jsp?id=<s:property value="topicid"/>'>Modify Assignment</td>
                <td> <a href='DeleteTopicModify?id=<s:property value="topicid"/>'>DELETE</td>
        </tr>
    </s:iterator>
                 </table>
              </div>
              
                  <s:form>
              <s:submit value="AddNewTopic" cssClass="btn btn-success" align="center" action="redirectPage" ></s:submit> 
                  </s:form>
                  </div>
              
        </div>
<div class="col-md-1"></div>
</div>
<jsp:include page="masterpage_assistants/footer.jsp"></jsp:include>
</html>


