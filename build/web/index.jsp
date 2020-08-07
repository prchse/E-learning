<%-- 
    Document   : index
    Created on : Mar 15, 2015, 6:10:22 PM
    Author     : SIDDHARTH
--%>

<!--%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>




        <jsp:include page="masterpage_assistants/header.jsp" />
       <%-- <s:if test="%{#session.user_registered=='yes'}">
           
             
					<script>
                                            alert("your account is under review of E-learning Team"); 
                                             <% 
                                                
                session.setAttribute("user_registered",null);
                                        %>
                                          
                                              
                                        </script>
                                       
                                        <s:set var="user_registered" scope="session" value="null"></s:set>
                                        
                                       
                                          
</s:if>  --%>
<s:if test="%{#session.user_exist=='yes'}">
           
             
					<script>
                                            alert("Account for this email id is already exist!"); 
                                             <% 
                                                
                session.setAttribute("user_exist",null);
                session.removeAttribute("user_exist");
                                        %>
                                          
                                              
                                        </script>
                                       
                                        <s:set var="user_exist" scope="session" value="null"></s:set>
                                        
                                       
                                          
</s:if>  
        <s:if test="%{#session.authenticate=='no'}">
           
             
					<script>
                                            alert("username or password is incorrect"); 
                                             <% 
                                                
                session.setAttribute("authenticate",null);
                                        %>
                                          
                                              
                                        </script>
                                       
                                        <s:set var="authenticate" scope="session" value="null"></s:set>
                                        
                                       
                                          
</s:if>  
         
        
        <div class="container">
        <div class="jumbotron">

        
          <p>
            E-learning (or eLearning) is the use of electronic educational technology in learning 
            and teaching.E-earning is essentially the network-enabled transfer of skills and 
            knowledge. E-learning refers to using electronic applications and processes to learn.
            E-learning applications and processes include Web-based learning, computer-based 
            learning. 
            E-Learning involves:
            ?	Engaging with online learning material either individually or as part of a group
            ?	Communicating with some discussions forum and fellow students via email or forums.
            Distance education provided the base for e-learning's development. E-learning can be "on demand". It overcomes timing, attendance and travel difficulties.
            E-learning would consist of the following modules:

            1.	Course Registration & content Uploading
            2.	Student Registration & Mapping
            3.	Time Tracking/Progress
            E-learning (or eLearning) is the use of electronic educational technology in learning and teaching. E-earning is essentially the network-enabled transfer of skills and knowledge. E-learning refers to using electronic applications and processes to learn. E-learning applications and processes include Web-based learning, computer-based learning.
            1.1.1	User groups
            Privileges and rights are given based on these groups. A user of the application can automatically be promoted to a more privileged user group based on criteria set by the administrator.
            Anonymous User
            Anonymous user of the site is commonly known as a guest or visitor. Guests are typically granted access to all functions that do not require database alterations or breach privacy. A guest can usually view the contents of the application or use such features as read marking.
            Registered User
            A registered user of the site is an authorized person by administrator of the site. Registered users have permissions to update his profile, learn the courses which are provided by the content adminitrator.   
            Administrator
            The administrators (short form: "admin") manage the technical details required for running the site. As such manage the rules, create sections and sub-sections, as well as perform any database operations (database backup etc.). Administrators may also make application wide announcements, or change the appearance (known as the skin) of application.
            1.1.2	Courses
            ?	The courses in the applications can be of the latest technologies of programming and development.
            ?	Courses can be of competative exams.
            ?	Courses can be of the topics which are very trending and emerging in the market.

            1.1.3	Evaluation/Quiz
            ?	Evaluation or quiz is to check the performance of the candidate/user/subscriber
            ?	Evaluation can be regularly basis and also user can give the evaluation test or quiz to check the performance by oneself.

            1.2	PURPOSE OF PROJECT 
            ?	The purpose of this site is to provide information to the user about courses.
            Purpose of this project is to provide a complete boosting system to enhance the knowledge about the various topics which are currently involved and emerged.
            ?	The purpose of this project is to upload various contents such as audio and video according various topics along with quizzes with answers and assignments to test the user what the user has learned till now. It will facilitate user to check his own capability. 
            ?	 This site will enlighten the way of learning by making it online.
            ?	On this site various area of education has been covered such as computer science, Biology, Mathematics, Commerce etc. and various topics will be added to these courses in future as needed.
            ?	The admin can add questions to the quiz and assignments and modify it as well.

            Benefits of the Project:
            ?	All shared questions and content can remotely be accessed from anywhere.
            ?	 Cheaper idea to find the information and knowledge in same place.
            ?	Reduces learning time and training cost.
            ?	 The project can save papers because everything is on to the database.
            ?	 Easily updating is possible unlike a printed magazine or journals. There is no expiration possible.
            ?	 Easy to Remove errors and fix them.
            ?	Easily leverage existing content

E-learning (or eLearning) is the use of electronic educational technology in learning and teaching. E-earning is essentially the network-enabled transfer of skills and knowledge. E-learning refers to using electronic applications and processes to learn. E-learning applications and processes include Web-based learning, computer-based learning.
1.1.1	User groups
Privileges and rights are given based on these groups. A user of the application can automatically be promoted to a more privileged user group based on criteria set by the administrator.
Anonymous User
Anonymous user of the site is commonly known as a guest or visitor. Guests are typically granted access to all functions that do not require database alterations or breach privacy. A guest can usually view the contents of the application or use such features as read marking.
Registered User
A registered user of the site is an authorized person by administrator of the site. Registered users have permissions to update his profile, learn the courses which are provided by the content adminitrator.   
Administrator
The administrators (short form: "admin") manage the technical details required for running the site. As such manage the rules, create sections and sub-sections, as well as perform any database operations (database backup etc.). Administrators may also make application wide announcements, or change the appearance (known as the skin) of application.
1.1.2	Courses
?	The courses in the applications can be of the latest technologies of programming and development.
?	Courses can be of competative exams.
?	Courses can be of the topics which are very trending and emerging in the market.

1.1.3	Evaluation/Quiz
?	Evaluation or quiz is to check the performance of the candidate/user/subscriber
?	Evaluation can be regularly basis and also user can give the evaluation test or quiz to check the performance by oneself.

1.2	PURPOSE OF PROJECT 
?	The purpose of this site is to provide information to the user about courses.
Purpose of this project is to provide a complete boosting system to enhance the knowledge about the various topics which are currently involved and emerged.
?	The purpose of this project is to upload various contents such as audio and video according various topics along with quizzes with answers and assignments to test the user what the user has learned till now. It will facilitate user to check his own capability. 
?	 This site will enlighten the way of learning by making it online.
?	On this site various area of education has been covered such as computer science, Biology, Mathematics, Commerce etc. and various topics will be added to these courses in future as needed.
?	The admin can add questions to the quiz and assignments and modify it as well.
    

        <jsp:include page="masterpage_assistants/footer.jsp"></jsp:include>