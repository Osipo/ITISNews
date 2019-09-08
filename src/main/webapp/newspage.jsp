<%@ page import="java.util.ArrayList" %>
<%@ page import="Servlets.Utilclasses.DBWorker" %><%--
  Created by IntelliJ IDEA.
  User: Олег
  Date: 05.11.2016
  Time: 18:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>News</title>
    <link rel="stylesheet" href="css/newspgstyle.css">
</head>
<body>
<header>
    <div class="maxwidth">
        <h1 id="text-header">BREAK NEWS</h1>
        <%String s =(String) request.getParameter("Title");
         System.out.println(s);
         ArrayList<String> content = DBWorker.dBFind("SELECT content FROM users.userlog_2 WHERE title='"+s+"'","content");
         ArrayList<String> descr = DBWorker.dBFind("SELECT describ FROM users.userlog_2 WHERE title='"+s+"'","describ");%>
        <h1 id="text-header2"><%=s%></h1>
    </div>
</header>
<div class="wrapper clear">
    <h1 class="article"><%=s%></h1>
    <br>
    <h2 class="cont"><%=content.get(0)%></h2>
    <br>
    <p class="desc"><%=descr.get(0)%></p>
</div>
</body>
</html>
