<html>
       <head>
              <script type="text/javascript" src="bootStrap/js/jquery-2.1.3.min.js"></script>
       </head>
<jsp:include page="masterpage_assistants/header.jsp"></jsp:include>
<div class="row">
    <div class="col-md-4"></div>
    <div class="col-md-4"> 
        <s:if test="%{#session.checkstatus=='abc'}">
		
					<script>
                                            alert("status is changed !");
                                            window.location="Login.jsp";
                                        
                                        </script>
                                        <s:set var="checkstatus" scope="session" value="null"></s:set>
		
</s:if>
              
</html>
    <div class="col-md-4"></div>
    <div class="col-md-4"></div>
</div>
<jsp:include page="masterpage_assistants/footer.jsp"></jsp:include>
    