<%--
  Created by IntelliJ IDEA.
  User: Олег
  Date: 07.11.2016
  Time: 10:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Not found!</title>
    <script type="text/javascript" src="js/gif.js"></script>
    <link rel="stylesheet" href="css/errorstyle.css">
</head>
<body>
<%String s =(String) request.getParameter("Error");%>
<% if(s.equals("search")){%>
<h1 class="htext">ERROR: NOT FOUND!</h1>
<div class="wrapper">
    <div class="error-content">
        <h1 class="atext">Uh oh... Something didn't work.(You fed in invalid data!)</h1>
        <p>This data doesn't seem to exist. You might have followed a bad way of your consciousness or mistyped the data, feel free to try again.  Alternatively, you can <a href="/Error">return to the home page</a></p>
    </div>
</div>
<% }else if(s.equals("regist")){%>
<h1 class="htext">ERROR: LOGIN AND PASSWORD ARE ALREADY EXIST!</h1>
<div class="wrapper">
    <div class="error-content">
        <h1 class="atext">Uh oh...You fed in invalid data.(LOGIN AND PASSWORD IS ALREADY EXIST!)</h1>
        <p>You might have followed a bad way of your consciousness or mistyped the data, feel free to try again. <a href="/regist.jsp">return to the Registration page</a></p>
    </div>
</div>
<% }else if(s.equals("exist")){%>
<h1 class="htext">ERROR: TITLE IS ALREADY EXISTS!</h1>
<div class="wrapper">
    <div class="error-content">
        <h1 class="atext">Uh oh...You fed in invalid data.(This title is already exist!)</h1>
        <p>You might have followed a bad way of your consciousness or mistyped the data, feel free to try again. <a href="/createnew.jsp">return to the CreateNews page</a></p>
    </div>
</div>
<%}else if(s.equals("regist1")){%>
<h1 class="htext">ERROR: LOGIN IS ALREADY EXISTS!</h1>
<div class="wrapper">
    <div class="error-content">
        <h1 class="atext">Uh oh...You fed in invalid data.(This LOGIN is already exist!)</h1>
        <p>You might have followed a bad way of your consciousness or mistyped the data, feel free to try again. <a href="/regist.jsp">return to the Registration page</a></p>
    </div>
</div>
<%}else if(s.equals("regist2")){%>
<h1 class="htext">ERROR: PASSWORD IS ALREADY EXISTS!</h1>
<div class="wrapper">
    <div class="error-content">
        <h1 class="atext">Uh oh...You fed in invalid data.(This PASSWORD. is already exist!)</h1>
        <p>You might have followed a bad way of your consciousness or mistyped the data, feel free to try again. <a href="/regist.jsp">return to the Registration page</a></p>
    </div>
</div>
<%}else if(s.equals("regist3")){%>
<h1 class="htext">ERROR: INVALID DATA!</h1>
<div class="wrapper">
    <div class="error-content">
        <h1 class="atext">Uh oh...You fed in invalid data.(Data have to be like: Login:my1example@email.com'
            Name: Nike_Dram3 Password:Without0spaces0!)</h1>
        <p>You might have followed a bad way of your consciousness or mistyped the data, feel free to try again. <a href="/regist.jsp">return to the Registration page</a></p>
    </div>
</div>
<%}%>
</body>
</html>
