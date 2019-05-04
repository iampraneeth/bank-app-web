<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>display</title>
</head>
<body>
	<table border="1">
		<thead>
			<tr>
				<th>Account id</th>
				<th>Account holder name</th>
				<th>Account type</th>
				<th>Account balance</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="account" items="${accounts}">
				<tr>
					<td><c:out value="${account.accountId}" /></td>
					<td><c:out value="${account.accountHolderName}" /></td>
					<td><c:out value="${account.accountType}" /></td>
					<td><c:out value="${account.accountBalance}" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>



</body>
</html>