<
    
<%@taglib uri="/struts-tags" prefix="s" %>
<jsp:include page="masterpage_assistants/header_adminLoggedin.jsp"></jsp:include>
<s:if test="%{#session.login == null}">

<% response.sendRedirect("login.jsp");%> 

</s:if>
 
   
<div class="row">
    <div class="col-md-2"></div>
    <div class="col-md-8">  
        <h3 align="center"><strong><span class="label label-primary">List of all Question of the Quiz </span></strong><s:property value="quizname"/> </h3>
            <h3 align="center">Creation Date of the Quiz is <s:property value="quizcreationdate"/> </h3>  
              <div class="jumbotron">
                  <table style="width: 80%" class="jumbotron" align="center">
                      <thead>
                   <tr>
                
                       <th style="color:midnightblue"><strong>Question Name</strong></th>
                       <th style="color:midnightblue"><strong>OptionA</strong></th>
                       <th style="color:midnightblue"><strong>OptionB</strong></th>
                       <th style="color:midnightblue"><strong>OptionC</strong></th>
                       <th style="color:midnightblue"><strong>OptionD</strong></th>
                       <th style="color:midnightblue"><strong>Answer</strong></th>
                       <th style="color:midnightblue"><strong>Back</strong></th>
                
                   </tr></thead>
<s:iterator value="Quizlist"> 
        
        <tr style="width: 50%">
               
                <td> <s:property value="questionname"/></td>
                <td> <s:property value="optionA"/></td>
                <td> <s:property value="optionB"/></td>
                <td> <s:property value="optionC"/></td>
                <td> <s:property value="optionD"/></td>
                <td> <s:property value="answer"/></td>
               
               
        </tr>
    </s:iterator>
         
                 </table>
                  <p align="center"> <s:submit value="Delete Quiz" action="DeleteQuiz" cssClass="btn btn-success" align="center"></s:submit>
                      <s:submit value="Cancel" cssClass="btn btn-warning" align="center"></s:submit>  </p>
                  </div>
        </div>
<div class="col-md-2">     </div>
</div>
         
<jsp:include page="masterpage_assistants/footer.jsp"></jsp:include>



