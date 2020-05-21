
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
	
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
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
</head>
<body>

<form action="/action_page.php" id="loginForm" method="get">
  <div class="container">
    <h1>Register</h1>
    <p>Please fill in this form to create an account.</p>
    <hr>

    <label for="email"><b>Mobile</b></label>
    <input type="text" id="mobile" placeholder="Enter Mobile" name="mobile" required>

    <label for="psw"><b>Password</b></label>
    <input type="password" placeholder="Enter OTP" name="psw" id="pwd" required>

   

    <button type="button" class="registerbtn" onclick="loginRequest()"> Register</button>
  </div>
  
  
</form>

<form action="/ca/mvc/showHomePage" id="homePageForm" method="get">
<input type="hidden" value="" name="token" id="token"/>
</form>
</body>


<script type="text/javascript">

function loginRequest(){
	var myObj = {
			"mobile" : document.getElementById("mobile").value,
			"password" : document.getElementById("pwd").value
			
		};

		var myJSON = JSON.stringify(myObj);

		var xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
				//	 	      document.getElementById("demo").innerHTML = this.responseText;
// 				alert(this.responseText);
				
				var obj = JSON.parse(this.responseText)
				document.getElementById("token").value=obj.accessToken;
				showHomePage();
				
			}
		};

		// 		  xhttp.open("PUT", "http://localhost:8081/ca/admin/updateUser", true);
		xhttp.open("POST", "/ca/signin", true);
		xhttp.setRequestHeader("Content-type", "application/json");
// 		xhttp
// 				.setRequestHeader(
// 						'Authorization',
// 						'Bearer '
// 								+ 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI5ODYwMjc3OTgxIiwiaWF0IjoxNTg5ODgzODM1LCJleHAiOjE1ODk5NzAyMzUsIlVzZXJJZCI6MTAwMX0.8j4gVvT5hHOtlD5IGHLxDkWkEf5CwahwZiadOV2Ewnck1dMtHgnyh_4v8F4hZR-4eoK_YnC5ZLRllzKaOnIUZw');
		xhttp.send(myJSON);
}


function showHomePage(){
// 	document.forms["homePageForm"].action="/ca/mvc/showList";
	document.forms["homePageForm"].action="/ca/mvc/home";
	document.forms["homePageForm"].submit();
}
</script>
</html>
