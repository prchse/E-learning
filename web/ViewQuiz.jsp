<%-- 
    Document   : ViewQuiz
    Created on : Apr 1, 2015, 1:27:38 AM
    Author     : SIDDHARTH
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>

        <jsp:include page="masterpage_assistants/header_loggedUser.jsp"></jsp:include>
            <div class="row">
                <div class="col-md-3"></div>
                <div class="col-md-6">  
                    <h3 align="center"><strong>Records of All Quiz</strong></h3>
                    <div class="jumbotron">
                        <table style="width: 50%" class="jumbotron">
                            <thead>
                                <tr>
                                    <th>Quiz Name</th>
                                    <th>Quiz Creation Date</th>
                                    <th>Duration</th>

                                    <th></th>


                                </tr></thead>
                            <tr>

                            <s:iterator value="Quizlist"> 




                                <td> <s:property value="quizname"/></td>
                                <td> <s:property value="quizcreationdate"/></td>
                                <td> <s:property value="duration"/></td>
                                <td><a class="btn btn-primary" href='QuizRetrieve?quizid=<s:property value="quizid"/>'>Quiz</a></td>
                            </tr>

                        </s:iterator>

                    </table>
                </div>
            </div>
            <div class="col-md-3"></div>
        </div>

        <jsp:include page="masterpage_assistants/footer.jsp"></jsp:include>
    </body>
</html>
