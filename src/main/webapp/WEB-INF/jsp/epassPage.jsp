<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String ss = request.getParameter("token");
	String userId = request.getParameter("userId");
	if(ss==null ||  ss.isEmpty()){
		System.err.println("sssssssssssssssssssssssssss");
		ss = (String)session.getAttribute("token");
	}else{
		System.err.println("HOHOHOHOHOHOHO -----"+ss);
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
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
</head>
<body>
	<form:form method="POST" id="ePassRequestDTOID"
		modelAttribute="ePassRequestDTO" action="/ca/mvc/sub">
		<form:hidden path="userId" id="userId_id" value="${ePassDTO.user.userId }"/>
		<form:hidden path="fromDate" id="fromDate_id"/>
		<form:hidden path="toDate" id="toDate_id"/>
		<form:hidden path="isAllowed" id="isAllowedId"/>
		
		<input type="hidden" value="<%=ss%>" name="token" id="token" />
		<input type="hidden" name="survey_id" id="surveyId_id" value="${ePassDTO.survey.surveyId }" />

		
		<div class="container" width="50%">
			<h1>Pass Assignment</h1>
			<p>Please fill in this form to assign ePass.   === ${ePassDTO.survey.surveyId }</p> 
			<hr>
			<c:if test="${ePassDTO.isAllowed}">
				<c:set var="checked" value="checked"></c:set>
			</c:if>
			<c:if test="!${ePassDTO.isAllowed}">
				<c:set var="checked" value=""></c:set>
			</c:if>
			<label for="email"><b>ePass Allowed </b></label> <input type="checkbox" name="isAllowed" id="isAllowedf_id"  ${checked }/> <br><br>
				
			<label for="email"><b>From Date :</b></label><input type="date" name="fromDate" value="${ePassDTO.fromDate}" id="fromDatef_id"><br><br>
			<label for="email"><b>To Date :</b></label> <input type="date" name="toDate" value="${ePassDTO.toDate}"  id="toDatef_id"> <br><br>
		
			<input type="button" value="Assign" onclick="doSubmit()" />
		</div>
		
	</form:form>
</body>

<script type="text/javascript">

function doSubmit(){
// 	alert(document.getElementById("isAllowedf_id").checked);
	document.getElementById("fromDate_id").value=document.getElementById("fromDatef_id").value;
	document.getElementById("toDate_id").value=document.getElementById("toDatef_id").value;
	document.getElementById("isAllowedId").value=document.getElementById("isAllowedf_id").value;
// 	document.getElementById("ePassRequestDTOID").action="/ca/mvc/surveys/4/epasses";
// 	document.getElementById("ePassRequestDTOID").submit();

// 	 alert(document.getElementById("fromDate_id").value);
	 var surveyId=document.getElementById("surveyId_id").value;
// 	 alert("surveyId--->"+surveyId);
	 var isAllowed = document.getElementById("isAllowedf_id").checked; //(status=='true')?false:true;
// 	 alert('isAllowed---->'+isAllowed);  
   	var myObj = {
		"userId" : document.getElementById("userId_id").value,
		"isAllowed":isAllowed,
		"toDate":document.getElementById("toDatef_id").value,
		"fromDate":document.getElementById("fromDatef_id").value
		
	};   
   	var myJSON = JSON.stringify(myObj);
   	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
// 		alert('this.readyState----'+this.readyState);
		if (this.readyState == 4) {
		//	 	      document.getElementById("demo").innerHTML = this.responseText;
// 			alert(this.responseText);
// 			showHomePage();
			if(this.status == 201){
				 window.opener.location.reload();
				window.close();	
			}else{
				alert("Error : UNPROCESSABLE_ENTITY");
			}
		}else{
		}
	};
	xhttp.open("POST", "/ca/mvc/surveys/"+surveyId+"/epasses", true);
	xhttp.setRequestHeader("Content-type", "application/json");
	xhttp.setRequestHeader(
					'Authorization',
					'Bearer '
							+ document.getElementById("token").value);
	xhttp.send(myJSON);  	
}

</script>
</html>