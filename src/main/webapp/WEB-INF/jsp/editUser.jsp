<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<%
	String ss = request.getParameter("token");
	String userId = request.getParameter("userId");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit User</title>
<script src="../js/common.js"></script>
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

	<jsp:include page="header.jsp" />

	<form action="/action_page.php">
		<input type="hidden" value="<%=ss%>" name="token" id="token" /> <input
			type="hidden" value="<%=userId%>" name="userId" id="userId" />


		<div class="container" width="50%">
			<h1>Register</h1>
			<p>Please fill in this form to create an account.</p>
			<hr>

			<label for="email"><b>First Name</b></label> <input type="text"
				id="firstName" placeholder="First Name" name="email" required>
			<p id="fname" style="color: red"></p>
			<label for="psw"><b>Last Name</b></label> <input type="text"
				placeholder="Enter Last Name" name="lastName" id="lastName" required>
			<p id="lname" style="color: red"></p>
			<label for="psw-repeat"><b>Mobile Number</b></label> <input
				type="text" placeholder="Enter Mobile Number" name="mobile"
				 id="mobile" required>
			<p id="mob" style="color: red"></p>
			<label for="psw-repeat"><b>Work Location</b></label> <select
				id="workLocation" name="workLocation">
				<option value="Pune">Pune</option>
				<option value="Noida">Noida</option>
				<option value="Mumbai">Mumbai</option>
			</select> <label for="psw-repeat"><b>Role</b></label> <select id="role"
				name="role">
				<option value="USER">User</option>
				<option value="MANAGER">MANAGER</option>
				<option value="ADMIN">ADMIN</option>
			</select> <label for="psw-repeat"><b>Manager</b></label> <select id="mgrId"
				name="mgrId">

			</select>

			<hr>


			<button type="button" class="registerbtn" onclick="validateAndSbt();">Update</button>
		</div>


	</form>



</body>


<script type="text/javascript">
	function getListOfManager() {
		var xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
				var obj = JSON.parse(this.responseText);
				for(i in obj){
					var x = document.getElementById("mgrId");
					var option = document.createElement("option");
					  option.text = obj[i].mgrFirstName +" "+obj[i].mgrLastName;
					  option.value=obj[i].mgrId;
					  x.add(option);
				}
				search('<%=userId%>');
			}
		};
		xhttp.open("GET", "/ca/reg/managers", false);
		xhttp
		.setRequestHeader(
				'Authorization',
				'Bearer '
						+ document.getElementById("token").value);
		xhttp.send();
		
	}
	
	function search(mobNumber) {
		
		var myObj = {
			"userId" : "" + mobNumber + ""
		};

		var myJSON = JSON.stringify(myObj);
		// 		alert(myJSON);
		var xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
// 								alert(this.responseText);
				var obj = JSON.parse(this.responseText);
				var table = document.getElementById("resultTable");
				for (i in obj) {
					document.getElementById("firstName").value = obj[i].firstName;
					document.getElementById("lastName").value = obj[i].lastName;
					document.getElementById("role").value = obj[i].role;
					document.getElementById("mgrId").value= obj[i].mgrID;
					document.getElementById("mobile").value = obj[i].mobile;
					document.getElementById("workLocation").value = obj[i].workLocation;
// 					alert(document.getElementById("workLocation").value + " " +document.getElementById("mobile").value +" "+document.getElementById("mgrId").value);
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


	function validateAndSbt() {
		if(!validateForm()){
			return false;
		}
		
		var myObj = {
			"firstName" : document.getElementById("firstName").value,
			"lastName" : document.getElementById("lastName").value,
			"mobile" : document.getElementById("mobile").value,
			"role" : document.getElementById("role").value,
			"workLocation" : document.getElementById("workLocation").value,
			"mgrID":document.getElementById("mgrId").value,
			"userId":document.getElementById("userId").value
		};

		var myJSON = JSON.stringify(myObj);

		var xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
				//	 	      document.getElementById("demo").innerHTML = this.responseText;
				alert(this.responseText);
				showHomePage();
			}
// 			else{
// 				alert("Error : UNPROCESSABLE_ENTITY");
// // 				break;
// 			}
		};

		// 		  xhttp.open("PUT", "http://localhost:8081/ca/admin/updateUser", true);
		xhttp.open("PUT", "/ca/admin/updateUser", true);
		xhttp.setRequestHeader("Content-type", "application/json");
		xhttp
				.setRequestHeader(
						'Authorization',
						'Bearer '
								+ document.getElementById("token").value);
		xhttp.send(myJSON);

	}

	function validateForm(){
		document.getElementById("fname").innerHTML="";
		document.getElementById("lname").innerHTML="";
		document.getElementById("mob").innerHTML="";
		var fname = document.getElementById("firstName").value;
		if(fname!=""){
// 				alert(allLetter(fname));
			if(!allLetter(fname)){
				document.getElementById("fname").innerHTML="First Name is required and character's only filed.";
				return false;
			}
			
		}else{
			document.getElementById("fname").innerHTML="First Name is required and character's only filed.";
			return false;
		}
		
		var lname = document.getElementById("lastName").value;
		if(lname!=""){
			if(!allLetter(lname)){
				document.getElementById("lname").innerHTML="Last Name is required and character's only filed.";
				return false;
			}
		}else{
			document.getElementById("lname").innerHTML="Last Name is required and character's only filed.";
			return false;
		}
		
		var mob = document.getElementById("mobile").value;
		if(mob!=""){
			if(!phonenumber(mob)){
				document.getElementById("mob").innerHTML="Mobile Number is required and 10 digit with no area code or special character.";
				return false;
			}
		}else{
			document.getElementById("mob").innerHTML="Mobile Number is required and 10 digit with no area code or special character.";
			return false;
		}
		return true;
	}

// 	function createUserJSON() {
// 		var jsonReq = {
// 			"firstName" : document.getElementById("firstName").value,
// 			"lastName" : document.getElementById("lastName").value,
// 			"mobile" : document.getElementById("mobile").value,
// 			"role" : document.getElementById("role").value,
// 			"workLocation" : document.getElementById("workLocation").value
// 		};
// 	}

function showHomePage(){
// 	document.forms["homePageForm"].action="/ca/mvc/showList";
	document.forms["homePageForm"].action="/ca/mvc/home";
	document.forms["homePageForm"].method="get";
	document.forms["homePageForm"].submit();
}
</script>

</html>