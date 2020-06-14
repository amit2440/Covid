<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- ${surveyReportDTO} --%>

<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="../css/covidStyle.css">
<link rel="stylesheet" href="../css/bootstrap.min.css">
<script src="../js/jquery.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
<script type="text/javascript">
	function getResponse(userID) {
// 		alert(userID);
		document.getElementById("userId_id").value = userID;
// 		alert(document.getElementById("userId_id").value);
		document.getElementById("FetchFeedbackRequestDTO_id").submit();

	}

	function doSumitEpass(userId, status,cnt) {
		//	 alert(document.getElementById("token").value);
		//	 alert('status --->'+status);
		var isAllowed = (status == 'true') ? false : true;
		//	 alert('isAllowed---->'+isAllowed);  
		var myObj = {
			"userId" : userId,
			"isAllowed" : isAllowed,
			"toDate" : "2020-06-12"
		};
		var myJSON = JSON.stringify(myObj);
		$
				.ajax({
					type : "POST",
					url : "/ca/mvc/surveys/1/epasses",
					dataType : 'json',
					contentType : 'application/json',
					headers : {
						"Authorization" : "Basic "
								+ document.getElementById("token")
					},
					data : myJSON,
					success : function(data, status, xhr) {
						if(isAllowed==true){
							document.getElementById(cnt).innerHTML="Allowed";
						}else{
							document.getElementById(cnt).innerHTML="Not Allowed";
						}
						location.reload();
					},
					error : function(jqXhr, textStatus, errorMessage) {
						alert('Error: Issue in assigning ePass! Please contact Admin.!');
// 						document.getElementById(cnt).innerHTML=isAllowed;
					}
				});
	}
</script>
</head>


<!-- iterating manager -->
<form:form method="POST" id="FetchFeedbackRequestDTO_id" modelAttribute="fetchFeedbackRequestDTO" action="/ca/mvc/feedBackRes">
	<form:hidden path="userId" id="userId_id" value="" />
	<form:hidden path="surveyId" id="surveyId_id" value="" />
</form:form>
<div class="container">
	<div class="rTable">
		<div class="rTableRow">
			<div class="rTableHead">
				<strong>Manager Name</strong>
			</div>
			<div class="rTableHead">
				<strong>Work Location</strong>
			</div>
		</div>
		<div class="rTableRow">
			<div class="rTableCell">${surveyReportDTO.manager.firstName}&nbsp;&nbsp;
				${surveyReportDTO.manager.lastName}</div>
			<div class="rTableCell">${surveyReportDTO.manager.workLocation}</div>
		</div>
	</div>
	<p>Please click on Risk status to view the response and reason for
		Risk Status.</p>

	<div class="rTable">
		<div class="rTableRow">
			<div class="rTableHead4by4">
				<strong>User Name</strong>
			</div>
			<div class="rTableHead4by4">
				<strong>Work Location</strong>
			</div>
			<div class="rTableHead4by4">
				<strong>Risk Status</strong>
			</div>
			<div class="rTableHead4by4">
				<strong>ePass Status</strong>
			</div>
		</div><% int cnt = 0; %>
		<c:forEach var="userList" items="${surveyReportDTO.users}">
			<c:if test="${userList.riskStatus=='H'}">
				<c:set var="fontColor" value="style=\"background-color:red;\""></c:set>
			</c:if>

			<c:if test="${userList.riskStatus=='M'}">
				<c:set var="fontColor" value="style=\"background-color:orange;\""></c:set>
			</c:if>
			<c:if test="${userList.riskStatus=='L'}">
				<c:set var="fontColor" value="style=\"background-color:green;\""></c:set>
			</c:if>
			<c:if test="${userList.riskStatus=='U'}">
				<c:set var="fontColor" value="style=\"background-color:gray;\""></c:set>
			</c:if>
			<div class="rTableRow">
				<div class="rTableCell4by4">${userList.firstName}
					&nbsp;&nbsp;${userList.lastName}</div>
				<div class="rTableCell4by4">${userList.workLocation}</div>
<%-- 				<div class="rTableCell4by4"	onclick="getResponse('${userList.userId}')"${fontColor} }>	${userList.riskStatus}</div> --%>
				<div class="rTableCell4by4"	${fontColor}>	${userList.riskStatus}</div>
				<div class="rTableCell4by4" id="allowed<%=cnt%>"
					onClick="doSumitEpass('${userList.userId}','${userList.epass.isAllowed}','allowed<%=cnt%>');">${userList.epass.isAllowed}</div>
			</div>
			<%cnt++; %>
		</c:forEach>
	</div>

</div>


