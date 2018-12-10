<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta charset="UTF-8">
    <title>Sign Up</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <header>
        <h1><span>Web Shop</span></h1>
        <nav>
            <ul>
                <li><a href="Controller">Home</a></li>
                <li><a href="Controller?action=overview">Overview</a></li>
                <li><a href="Controller?action=products">products</a></li>
                <% if(session.getAttribute("role") != null &&
                        session.getAttribute("role").equals("ADMIN")){ %>
                <li id="actual"><a href="Controller?action=addProduct">add product</a></li>
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
            Add product
        </h2>

    </header><main>
    <% if (request.getAttribute("errors") != null) {%>
    <div class="alert-danger">
        <ul>
            <c:forEach var="error" items="${errors}">
                <li>${error}</li>
            </c:forEach>
        </ul>
    </div>
    <% } %>
    <form method="post" action="Controller?action=Add" novalidate="novalidate">
        <!-- novalidate in order to be able to run tests correctly -->
        <p><label for="ide">ID</label>
            <input type="text" id="ide" name="ide"></p>

        <p><label for="name">name</label>
            <input type="text" id="name" name="name"
                                                     required value="${name}"></p>
        <p><label for="description">description</label>
            <input type="text" id="description" name="description"
                                                           required value="${description}"> </p>
        <p><label for="price">price</label>
            <input type="number" id="price" name="price"
                                                         required value="${price}"> </p>
        <p><input type="submit" id="add" value="Add"></p>

    </form>
</main>
    <footer>
        &copy; Webontwikkeling 3, UC Leuven-Limburg
    </footer>
</div>
</body>
</html>
