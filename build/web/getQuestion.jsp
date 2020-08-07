
<jsp:include page="masterpage_assistants/header_loggedUser.jsp"></jsp:include>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ page import="java.util.*;" %>
<div class="row">
    <div class="col-md-3"></div>
    <div class="col-md-6">  
        <h3 align="center"><strong>Simple Quiz</strong></h3>
        <div class="container">
            <table style="width: 50%" class="jumbotron">
                <thead>
                </thead>

                <s:form method="post" >
                    <tr><td>  
                            <table class="jumbotron">
                                <tr bgcolor="white" >
                                    <td><div><s:property value="%{questionNo}"/></div></td>
                                    <td><div><s:property value="%{qb.questionName}"/></div></td>

                                </tr>
                                <tr>
                                    <td ><input type="radio" name="userAnswer"  value="A" /><div><s:property value="%{qb.optionA}"/></div></td>

                                    <td ><input type="radio" name="userAnswer"  value="B" /><div><s:property value="%{qb.optionB}"/></div></td>
                                </tr>
                                <tr>
                                    <td ><input type="radio" name="userAnswer"  value="C" /><div><s:property value="%{qb.optionC}"/></div></td>
                                    <td ><input type="radio" name="userAnswer"  value="D" /><div><s:property value="%{qb.optionD}"/></div></td>

                                 
                                    <td> <input type="hidden" value="<s:property value="%{qb.answer}"/>" name="correctAnswer" /></td>
                                    <td> <input type="hidden" value="%{qb.quizId}" name="quizId" /></td>
                                    
                                </tr>
                            </table></td></tr>

                    <tr>
                        
                        <td><s:submit action="nextQues" value="Next" ></s:submit></td>
                     
                    </s:form>

            </table>
        </div>
    </div>
    <div class="col-md-3"></div>
</div>
<jsp:include page="masterpage_assistants/footer.jsp"></jsp:include>