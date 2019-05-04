<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
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

			<tr>
				<td>${searches.accountId}</td>
				<td>${searches.accountHolderName}</td>
				<td>${searches.accountType}</td>
				<td>${searches.accountBalance}</td>
			</tr>

		</tbody>
	</table>



</body>
</html>