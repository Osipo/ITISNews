<%@ page import="Servlets.Utilclasses.DBWorker" %>
<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: Олег
  Date: 14.10.2016
  Time: 12:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" extends="Servlets.SecureJSP" %>
<html>
<head>
    <title>UserPage</title>
    <link rel="stylesheet" href="css/hellstyle.css">
    <link rel="stylesheet" href="css/conhell.css">
</head>
<body>
<header>
    <div class="maxwidth">
        <h1 class="text-header">BREAK NEWS</h1>
        <form id="f1" method="get" action="/Search">
            <input type="text" name="SearchField" placeholder="searching for...">
            <button type="submit" name="search">Search</button>
        </form>
        <form id="f12" method="post" action="/Lout">
            <button type="submit">Log out</button>
        </form>
    </div>
</header>
<div class="wrapper clear">
    <div class="userpan">
        <h2 class="text-userpan"><%=(String) session.getAttribute("UserName")%></h2>
        <hr>
        <label class="upanlable">News created:<%=(Integer) session.getAttribute("Created")%></label>
        <label class="upanlable">News searched: <%=(Integer) session.getAttribute("Searched")%></label>
        <label class="upanlable">Last Session:<%=new Date().toString()%></label>
        <a href="createnew.jsp"><button type="submit" class="ubutton">Create News</button></a>
            </div>
    <% if(request.getParameter("UID").equals("1"))
    {%>
    <div class="adminspan">
    <form class="adform" action="/Delete" method="post">
        <label for="1" class="aslab">Delete User or New: </label>
        <input type="text" name="TextTitleD" id="1" class="astext">
        <button type="submit" name="delete" class="asbutton">Delete.</button>
    </form>
        <form class="adform" action="/AdminSelect" method="post">
            <label for="4" class="aslab">Select New:</label>
            <input type="text" name="TextTitle" id="4" class="astext">
            <button type="submit" name="select" class="asitbutton">Select.</button>
        </form>
        </div>
    <%} else {%>
    <div class="adminspan">
        <form class="adform" action="/DeleteNew" method="post">
            <label for="2" class="aslab">Select your New: </label>
            <input type="text" name="TextTitleD" id="2" class="astext">
            <button type="submit" name="delete" class="asbutton">Delete.</button>
        </form>
        <form class="adform" action="/Select" method="post">
            <label for="3" class="aslab">Select your New: </label>
            <input type="text" name="TextTitle" id="3" class="astext">
            <button type="submit" name="select" class="asitbutton">Select.</button>
        </form>
        <a href="update.jsp"><button type="button" class="asnobutton" name="update">Update.</button></a>
    </div>
    <%}%>
</div>
</body>
</html>
