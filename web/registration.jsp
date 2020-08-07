<%@ taglib uri="/struts-tags" prefix="s" %>  
<jsp:include page="masterpage_assistants/header.jsp"></jsp:include>
    <div class="row">
        <div class="col-md-3"></div>
        <div class="col-md-6">
            <script type="text/javascript">
                function chkfrm(form)
                {
                    if (form.studentId.value == "")
                    {
                        alert("Plz enter the email_id");
                        form.studentId.focus();
                        return false;
                    }
                    else if (form.emailId.value == "")
                    {
                        alert("Plz enter again email_id");
                        form.emailId.focus();
                        return false;
                    }
                    else if (form.password.value == "")
                    {
                        alert("Plz enter the password");
                        form.password.focus();
                        return false;
                    }
                    else if (form.fname.value == "")
                    {
                        alert("Plz enter first name");
                        form.fname.focus();
                        return false;
                    }
                    else if (form.lname.value == "")
                    {
                        alert("Plz enter last name");
                        form.lname.focus();
                        return false;
                    }
                    else if (form.contactno.value == "")
                    {
                        alert("Plz enter the conactno");
                        form.contactno.focus();
                        return false;
                    }
                    else if (form.studentId.value != form.emailId.value)
                    {
                        alert("Email Id is not matched");
                        form.emailId.focus();
                        form.studentId.focus();
                        return false;
                    }
                    else
                    {

                        return true;
                    }
                    return true;
                }
            </script>

            <div class="jumbotron">
                <h2 align="center"><strong><span class="label label-primary">Register Here</span></strong></h2>

            <s:form theme="simple" action="registerprocess" onSubmit="return chkfrm(this)" method="post" >
                <s:label value="Email Id:"></s:label><s:textfield type="email" cssClass="form-control" placeholder="Email Id" name="studentId"  requiredLabel="true"></s:textfield>
                <s:label value="Confirm Email Id:"></s:label><s:textfield type="email" cssClass="form-control" placeholder="Confirm Email Id" name="emailId" requiredLabel="true"></s:textfield>
                <s:label value="Password:"></s:label><s:password type="password" cssClass="form-control" placeholder="Enter Password" name="password" minlength="6" maxLength="25" requiredLabel="true"></s:password>
                <s:label value="First Name:"></s:label><s:textfield type="text" cssClass="form-control" placeholder="First Name" name="fname"  maxlength="25" requiredLabel="true"></s:textfield>
                <s:label value="Last Name:"></s:label><s:textfield type="text" cssClass="form-control" placeholder="Last Name" name="lname"  maxlength="25" requiredLabel="true"></s:textfield>

                <s:label value="Contact No:"></s:label><s:textfield type="text" cssClass="form-control" placeholder="1-234-567-890" pattern="[6-9]{1}[0-9]{9}" name="contactno" requiredLabel="true"></s:textfield></br>
                <s:submit cssClass="btn btn-primary" value="register" ></s:submit>
                <s:reset cssClass="btn btn-danger"></s:reset>
            </s:form>  </div></div>
    <div class="col-md-3"></div>
</div><jsp:include page="masterpage_assistants/footer.jsp"></jsp:include>




