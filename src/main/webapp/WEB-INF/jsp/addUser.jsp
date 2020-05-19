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
<title>Add User</title>

<style>
body {
	font-family: Arial, Helvetica, sans-serif;
	background-color: black;
}

* {
	box-sizing: border-box;
}

/* Add padding to containers */
.container {
	padding: 16px;
	background-color: white;
}

/* Full-width input fields */
input[type=text], input[type=password] {
	width: 100%;
	padding: 15px;
	margin: 5px 0 22px 0;
	display: inline-block;
	border: none;
	background: #f1f1f1;
}

input[type=text]:focus, input[type=password]:focus {
	background-color: #ddd;
	outline: none;
}

/* Full-width input fields */
select {
	width: 100%;
	padding: 15px;
	margin: 5px 0 22px 0;
	display: inline-block;
	border: none;
	background: #f1f1f1;
}

/* Overwrite default styles of hr */
hr {
	border: 1px solid #f1f1f1;
	margin-bottom: 25px;
}

/* Set a style for the submit button */
.registerbtn {
	background-color: #4CAF50;
	color: white;
	padding: 16px 20px;
	margin: 8px 0;
	border: none;
	cursor: pointer;
	width: 100%;
	opacity: 0.9;
}

.registerbtn:hover {
	opacity: 1;
}

/* Add a blue text color to links */
a {
	color: dodgerblue;
}

/* Set a grey background color and center the text of the "sign in" section */
.signin {
	background-color: #f1f1f1;
	text-align: center;
}
</style>

<script type="text/javascript">
// alert(document.getElementById("token"));
<%-- document.getElementById("token").value="<%=ss%>"; --%>
// 	alert(document.getElementById("token").value);
</script>
</head>
<body onload="getListOfManager()">



	<form action="/action_page.php">
	<input type="hidden" value="<%=ss %>" name="token" id="token"/>
	
	
		<div class="container" width="50%">
			<h1>Register</h1>
			<p>Please fill in this form to create an account.</p> 
			<hr>

			<label for="email"><b>First Name</b></label> <input type="text"
				id="firstName" placeholder="First Name" name="email" required>

			<label for="psw"><b>Last Name</b></label> <input type="text"
				placeholder="Enter Last Name" name="lastName" id="lastName" required>

			<label for="psw-repeat"><b>Mobile Number</b></label> <input
				type="text" placeholder="Enter Mobile Number" name="mobile"
				id="mobile" required> <label for="psw-repeat"><b>Work
					Location</b></label> <select id="workLocation" name="workLocation">
				<option value="Pune">Pune</option>
				<option value="Noida">Noida</option>
				<option value="Mumbai">Mumbai</option>
			</select> 
			
			<label for="psw-repeat"><b>Role</b></label> 
			<select id="role"	name="role">
				<option value="USER">User</option>
				<option value="MANAGER">MANAGER</option>
				<option value="ADMIN">ADMIN</option>
			</select>
			
			<label for="psw-repeat"><b>Manager</b></label> 
			<select id="mgrId"	name="mgrId">
				
			</select>

			<hr>

			<!-- 			<p> -->
			<!-- 				By creating an account you agree to our <a href="#">Terms & -->
			<!-- 					Privacy</a>. -->
			<!-- 			</p> -->

			<button type="button" class="registerbtn" onclick="validateAndSbt();">Register</button>
		</div>

		<!-- 		<div class="container signin"> -->
		<!-- 			<p> -->
		<!-- 				Already have an account? <a href="#">Sign in</a>. -->
		<!-- 			</p> -->
		<!-- 		</div> -->
	</form>



</body>


<script type="text/javascript">
	function getListOfManager() {
// 		alert(document.getElementById("token").value);
		var xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
				//	      document.getElementById("demo").innerHTML = this.responseText;
// 				alert(this.responseText);
				
				var obj = JSON.parse(this.responseText);
				for(i in obj){
					var x = document.getElementById("mgrId");
					var option = document.createElement("option");
					  option.text = obj[i].mgrFirstName +" "+obj[i].mgrLastName;
					  option.value=obj[i].mgrId;
					  x.add(option);
				}
				
			}
		};
		xhttp.open("GET", "http://localhost:8081/ca/reg/managers", true);
		xhttp
		.setRequestHeader(
				'Authorization',
				'Bearer '
						+ document.getElementById("token").value);
		xhttp.send();
		
	}
	
	

	function validateAndSbt() {

		var myObj = {
			"firstName" : document.getElementById("firstName").value,
			"lastName" : document.getElementById("lastName").value,
			"mobile" : document.getElementById("mobile").value,
			"role" : document.getElementById("role").value,
			"workLocation" : document.getElementById("workLocation").value,
			"mgrID":document.getElementById("mgrId").value
		};

		var myJSON = JSON.stringify(myObj);

		var xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
				//	 	      document.getElementById("demo").innerHTML = this.responseText;
				alert(this.responseText);
				document.getElementById("firstName").value="";
				document.getElementById("lastName").value="";
				 document.getElementById("mobile").value="";
			}
		};

		// 		  xhttp.open("PUT", "http://localhost:8081/ca/admin/updateUser", true);
		xhttp.open("POST", "http://localhost:8081/ca/reguser/createUser", true);
		xhttp.setRequestHeader("Content-type", "application/json");
		xhttp
				.setRequestHeader(
						'Authorization',
						'Bearer '
								+ 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI5ODYwMjc3OTgxIiwiaWF0IjoxNTg5ODgzODM1LCJleHAiOjE1ODk5NzAyMzUsIlVzZXJJZCI6MTAwMX0.8j4gVvT5hHOtlD5IGHLxDkWkEf5CwahwZiadOV2Ewnck1dMtHgnyh_4v8F4hZR-4eoK_YnC5ZLRllzKaOnIUZw');
		xhttp.send(myJSON);

	}

	// functionValidateForm(){

	// }

// 	function createUserJSON() {
// 		var jsonReq = {
// 			"firstName" : document.getElementById("firstName").value,
// 			"lastName" : document.getElementById("lastName").value,
// 			"mobile" : document.getElementById("mobile").value,
// 			"role" : document.getElementById("role").value,
// 			"workLocation" : document.getElementById("workLocation").value
// 		};
// 	}
</script>

</html>