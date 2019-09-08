<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Олег
  Date: 05.11.2016
  Time: 18:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Search</title>
    <link rel="stylesheet" href="css/searcgstyle.css">
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
    <div id="swrap">
        <%
            ArrayList<String> an = (ArrayList<String>) request.getAttribute("Searchlist");
            for (String s : an) {
                String href = "/newspage.jsp?Title=" + s;
        %><a href="<%=href%>" target="_blank"><%=s%>
    </a><%
        }
    %>
    </div>
</div>
</body>
</html>
