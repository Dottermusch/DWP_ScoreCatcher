<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="styles/main.css">
<title>Get Score</title>
</head>
<body>
	<h1>Enter Test Score</h1>
	<form action="scoreServlet" method="post">
	<input type="hidden" name="action" value="addScore">
	<label for="testScore" class="moreNarrow">Score:</label>
	<input type="number" class="moreNarrow" name="score" max="100" min="0" required /><br><br>
	<input type="submit" value="Add Score" />
	</form>
	<br>
	<form action="scoreServlet" method="post">
	<input type="hidden" name="action" value="calcAverage">
	<input type="submit" value="Calculate Average" />	
	</form>
</body>
</html>