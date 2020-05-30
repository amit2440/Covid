<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	 
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- ${surveyReportDTO} --%>
<html>
<head>

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="../css/covidStyle.css">
	<link rel="stylesheet" href="../css/bootstrap.min.css">
  <script src="../js/jquery.min.js"></script>
  <script src="../js/bootstrap.min.js"></script>
  
  <script type="text/javascript">
  function getResponse(userID){
	  alert(userID);
	  document.getElementById("userId_id").value=userID;
	  alert(document.getElementById("userId_id").value);
	  document.getElementById("FetchFeedbackRequestDTO").submit();
	  
  }
  </script>
</head>
<body>
<jsp:include page="header.jsp" /> 
<!-- iterating manager -->
<form:form method="POST" id="FetchFeedbackRequestDTO" modelAttribute="fetchFeedbackRequestDTO" action="/ca/mvc/feedBackRes">
	<form:hidden path="userId" id="userId_id" value=""/>
	<form:hidden path="surveyId" id="surveyId_id"  value=""/>
</form:form>
<div class="container">	
		
	<div class="rTable">
		<div class="rTableRow">
			<div class="rTableHead100" ><strong>User Info </strong></div>
		</div>
      	<div class="rTableRow">
			<div class="rTableHead"><strong>User Name </strong></div>
			<div class="rTableHead"><strong>Manager Name </strong></div>
		</div>
		<div class="rTableRow">
			<div class="rTableCell">${userInfo.firstName} &nbsp; &nbsp; ${userInfo.lastName}</div>
			<div class="rTableCell">${userInfo.managerName} </div>
		</div>
	</div>
	<br>
	<div class="rTable">
		<div class="rTableRow">
			<div class="rTableHead100" ><strong>Question and Answer response for user </strong></div>
		</div>
	</div>
	<% int i=0; %>
	<c:forEach var="feedbackResponseDTO" items="${feedbackDTO.feedbacks}">
		<div class="panel-group">
		 <div class="panel panel-default">
		 	<div class="panel-heading">
		        <h4 class="panel-title">
		        	<div class="rTable">
		        		<div class="rTableRow">
							<div class="rTableHead100" ><strong>Question</strong></div>
							
						</div>
						<div class="rTableRow">
							<div class="rTableCell100">${feedbackResponseDTO.question}</div>
						</div>
		        	</div>
		        	<br>
		          	<a data-toggle="collapse" href="#collapse<%=i%>">View Response</a>
		        </h4>
		    </div>
		     <div id="collapse<%=i%>" class="panel-collapse collapse">
		        <div class="rTable">
		        		<div class="rTableRow">
							<div class="rTableHead"><strong>Answer Submitted</strong></div>
<!-- 							<div class="rTableHead4by4"><strong>Work Location</strong></div> -->
							<div class="rTableHead"><strong>Risk Status</strong></div>
<!-- 							<div class="rTableHead4by4"><strong>ePass Status</strong></div> -->
						</div>
						<c:forEach var="OptionDTO" items="${feedbackResponseDTO.answers}">
						
						<div class="rTableRow">
							<div class="rTableCell">${OptionDTO.displayName }</div>
							<div class="rTableCell" >
								<c:if test="${OptionDTO.risk=='H'}">
									<c:set var="fontColor" value="High"></c:set>
								</c:if>
								<c:if test="${OptionDTO.risk=='M'}">
									<c:set var="fontColor" value="Medium"></c:set>
								</c:if>
								<c:if test="${OptionDTO.risk=='L'}">
									<c:set var="fontColor" value="Low"></c:set>
								</c:if>
								<c:if test="${OptionDTO.risk=='U'}">
									<c:set var="fontColor" value="Survey not Done"></c:set>
								</c:if>
									${fontColor}
							</div>
<!-- 							<div class="rTableCell4by4" > 333</div> -->
<!-- 							<div class="rTableCell4by4" >4444</div> -->
						</div>
						</c:forEach>
		        </div>
		        
		       
		      </div>
		 </div>
	</div>
	<% i++; %>
	</c:forEach>
</p>
</div>	
</body>
</html>