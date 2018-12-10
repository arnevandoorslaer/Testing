<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta charset="UTF-8">
<title>Overview</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
<header>
<h1><span>Web shop</span></h1>
<nav>
<ul>
<li><a href="Controller">Home</a></li>
<li id="actual"><a href="Controller?action=overview">Overview</a></li>
<li><a href="Controller?action=products">products</a></li>
    <% if(session.getAttribute("role") != null &&
            session.getAttribute("role").equals("ADMIN")){ %>
    <li><a href="Controller?action=addProduct">add product</a></li>
    <% } %>
<li><a href="Controller?action=signUp">Sign up</a></li>
    <li><a href="Controller?action=cart">
        My Cart
        <%if(request.getSession().getAttribute("tot") == null){ %>
        [0]
        <% }else{ %>
        [<%=request.getSession().getAttribute("tot") %>]
        <% } %>
    </a></li>
</ul>
</nav>
<h2>
User Overview
</h2>

</header><main>
    <form method="post" action="Controller?action=SetPersonOrder">
        <p>Sorteren op:</p>
        <select name="order">
            <c:forEach var="headd" items="${headers}">
                <option name="${headd}" value="${headd}">${headd}</option>
                <!--Hier nog aanduiden welke er actief is
                De var heet "orderrr"-->
            </c:forEach>
            <input type="submit" value="sorteren">
        </select>
    </form>
<table>
<tr>
<th>E-mail</th>
<th>First Name</th>
<th>Last Name</th>
    <% if(session.getAttribute("role") != null &&
            session.getAttribute("role").equals("ADMIN")){ %>
    <th> Delete</th>
    <% } %>
    <th> Check password</th>
</tr>
<c:forEach var="person" items="${persons}">
    <tr>
        <td>${person.email}</td>
        <td>${person.firstName}</td>
        <td>${person.lastName}</td>
        <% if(session.getAttribute("role") != null &&
                session.getAttribute("role").equals("ADMIN")){ %>
        <td><a href="Controller?action=deletePers&id=${person.userid}">delete</a></td>
        <% } %>
        <td><a href="Controller?action=checkRedirect&id=${person.userid}">Check</a></td>
    </tr>
</c:forEach>


<caption>Users Overview</caption>
</table>
</main>
<footer>
&copy; Webontwikkeling 3, UC Leuven-Limburg
</footer>
</div>
</body>
</html>