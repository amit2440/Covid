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
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
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
<script src="../js/common.js"></script>
</head>
<body>
	<jsp:include page="header.jsp" />

	<form action="/action_page.php">
		<input type="hidden" value="<%=ss%>" name="token" id="token" />
	
		<div class="container" width="50%">
			<h1>Register</h1>
				<p>Please select Date and Location for the list of user having Epass Assigned for the selected Date.</p> 
			<hr>
			
			<label for="email"><b>Pass Allowed :</b></label>
			<input type="date" name="fromDate"  id="fromDatef_id">
			
			<label for="psw-repeat"><b>Work	Location</b></label> 
			<select id="workLocation" name="workLocation">
				<option value="Pune">Pune</option>
				<option value="Noida">Noida</option>
				<option value="Mumbai">Mumbai</option>
			</select> 

			<input type="button" value="search" onclick="search();">
			<hr>
			
			<div>Search Results :</div> </br>
			<b><div id="countDiv" style="Color:Red"> </div></b> </br> 
			<div> 
			<table border="1" width="70%" id="resultTable">
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Manager</th>
				</tr>

			</table>

		</div>
		</div>
		
	</form>
</body>
<script type="text/javascript">
	function search() {
		var x = document.getElementById("resultTable").rows.length;
		document.getElementById("countDiv").innerHTML = "Record Count : 0";
		if (!validateForm()) {
			return false;
		}
		
		for (res = x; res > 1; res--) {
			if (res == 1) {
				break;
			}
			document.getElementById("resultTable").deleteRow(1);
		}
		var myObj = {
			"toDate": document.getElementById("fromDatef_id").value ,
			"location":document.getElementById("workLocation").value 
		};

		var myJSON = JSON.stringify(myObj);
// 				alert(myJSON);
		var xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
// 								alert(this.responseText);
				var obj = JSON.parse(this.responseText);
// 				alert(document.getElementById("countDiv").innerHTML);
				var table = document.getElementById("resultTable");
// 				alert("length--"+obj.epassDTOList);
				if(typeof obj.epassDTOList !='undefined' && obj.epassDTOList.length>0){
					document.getElementById("countDiv").innerHTML = "Record Count : "+obj.count;
					for (i in obj.epassDTOList) {
						var row = table.insertRow(1);
						var cell0 = row.insertCell(0);
						var cell1 = row.insertCell(1);
						var cell2 = row.insertCell(2);
//	 					var cell3 = row.insertCell(3);
						
						cell0.innerHTML = obj.epassDTOList[i].user.firstName;
						cell1.innerHTML = obj.epassDTOList[i].user.lastName;
						cell2.innerHTML = obj.epassDTOList[i].user.managerName;
//	 					if (obj[i].managerName == ''
//	 							|| obj[i].managerName == 'undefined'
//	 							|| obj[i].managerName == null) {
//	 						cell4.innerHTML = "";
//	 					} else {
//	 						cell4.innerHTML = obj[i].managerName
//	 					}
//	 					cell5.innerHTML = obj[i].mobile;
					}
				}else{
// 					alert('12');
					var row = table.insertRow(1);
					var cell0 = row.insertCell(0);
					cell0.id="mCell";
					document.getElementById("mCell").colSpan = "3"
					cell0.innerHTML = "No Result Found";
				}
				
			}
		};
		xhttp.open("POST", "/ca/admin/userEpassRpt", true);
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
		document.forms["homePageForm"].method = "post";
		document.forms["homePageForm"].submit();

	}

	function validateForm() {
// 		document.getElementById("fromDatef_id").innerHTML = "";
// 		document.getElementById("lname").innerHTML = "";
// 		document.getElementById("mob").innerHTML = "";
// 		var fname = document.getElementById("firstName").value;
// 		if (fname != "") {
// 			// 				alert(allLetter(fname));
// 			if (!allLetter(fname)) {
// 				document.getElementById("fname").innerHTML = "Please enter character's in First Name filed.";
// 				return false;
// 			}

// 		}

// 		var lname = document.getElementById("lastName").value;
// 		if (lname != "") {
// 			if (!allLetter(lname)) {
// 				document.getElementById("lname").innerHTML = "Please enter character's in Last Name filed.";
// 				return false;
// 			}
// 		}

// 		var mob = document.getElementById("mobile").value;
// 		if (mob != "") {
// 			if (!phonenumber(mob)) {
// 				document.getElementById("mob").innerHTML = "Mobile Number is 10 digit with no area code or special character.";
// 				return false;
// 			}
// 		}
		return true;
	}
</script>
</html>