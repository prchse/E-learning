
       
   
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
        <h3 align="center"><strong><span class="label label-primary">List of all Question of this Quiz </span></strong></h3>
              
              <div class="jumbotron">
                  <table style="width: 90%" class="table table-bordered" align="center">
                      <thead>
                   <tr>
                
                       <th><strong>Question Name</strong></th>
                       <th><strong>OptionA</strong></th>
                       <th><strong>OptionB</strong></th>
                       <th><strong>OptionC</strong></th>
                       <th><strong>OptionD</strong></th>
                       <th><strong>Answer</strong></th>
                        <th><strong>Marks</strong></th>
                   </tr></thead>
<s:iterator value="Quizlist"> 
        
        <tr style="width: 50%">
               
                <td> <s:property value="questionname"/></td>
                <td> <s:property value="optionA"/></td>
                <td> <s:property value="optionB"/></td>
                <td> <s:property value="optionC"/></td>
                <td> <s:property value="optionD"/></td>
                <td> <s:property value="answer"/></td>
                <td> <s:property value="marks"/></td>
               
        </tr>
    </s:iterator>
                 </table>
                  </div>
        </div>
<div class="col-md-2"></div>
</div>
<jsp:include page="masterpage_assistants/footer.jsp"></jsp:include>



