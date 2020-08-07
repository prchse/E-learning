
<%@taglib uri="/struts-tags" prefix="s" %>
<jsp:include page="masterpage_assistants/header_adminLoggedin.jsp"></jsp:include>
<s:if test="%{#session.check_status=='success'}">
           
             
					<script>
                                            alert("question is modified successfully!"); 
                                           location="ModifyQuestionView.action";
                                              
                                        </script>
                                        <% 
                                                
                session.setAttribute("check_status",null);
                                        %>
                                        <s:set var="check_status" scope="session" value="null"></s:set>
                                        
                                       
                                          
</s:if>
                                        <s:if test="%{#session.check_status_del=='success'}">
           
             
					<script>
                                            alert("question is deleted successfully!"); 
                                           location="ModifyQuestionView.action";
                                              
                                        </script>
                                        <% 
                                                
                session.setAttribute("check_status_del",null);
                                        %>
                                        <s:set var="check_status_del" scope="session" value="null"></s:set>
                                        
                                       
                                          
</s:if>
 
   
<<div class="col-md-2"></div>
    <div class="col-md-8">  
        <h3 align="center"><strong><span class="label label-primary">List of all Question of the Quiz</span> <s:property value="quizname"/> </strong></h3>
        <h3 align="center"><strong>Creation Date of the Quiz is <s:property value="quizcreationdate"/> </strong></h3>  
              <div class="jumbotron">
                  <table style="width: 90%" class="jumbotron" align="center">
                      <thead>
                   <tr>
                
                       <th style="color:midnightblue"><strong>Question Name</strong></th>
                       <th style="color:midnightblue"><strong>OptionA</strong></th>
                       <th style="color:midnightblue"><strong>OptionB</strong></th>
                       <th style="color:midnightblue"><strong>OptionC</strong></th>
                       <th style="color:midnightblue"><strong>OptionD</strong></th>
                       <th style="color:midnightblue"><strong>Answer</strong></th>
                       <th style="color:midnightblue"><strong>Edit This Question</strong></th>
                       <th style="color:midnightblue"><strong>Delete this Question</strong></th>
                  
                
                   </tr></thead>
<s:iterator value="Quizlist"> 
        
        <tr style="width: 50%">
               
                <td> <s:property value="questionname"/></td>
                <td> <s:property value="optionA"/></td>
                <td> <s:property value="optionB"/></td>
                <td> <s:property value="optionC"/></td>
                <td> <s:property value="optionD"/></td>
                <td> <s:property value="answer"/></td>
                 <td> <a href='AdminQuestionEditView?id=<s:property value="questionid"/>'>Edit</td>
               <td> <a href='DeleteQuestion?id=<s:property value="questionid"/>'>Delete</td>
               
        </tr>
    </s:iterator>
          
                 </table>
                   <form>
          <s:submit value="Cancel" cssClass="btn btn-warning" align="center"></s:submit>  
          <s:submit value="Add New Question" action="RedirectQuestion" cssClass="btn btn-warning" align="center"></s:submit>  
                    </form>

                  </div>
        </div>
<div class="col-md-2">     </div>
</div>
<jsp:include page="masterpage_assistants/footer.jsp"></jsp:include>



