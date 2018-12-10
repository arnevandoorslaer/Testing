<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>error 404</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <header>
        <h1>
            <span>Web shop</span>
        </h1>
        <nav>
            <ul>
                <li id="actual"><a href="Controller">Home</a></li>
                <li><a href="Controller?action=overview">Overview</a></li>
                <li><a href="Controller?action=products">products</a></li>
                <% if(session.getAttribute("role") != null &&
                        session.getAttribute("role").equals("ADMIN")){ %>
                <li><a href="Controller?action=addProduct">add product</a></li>
                <%} %>
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
        <h2>Error Page 404</h2>

    </header>
    <main> Deze pagina bestaat niet. <br><br>
        Het glas is niet half vol of half leeg.<br>
        <strong> Het is vulbaar!</strong>

    </main>
    <footer> &copy; Webontwikkeling 3, UC Leuven-Limburg </footer>
</div>
</body>
</html>
