<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	String ss = request.getParameter("token");
	String userId = request.getParameter("userId");

	    String tt = response.getHeader("Authorization");
	    System.err.println("tt----->"+tt);
	session.setAttribute("token", tt);

	String ttRe = request.getHeader("Authorization");
	//     System.err.println("ttRe----->"+ttRe);
%>
<!DOCTYPE html>
<html>
<head>
<style>
ul {
	list-style-type: none;
	margin: 0;
	padding: 0;
	overflow: hidden;
	background-color: #333;
}

li {
	float: left;
}

li a {
	display: block;
	color: white;
	text-align: center;
	padding: 14px 16px;
	text-decoration: none;
}

li a:hover {
	background-color: #111;
}
</style>

<script type="text/javascript">
	function addUser() {
		document.forms["homePageForm"].action = "/ca/mvc/showList";
		document.forms["homePageForm"].submit();
	}

	function searchUsers() {
		document.forms["homePageForm"].action = "/ca/mvc/search";
		document.forms["homePageForm"].submit();
	}

	function doFeedBackReport() {
		document.forms["homePageForm"].action = "/ca/mvc/feedBack";
		document.forms["homePageForm"].method = "post";
		document.forms["homePageForm"].submit();
	}

	function bulkUpload() {
		document.forms["homePageForm"].action = "/ca/mvc/bulkUpload";
		document.forms["homePageForm"].method = "post";
		document.forms["homePageForm"].submit();
	}

	function testjspFile() {
		document.forms["homePageForm"].action = "/ca/mvc/testjsp";
		document.forms["homePageForm"].method = "post";
		document.forms["homePageForm"].submit();
	}
	
	
// 	alert(performance.navigation.type);
	
</script>
</head>
<body>
	<ul>
		<li><a class="active" onclick="addUser();">Add User</a></li>
		<li><a onclick="searchUsers();">Search</a></li>
		<li><a onclick="bulkUpload()">Load User File</a></li>
		<li><a onclick="doFeedBackReport()">Report</a></li>
		<li><a onclick="testjspFile()">test Jsp</a></li>

	</ul>
	<form action="/ca/mvc/showHomePage" id="homePageForm" method="post">
		<input type="hidden" value="<%=ss%>" name="token" id="token" /> <input
			type="hidden" name="userId" id="userId" value="<%=userId%>">
	</form>
</body>
</html>