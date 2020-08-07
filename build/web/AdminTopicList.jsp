
<%@taglib uri="/struts-tags" prefix="s" %>
<jsp:include page="masterpage_assistants/header_adminLoggedin.jsp"></jsp:include>
<head>
        <style>
            table, th, td {
    border: 1px solid black;
    border-collapse: collapse;
}
th, td {
    padding: 5px;
}
            </style>
    </head>
<div class="row">
  <div class="col-md-1"></div>
    <div class="col-md-10">  
         <s:if test="%{#session.topicdeleted=='yes'}">
           
             
					<script>
                                            alert("topic is deleted!"); 
                                             <% 
                                                
                session.setAttribute("topicdeleted",null);
                                        %>
                                           location="test";
                                              
                                        </script>
                                       
                                        <s:set var="topicdeleted" scope="session" value="null"></s:set>
                                        
                                       
         </s:if>                            
                                        <s:if test="%{#session.topicedited=='yes'}">
           
             
					<script>
                                            alert("topic details are sucessfully edited!"); 
                                             <% 
                                                
                session.setAttribute("topicedited",null);
                                        %>
                                            
                                           location="test";
                                              
                                        </script>
                                       
                                        <s:set var="topicedited" scope="session" value="null"></s:set>
                                        
                                       
                                          
</s:if>  
                                        <s:if test="%{#session.quizadded=='yes'}">
           
             
					<script>
                                            alert(" quiz has been added sucesfully!"); 
                                             <% 
                                                
                session.setAttribute("quizadded",null);
                                        %>
                                            
                                           location="test";
                                              
                                        </script>
                                       
                                        <s:set var="quizadded" scope="session" value="null"></s:set>
                                        
                                       
                                          
</s:if>  
           <s:if test="%{#session.checkQuiz=='abc'}">
           
             
					<script>
                                            alert("quiz has been added previously can modify the question!"); 
                                             <% 
                                                
                session.setAttribute("checkQuiz",null);
                                        %>
                                            
                                           location="test";
                                              
                                        </script>
                                       
                                        <s:set var="checkquiz" scope="session" value="null"></s:set>
                                        
                                       
                                          
</s:if>  
                                        <s:if test="%{#session.checkAssignment=='abc'}">
           
             
					<script>
                                            alert("assignment is already added!"); 
                                             <% 
                                                
                session.setAttribute("checkAssignment",null);
                                        %>
                                           location="test";
                                              
                                        </script>
                                       
                                        <s:set var="checkAssignment" scope="session" value="null"></s:set>
                                        
                                       
                                          
</s:if>  
                                        <s:if test="%{#session.assignmentadded=='yes'}">
           
             
					<script>
                                            alert("assignment is added successfully!"); 
                                             <% 
                                                
                session.setAttribute("assignmentadded",null);
                                        %>
                                           location="test";
                                              
                                        </script>
                                       
                                        <s:set var="assignmentadded" scope="session" value="null"></s:set>
                                        
                                       
                                          
</s:if>  
                                         
                                        <s:if test="%{#session.assign_quiz=='no'}">
           
             
					<script>
                                            alert("firstly add the assignment or quiz to the topics to uplpad the course!"); 
                                             <% 
                                                
                session.setAttribute("assign_quiz",null);
                session.removeAttribute("assign_quiz");
                                        %>
                                           location="test";
                                              
                                        </script>
                                       
                                        <s:set var="assign_quiz" scope="session" value="null"></s:set>
                                        
                                                        
                                          
</s:if>  
                                         <s:if test="%{#session.content=='no'}">
           
             
					<script>
                                            alert("firstly add the contents to the topics to uplpad the course!"); 
                                             <% 
                                                
                session.setAttribute("content",null);
                session.removeAttribute("content");
                                        %>
                                           location="test";
                                              
                                        </script>
                                       
                                        <s:set var="content" scope="session" value="null"></s:set>
                                        
                                                          
                                          
</s:if>  
                                        
  <s:if test="%{#session.both=='no'}">
           
             
					<script>
                                            alert("firstly add the contents and quiz/assignments to the topics to uplpad the course!"); 
                                             <% 
                                                
                session.setAttribute("both",null);
                session.removeAttribute("both");
                                        %>
                                           location="test";
                                              
                                        </script>
                                       
                                        <s:set var="both" scope="session" value="null"></s:set>
                                        
                                                          
                                          
</s:if> 
                                        <s:if test="%{#session.contentadded=='yes'}">
           
             
					<script>
                                            alert("contents are added successfully to the topic!"); 
                                             <% 
                                                
                session.setAttribute("contentadded",null);
                session.removeAttribute("contentadded");
                                        %>
                                           location="test";
                                              
                                        </script>
                                       
                                        <s:set var="contentadded" scope="session" value="null"></s:set>
                                        
                                                          
                                          
</s:if>                                         
         
                                 
    
             <h3 align="center"><strong><span class="label label-primary">List of all topic of this course</span> </strong></h3>
              <h5 align="center">Click on the buttons to do particular task  </h5>
              <div class="jumbotron">
                  <table style="width: 100%" class="table table-bordered" align="center">
                      <thead>
                   <tr>
                
                       <th style="color:midnightblue"><strong>Topic Name</strong></th>
                       <th style="color:midnightblue"><strong>Topic Creation Date</strong></th>
                       <th style="color:midnightblue"><strong>Description</strong></th>
                       <th style="color:midnightblue"><strong>Edit the Topic Details</strong></th>
                       <th style="color:midnightblue"><strong>Add Content</strong></th>
                       <th style="color:midnightblue"><strong>Add Quiz</strong> </th>
                       <th style="color:midnightblue"><strong>Add Assignment</strong></th>
                       <th style="color:midnightblue"><strong>Delete Topic</strong></th>
                
                   </tr></thead>
<s:iterator value="topiclist"> 
        
        <tr style="width: 50%">
               
                <td> <s:property value="topicname"/></td>
                <td> <s:property value="creationdate"/></td>
                <td> <s:property value="topicdescription"/></td>
                <td> <a href='manageTopicedit?id=<s:property value="topicid"/>'>EDIT</td>
                <td> <a href='AdminFileUpload.jsp?id=<s:property value="topicid"/>'>ADD CONTENT</td>
                <td> <a href='CheckQuiz?id=<s:property value="topicid"/>'>ADD QUIZ</td>
                <td> <a href='Checkassign?id=<s:property value="topicid"/>'>ADD ASSIGNMENT</td>
                <td> <a href='manageTopic?id=<s:property value="topicid"/>'>DELETE</td>
              
        </tr>
    </s:iterator>
                 </table>
                   <s:form>
                   
                        
                          <s:submit  value="Upload Course" cssClass="btn btn-primary" action="UploadCourse"></s:submit>
                          <a href="Admin.jsp" class="btn btn-info" role="button">Upload Later/Cancel</a>
               </s:form>
                  </div>
        </div>
<div class="col-md-1"></div>
</div>
<jsp:include page="masterpage_assistants/footer.jsp"></jsp:include>
