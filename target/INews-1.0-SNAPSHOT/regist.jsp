<%--
  Created by IntelliJ IDEA.
  User: Олег
  Date: 28.10.2016
  Time: 12:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" extends="Servlets.SecureReg" %>
<html>
<head>
    <title>Registration</title>
    <link rel="stylesheet" href="css/regstyle.css">
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
        <form id="re" method="post" action="/Reg">
            <h2>Want to sign up fill out this form!</h2>
            <br>
            <hr>
            <label for="1">Your Nickname:</label>
            <input type="text" name="NickField" placeholder="Jake" class="ntSaveForms" id="1">
            <label  for="2">Your Email:</label>
            <input type="text" placeholder="input login" name="LoginField" class="ntSaveForms" id="2">
            <label  for="3">Your Password:</label>
            <input type="password" name="PasswordField" placeholder="input password" class="ntSaveForms" id="3">
            <button type="submit" class="ntSaveFormsSubmit">Sign in</button>
        </form>
    </div>
</div>
<script type='text/javascript' src='http://code.jquery.com/jquery-latest.min.js'></script>
<script type="text/javascript" src="js/jquery.Storage.js"></script>
<script type='text/javascript' src="js/ntsaveforms.js"></script>
</body>
</html>
