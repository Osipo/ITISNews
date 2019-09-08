<%--
  Created by IntelliJ IDEA.
  User: Олег
  Date: 31.10.2016
  Time: 12:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" extends="Servlets.SecureJSP" %>
<html>
<head>
    <title>Create New</title>
    <link rel="stylesheet" href="css/createstyle.css">
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
    <div id="crform">
        <h2>Create News</h2>
        <br>
        <hr>
        <form method="post" action="/Create">
            <label for="1">Title of News:</label>
            <input type="text" name="TitleField" class="ntSaveForms" id="1">
            <label for="2">Content:</label>
            <input type="text" name="ContentField" class="ntSaveForms" id="2">
            <label for="3">Description:</label>
            <textarea id="3" typeof="text" class="ntSaveForms" name="DescField"></textarea>
            <button type="submit" class="ntSaveFormsSubmit" id="cbutton">Create</button>
            <a href="hello.jsp?UID=<%=session.getAttribute("UserId")%>"><button type="button" id="cbutton2">Cancel</button></a>
        </form>
    </div>
</div>
<script type='text/javascript' src='http://code.jquery.com/jquery-latest.min.js'></script>
<script type="text/javascript" src="js/jquery.Storage.js"></script>
<script type='text/javascript' src="js/ntsaveforms.js"></script>
</body>
</html>
