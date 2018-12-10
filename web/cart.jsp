<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="domain.model.Product" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="domain.model.ShopService" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>My Cart</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <header>
        <h1>
            <span>Web Shop</span>
        </h1>
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
                <li id="actual"><a href="Controller?action=cart">
                    My Cart
                    <%if(request.getSession().getAttribute("tot") == null){ %>
                    [0]
                    <% }else{ %>
                    [<%=request.getSession().getAttribute("tot") %>]
                    <% } %>
                </a></li>
            </ul>
        </nav>
        <h2>My Cart</h2>

    </header>
    <main>
        <% if(request.getAttribute("foet") != null){ %>
            <h3><%=request.getAttribute("foet") %></h3>
        <% } %>
        <table>
        <tr>
            <th>id</th>
            <th>name</th>
            <th>description</th>
            <th>price</th>
            <th>amount</th>
        </tr>
        <%
            Map<Integer, Integer> mp =
                    (HashMap<Integer, Integer>)session.getAttribute("list");
            ShopService serv = (ShopService)request.getAttribute("service");
            int totaal = 0;
            try{
            Iterator it = mp.entrySet().iterator();
            while (it.hasNext()) {
                HashMap.Entry pair = (HashMap.Entry)it.next();
                String prodID = pair.getKey().toString();
                Product prod = serv.getProduct(prodID);
                int amount = (int)pair.getValue();
                totaal += amount*prod.getPrice();
            %>
            <tr>
                <td><%=prodID %></td>
                <td><%=prod.getName() %></td>
                <td><%=prod.getDescription() %></td>
                <td><%=prod.getPrice()%></td>
                <td><%=amount %></td>
                <td><a href="Controller?action=deleteCart&id=<%=prodID %>">delete from cart</a></td>
                <br>
            </tr>
        <%
            }
            }catch (Exception e){
                e.printStackTrace();
            }%>
        </table>
        <h3>De totaalprijs is: <%=totaal %></h3>
        <form method="post" action="Controller?action=Bestel">
            <input type="submit" value="Bestellen">
        </form>
    </main>
    <footer> &copy; Webontwikkeling 3, UC Leuven-Limburg </footer>
</div>
</body>
</html>