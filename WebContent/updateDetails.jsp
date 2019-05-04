<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ page isELIgnored="false"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>DISPLAY ACCOUNT DETAILS</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
</head>
<body>
	<form action="updateDetails.do" method="post">
		<div class="form-group">
			<label for="Name">CUSTOMER ID</label> 
			<input type="text"
				class="form-control input-sm" style="width: 270px" id="text"
				placeholder="Enter NAME" name="customer_id"
				value="${accounts.accountId }" readonly="readonly" required> 
				
				<label for="Name">CUSTOMER
				NAME:</label> <input type="text" class="form-control input-sm"
				style="width: 270px" id="text" placeholder="Enter NAME"
				name="customer_name" value="${accounts.accountHolderName }" required>
		</div>
		<div class="form-group">
			<label for="type">ACCOUNT TYPE:</label> <select name="account_type"
				value="${accounts.accountType }" required>
				<option value="">SELECT</option>
				<option value="Current">CURRENT</option>
				<option value="Savings">SAVINGS</option>
			</select>
		</div>
		<div class="form-group">
			<label for="number">ACCOUNT BALANCE:</label> <input type="number"
				class="form-control input-sm" style="width: 270px" id="number"
				placeholder="Enter Amount" name="account_balance"
				value="${accounts.accountBalance }" readonly="readonly" required>
			<br>
			<div style="margin-left: 80px;">
				<button type="submit" class="btn btn-default">update</button>
			</div>
	</form>

</body>
</html>