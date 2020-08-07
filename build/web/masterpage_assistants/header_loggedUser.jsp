
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

        <!-- Latest compiled and minified JavaScript -->
        <script src="js/bootstrap.min.js"></script>

    </head>
    <body>
    <center><h1><img id="logo-main" src="images/elearning1.png" width="100" height="100" > E-learning</h1>

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
                <a class="navbar-brand" href="index.jsp">Elearning</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li class="active"><a href="Home.jsp">Home<span class="sr-only">(current)</span></a></li>
                    <li><a href="loggedusercourse">Courses</a></li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Courses<span class="caret"></span></a>
                        <ul class="dropdown-menu" role="menu">
                            <li><a href="loggedusercourse">Enroll to course</a></li>
                            <li><a href="loggedusercoursedis">Disenroll to course</a></li>
                            <!--<li class="divider"></li>-->
                            <!--<li><a href="#">Separated link</a></li>-->
                        </ul>
                    </li>
                    <li><a href="enrolledCourses">Enrolled Courses</a></li>
                    <li><a href="StudentNotify">Notification</a></li>
                    <li><a href="loggedAboutUs.jsp">About Us</a></li>
                    <li><a href="loggedContactus.jsp">Contact Us</a></li>
                    <li><a href="loggedfrequently.jsp">FAQ</a></li>
                </ul>
                <!--form class="navbar-form navbar-left" role="search">
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="Search">
                    </div>
                    <button type="submit" class="btn btn-default">Submit</button>
                </form-->
                <ul class="nav navbar-nav navbar-right">


                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"> <s:property value="#session.login"/><span class="caret"></span></a>
                        <ul class="dropdown-menu" role="menu">
                            <li><a href="profile">Profile</a></li>
                            <li><a href="logout">logout</a></li>
                            <!--<li class="divider"></li>-->
                            <!--<li><a href="#">Separated link</a></li>-->
                        </ul>
                    </li>
                </ul>
            </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
    </nav>