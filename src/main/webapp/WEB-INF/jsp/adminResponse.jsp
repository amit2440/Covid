<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- ${surveyReportDTO} --%>

<head>
<style>
table {
	font-family: arial, sans-serif;
	border-collapse: collapse;
	width: 70%;
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
<table>
		<tr>
			<th>Admin </th>
			<th>Work Location</th>
		</tr>
		<tr>
			<td>${surveyReportDTO.admin.firstName} &nbsp; &nbsp; ${surveyReportDTO.admin.lastName}</td>
			<td>${surveyReportDTO.admin.workLocation}</td>
		</tr>
	</table>
	<BR>
<!-- iterating manager -->
<c:forEach var="surveyFeedbackDTO" items="${surveyReportDTO.feedbacks}">

	
	<table>
		<tr>
			<th>Manager First Name</th>
			<th>Last Name</th>
			<th>Work Location</th>
		</tr>
		<tr>
			<td>${surveyFeedbackDTO.manager.firstName}</td>
			<td>${surveyFeedbackDTO.manager.lastName}</td>
			<td>${surveyFeedbackDTO.manager.workLocation}</td>
		</tr>
	</table>
	<p>
	<table>
		<tr>
			<th>User Name</th>
			<th>Work Location</th>
			<th>Risk Status</th>
			<th>ePass Status</th>
		</tr>
		<c:forEach var="userList" items="${surveyFeedbackDTO.users}">
					
			<c:if test="${userList.riskStatus=='H'}">
				<c:set var="fontColor" value="style=\"background-color:red;\""></c:set>
			</c:if>
					
			<c:if test="${userList.riskStatus!='H'}">
				<c:set var="fontColor" value=""></c:set>
			</c:if>
			<tr>
				<td>${userList.firstName} &nbsp;&nbsp;${userList.lastName}</td>
				<td>${userList.workLocation}</td>
				<td ${fontColor} }> ${userList.riskStatus}</td>
				<td>${userList.epass.isAllowed}</td>
			</tr>
		</c:forEach>
	</table>
	</p>
	<br>
</c:forEach>