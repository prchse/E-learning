


<%@taglib uri="/struts-tags" prefix="s" %>
<jsp:include page="masterpage_assistants/header_loggedUser.jsp"></jsp:include>
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
            <h3 align="center"><strong><span class="label label-success">All contents of this course </span></strong></h3>

            <div class="jumbotron">
                <table style="width: 100%" class="jumbotron" align="center">
                    <thead>
                        <tr>

                            <th><strong>Topic Id</strong></th>
                           
                            <th><strong> Content</strong></th>







                        </tr></thead>
                    <s:iterator value="tcblst"> 

                    <tr style="width: 50%">


                        <td><s:property value="topicId"/></td>
                       
                      <td><s:a href="download?fname=%{docContent}" ><s:property value="docContent" /></s:a></td>

                        
 
                        <%--<s:a href='<s:property value="docContent"/>' name="docUrl"><s:property value="docContent"/></s:a>--%>



                    </tr>
                </s:iterator>
            </table>
        </div>
    </div>
    <div class="col-md-1"></div>
</div>
<jsp:include page="masterpage_assistants/footer.jsp"></jsp:include>



