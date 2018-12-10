
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta charset="UTF-8">
    <title>Check Password</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
<header>
    <h1><span>Web shop</span></h1>
    <nav>
        <ul>
            <li><a href="Controller">Home</a></li>
            <li><a href="Controller?action=overview">Overview</a></li>
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
        Check Password
    </h2>

</header>
    <main>
        <h3>Fill in the password</h3>
        <% if(request.getAttribute("check") != null){ %>
            <p><%=request.getAttribute("check") %></p>
        <% } %>
        <form method="post" action="Controller?action=check" novalidate="novalidate">
            <p><input type="hidden" id="id" name="id" value="${id}"> </p>
            <p><input type="password" id="password"  name="password" required> </p>
            <p><input type="submit" id="check" value="Check"></p>
        </form>
        <p>${out}</p>
    </main>
<footer>
    &copy; Webontwikkeling 3, UC Leuven-Limburg
</footer>
</div>
</body>
</html>
