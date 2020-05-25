<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>FeebBack Report</title>
<style>
table {
  font-family: arial, sans-serif;
  border-collapse: collapse;
  width: 100%;
}

td, th {
  border: 1px solid #dddddd;
  text-align: left;
  padding: 8px;
}

tr:nth-child(even) {
  background-color: #dddddd;
}
</style>
</head>
<body>
<jsp:include page="header.jsp" /> 


<br>
<c:if test="${logged_in == 'ADMIN'}">
<br>
Admin Report
<jsp:include page="/WEB-INF/jsp/adminResponse.jsp"></jsp:include>	

</c:if>
<c:if test="${logged_in == 'MANAGER'}">
<br>
Managers Report
	<jsp:include page="/WEB-INF/jsp/mgrResponse.jsp"></jsp:include>
</c:if>

</body>
</html>