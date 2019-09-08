<%--
  Created by IntelliJ IDEA.
  User: Олег
  Date: 14.10.2016
  Time: 12:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" extends="Servlets.SecureReg" %>
<html>
<head>
    <title>HomePage</title>
    <link rel="stylesheet" href="css/startpage.css">
</head>
<body>
<header>
    <div class="maxwidth">
        <h1 class="text-header">BREAK NEWS</h1>
        <form id="f1" method="get" action="/Search">
            <input type="text" name="SearchField" placeholder="searching for...">
            <button type="submit" name="search">Search</button>
        </form>
    </div>
</header>
<div class="wrapper clear">
    <div class="formbase">
        <form id="ff2" method="post" action="/Login">
            <label  for="1">Login:</label>
            <input type="text" placeholder="input login" name="LoginField" id="1">
            <label  for="2">Password:</label>
            <input type="password" name="PasswordField" placeholder="input password" id="2">
            <button type="submit">Log in</button>
        </form>
    </div>
    <div id="r">
        <label name="brname">Want to create news? SIGN UP!</label>
        <a href="regist.jsp"><button type="submit">Registration</button></a>
    </div>
</div>
</body>
</html>
