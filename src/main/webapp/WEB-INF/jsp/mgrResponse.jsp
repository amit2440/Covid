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

<!-- iterating manager -->
<%-- ${surveyReportDTO } --%>
<%-- <c:forEach var="surveyFeedbackDTO" items="${SurveyFeedbackDTO.feedbacks}"> --%>

	
	<table>
		<tr>
			<th>Manager Name</th>
			<th>Work Location</th>
		</tr>
		<tr>
			<td>${surveyReportDTO.manager.firstName} &nbsp;&nbsp; ${surveyReportDTO.manager.lastName}</td>
			<td>${surveyReportDTO.manager.workLocation}</td>
		</tr>
	</table> <br>
	<p>
		Please click on Risk status to view the response and reason for Risk Status.
	</p>
	<table>
		<tr>
			<th>User Name</th>
			<th>Work Location</th>
			<th>Risk Status</th>
			<th>ePass Status</th>
		</tr>
		<c:forEach var="userList" items="${surveyReportDTO.users}">
					
			<c:if test="${userList.riskStatus=='H'}">
				<c:set var="fontColor" value="style=\"background-color:red;\""></c:set>
			</c:if>
					
			<c:if test="${userList.riskStatus!='H'}">
				<c:set var="fontColor" value=""></c:set>
			</c:if>
			<tr>
				<td>${userList.firstName} &nbsp;&nbsp;${userList.lastName} </td>
				<td>${userList.workLocation}</td>
				<td ${fontColor} ><a href="#" onclick="viewSurveyResponse()" >${userList.riskStatus}</a></td>
				<td>${userList.epass.isAllowed}</td>
			</tr>
		</c:forEach>
	</table>
	<br>
	
	<script type="text/javascript">
// <!--
function viewSurveyResponse(){
	document.forms["homePageForm"].action="/ca/mvc/feedBackRes";
	document.forms["homePageForm"].method="post";
	document.forms["homePageForm"].submit();
}
-->
</script>
<%-- </c:forEach> --%>