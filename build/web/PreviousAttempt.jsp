<%@ taglib uri="/struts-tags" prefix="s" %>  
<jsp:include page="masterpage_assistants/header_loggedUser.jsp"></jsp:include>
<s:if test="%{#session.login == null}">

<% response.sendRedirect("login.jsp");%> 

</s:if>
    <div class="row">
        <div class="col-md-4"></div>
        <div class="col-md-4">  
            
             <div class="jumbotron">
                  <table style="width:100%" class="table jumbotron" align="center">
                    <thead>
                     <tr>
                         <th style="color:midnightblue"><strong>Assignment Content</strong></th>
                         <th style="color:midnightblue"><strong>Submission Date</strong></th>
                         <th style="color:midnightblue"><strong>Status</strong></th>
                         <th style="color:midnightblue"><strong>Remarks</strong></th>
                        
                
            </tr></thead>
<s:iterator value="list_assign"> 
        
        <tr style="width: 50%">
                
            <td><a href="<s:property value="assignment_content"/>"><s:property value="assignment_content"/></a></td>
                <td> <s:property value="assign_sub_date"/></td>    
                <td><s:property value="verified"/></td>
                <td><s:property value="remarks"/></td>  
              
                
        </tr>
</s:iterator>
                   </table>
           
        </div>
        <div class="col-md-4"></div>
    </div>
    <jsp:include page="masterpage_assistants/footer.jsp"></jsp:include>