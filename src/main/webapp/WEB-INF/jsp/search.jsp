<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<%
	String ss = null;//response.getHeader("Authorization");
	ss = request.getParameter("token");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Search</title>

<style>
table {
	width: 70%;
}

table, th, td {
	border: 1px solid black;
	border-collapse: collapse;
}

th, td {
	padding: 15px;
	text-align: left;
}

table#t01 tr:nth-child(even) {
	background-color: #eee;
}

table#t01 tr:nth-child(odd) {
	background-color: #fff;
}

table#t01 th {
	background-color: black;
	color: white;
}
</style>
</head>
<body>
	<jsp:include page="header.jsp" />

	<form action="/action_page.php">
		<input type="hidden" value="<%=ss%>" name="token" id="token" />

		<p>
			<label for="email"><b>First Name</b></label> <input type="text"
				id="firstName" placeholder="First Name" name="email">
			&nbsp;&nbsp;&nbsp;&nbsp; <label for="psw"><b>Last Name</b></label> <input
				type="text" placeholder="Enter Last Name" name="lastName"
				id="lastName"> &nbsp;&nbsp;&nbsp;&nbsp; <label
				for="psw-repeat"><b>Mobile Number</b></label> <input type="text"
				placeholder="Enter Mobile Number" name="mobile" id="mobile">
			&nbsp;&nbsp;&nbsp;&nbsp; <input type="button" onclick="search()"
				value="Search" />
		</p>


		<p>
		<div>Search Results :</div>

		<div>
			<table border="1" width="70%" id="resultTable">
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Role</th>
					<th>Manager</th>
					<th>Mobile</th>
				</tr>

			</table>

		</div>
		</p>

	</form>
</body>
<script type="text/javascript">
	function search() {
		var x = document.getElementById("resultTable").rows.length;
		;
		// 		alert(x);
		for (res = x; res > 1; res--) {
			if (res == 1) {
				break;
			}
			document.getElementById("resultTable").deleteRow(1);
		}
		var myObj = {
			"firstName" : "" + document.getElementById("firstName").value + "",
			"lastName" : "" + document.getElementById("lastName").value + "",
			"mobile" : "" + document.getElementById("mobile").value + ""
		};

		var myJSON = JSON.stringify(myObj);
		// 		alert(myJSON);
		var xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
				// 				alert(this.responseText);
				var obj = JSON.parse(this.responseText);
				var table = document.getElementById("resultTable");
				for (i in obj) {
					var row = table.insertRow(1);
					var cell0 = row.insertCell(0);
					var cell1 = row.insertCell(1);
					var cell2 = row.insertCell(2);
					var cell3 = row.insertCell(3);
					var cell4 = row.insertCell(4);
					cell0.innerHTML = '<a href="#" onclick="doRefine(\''
							+ obj[i].userId + '\')">' + obj[i].firstName
							+ '</a>';
					cell1.innerHTML = obj[i].lastName;
					cell2.innerHTML = obj[i].role;
					cell3.innerHTML = obj[i].managerName;
					cell4.innerHTML = obj[i].mobile;
				}
			}
		};
		xhttp.open("POST", "/ca/admin/user", true);
		xhttp.setRequestHeader("Content-type", "application/json");
		// 		xhttp.setRequestHeader('Authorization', 'Bearer '
		// 				+ document.getElementById("token").value);
		xhttp.setRequestHeader('Authorization', 'Bearer '
				+ document.getElementById("token").value);
		xhttp.send(myJSON);
	}

	function doRefine(userId) {
		document.forms["homePageForm"].action = "/ca/mvc/userSearch";
		document.getElementById("userId").value = userId;
		// 		alert(document.getElementById("mobileNum").value);
		document.forms["homePageForm"].method="post";
		document.forms["homePageForm"].submit();

	}
</script>
</html>