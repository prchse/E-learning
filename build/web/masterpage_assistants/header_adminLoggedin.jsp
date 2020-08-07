<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="css/bootstrap.min.css">

        <!-- Optional theme -->
        <link rel="stylesheet" href="css/bootstrap-theme.min.css">
        <!--script src="../js/jquery-2.1.3.min.js" type="text/javascript"></script-->
        <!-- Latest compiled and minified JavaScript -->
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery-2.1.3.min.js" type="text/javascript"></script>

    </head>
    <body>
        
        <!-- Course Check -->
      
        
        
        
<center><h1> <img id="logo-main" src="images/elearning1.png" width="15%" height="15%" alt="Logo Thing main logo"> Welcome to Elearning</h1>
</center>
<nav class="navbar navbar-default">
    <div class="container">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Elearning</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li class="active"><a href="Admin.jsp">Home<span class="sr-only"></span></a></li>
             
                <li><a href="ManageStudents">Manage Students</a></li>
                <!--<li><a href="AdminManageCourse.jsp">Manage Course</a></li>-->
                <!--new changes-->
                <li class="dropdown">
                    
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Manage Courses <span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="AdminAddCourse.jsp">Create New Course </a></li>
                         <li><a href="Viewcourse">View all Course</a></li>
                         <li><a href="ModifyCourse">Modify Course Contents</a></li>
                         <li><a href="DeleteCourseSelect">Delete Course</a></li>
                           <li><a href="Viewcourse">Change the Status of the Course</a></li>
<!--                        <li><a href="#">Something else here</a></li>
                        <li class="divider"></li>
                        <li><a href="#">Separated link</a></li>-->
                    </ul>
                </li>
                  <li><a href="NotificationAdmin">Notification</a></li>
            </ul>
            <!--form class="navbar-form navbar-left" role="search">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="Search">
                </div>
                <button type="submit" class="btn btn-default">Submit</button>
            </form-->
            <ul class="nav navbar-nav navbar-right">
                
                <li><a href="#">Welcome Admin</a></li>
                <li class="dropdown">
                    
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><s:property value="#session.login"/> <span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="#">Profile</a></li>
                        <li><a href="logout">logout</a></li>
<!--                        <li><a href="#">Something else here</a></li>
                        <li class="divider"></li>
                        <li><a href="#">Separated link</a></li>-->
                    </ul>
                </li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>