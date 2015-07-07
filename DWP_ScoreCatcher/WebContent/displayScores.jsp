<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="styles/main.css">
<title>Insert title here</title>
</head>
<body>
	<h1>Display Scores & Average</h1>
	<h3>Below are the scores with the average</h3>
	<c:forEach var="score" items="${scores}">
		<span><c:out value="${score.score}" /></span><br>
	</c:forEach>
	<br>
	<h3>The average score is:</h3>
<%-- 	<span><fmt:formatNumber type="number" maxIntegerDigits="2" minFractionDigits="2" value="${average}" /></span> --%>
	<c:out value="${average}" />
	<br>
	<c:out value="${message}" />
	<br>
	<a href="getScore.jsp"><button>Add Scores</button></a>
</body>
</html>