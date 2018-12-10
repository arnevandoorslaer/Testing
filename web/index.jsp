<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Home</title>
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
			<h2>Home</h2>

		</header>
		<main> Sed ut perspiciatis unde omnis iste natus error sit
		voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque
		ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae
		dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit
		aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos
		qui ratione voluptatem sequi nesciunt.
			<%if(session.getAttribute("person") == null){ %>
			<form method="post" action="Controller?action=login">
				<label for="id">User ID</label>
				<input type="number" name="id" id="id" placeholder="id">

				<label for="pwd">Password </label>
				<input type="password" name="pwd" id="pwd">

				<input type="submit" value="Log In">
			</form>
			<%}else{ %>
				<br>
				<h3>Welcome <%=session.getAttribute("person")%> !!!</h3>
				<form method="post" action="Controller?action=logout">
					<input type="submit" value="Log out">
				</form>
			<%} %>
		</main>
		<footer> &copy; Webontwikkeling 3, UC Leuven-Limburg </footer>

		<form method="post" action="Controller?action=setQuote" novalidate="novalidate">
			<p>Wil u een leuke Quote zien ?????</p>

			<input type="radio" name="choice" value="Ja"
				<% if(request.getAttribute("quote") != ""){ %>
				   checked="checked"
				<% } %>> Ja

			<input type="radio" name="choice" value="Nee"
				<% if(request.getAttribute("quote") == ""){ %>
				   checked="checked"
				<% } %>>Neen

			<input type="submit" id="submit" value="Instellen">
		</form>

		<p>
			<%=request.getAttribute("quote") %>
		</p>
	</div>
</body>
</html>