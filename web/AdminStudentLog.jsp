
        
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
    <div class="col-md-3"></div>
    <div class="col-md-6">  
        <h3 align="center"><strong><span class="label label-primary">Records of Logs of This Students</span></strong></h3>
        <div class="jumbotron">
                  <table style="width: 50%" class="jumbotron" align="center">
                    <thead>
                     <tr>
                         <th style="color:midnightblue"><strong>Session Id</strong></th>
                         <th style="color:midnightblue"><strong>Login Time</strong></th>
                         <th style="color:midnightblue"><strong>Log Out</strong></th>
                
                
            </tr></thead>
<s:iterator value="list"> 
        
        <tr style="width: 50%">
                
                <td> <s:property value="seesionid"/></td>
                <td> <s:property value="login"/></td>    
                <td><s:property value="logout"/></td>
               
        </tr>
</s:iterator>
                   </table>
        </div>
        </div>
<div class="col-md-3"></div>
</div>
<jsp:include page="masterpage_assistants/footer.jsp"></jsp:include>

