<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Iterator" %><%--
  Created by IntelliJ IDEA.
  User: Олег
  Date: 22.12.2016
  Time: 0:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SelectPage</title>
    <link rel="stylesheet" href="css/tablestyle.css">
</head>
<body>
<header>
    <div class="maxwidth">
        <h1 class="text-header">BREAK NEWS</h1>
        <form id="f1">
            <input type="text" name="SearchField" placeholder="searching for...">
            <button type="submit" name="search">Search</button>
        </form>
        <form id="f12">
            <button type="submit">Log out</button>
        </form>
    </div>
</header>
<div class="wrapper clear">
    <div class="tablespan">
        <table class="spantable"  cellspacing="2" border="4px solid">
            <colgroup>
                <col span="1" width="15%">
            </colgroup>
            <thead>
            <tr>
                <th>User Id:</th>
                <th>Title of New:</th>
            </tr>
            </thead>
            <%
                int q = 0;
                ArrayList<String> Titles = (ArrayList<String>) request.getAttribute("Titles");
                ArrayList<Integer> u_ids = (ArrayList<Integer>) request.getAttribute("U_IDS");
                while(q<Titles.size()){
                    %>
                 <tr>
                     <th><%=u_ids.get(q)%></th>
                     <th><%=Titles.get(q)%></th>
                 </tr>
            <%
                    q++;
                }
            %>
        </table>
    </div>
</div>
</body>
</html>
