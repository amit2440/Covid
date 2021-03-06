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
  function getResponse(userID){
// 	  alert(userID);
	  document.getElementById("userId_id").value=userID;
// 	  alert(document.getElementById("userId_id").value);
	  document.getElementById("FetchFeedbackRequestDTO").submit();
	  
  }
  
//  function doSumitEpass(userId,status,cnt){
// // 	 alert(document.getElementById("token").value);
// // 	 alert('status --->'+status);
// 	 var isAllowed = (status=='true')?false:true;
// // 	 alert('isAllowed---->'+isAllowed);  
// 	    	var myObj = {
// 				"userId" : userId,
// 				"isAllowed":isAllowed,
// 				"toDate":"2020-06-12"
// 			};   
// 	    	var myJSON = JSON.stringify(myObj);
// 	 $.ajax
// 	  ({
// 	    type: "POST",
// 	    url: "/ca/mvc/surveys/1/epasses",
// 	    dataType: 'json',
// 	    contentType:'application/json',
// 	    headers: {
// 	      "Authorization": "Basic " + document.getElementById("token")
// 	    },
// 	    data:myJSON,
// 	    success: function (data,status,xhr){
// 	    	if(isAllowed==true){
// 				document.getElementById(cnt).innerHTML="Allowed";
// 			}else{
// 				document.getElementById(cnt).innerHTML="Not Allowed";
// 			} 
// 	    	location.reload();
// 	    },
// 		error: function (jqXhr, textStatus, errorMessage) {
// 			alert('Error: Issue in assigning ePass! Please contact Admin.!' );
// 		}
// 	  });
//  } 
 
 
 
function doSumitEpass(userId, status,cnt) {
// 	alert('1111');
	 window.open("/ca/mvc/epassPage/"+userId, "_blank", "toolbar=no,scrollbars=no,resizable=yes,top=500,left=500,width=400,height=400");
}
  
  </script>
</head>

<!-- iterating manager -->
<form:form method="POST" id="FetchFeedbackRequestDTO" modelAttribute="fetchFeedbackRequestDTO" action="/ca/mvc/feedBackRes">
	<form:hidden path="userId" id="userId_id" value=""/>
	<form:hidden path="surveyId" id="surveyId_id"  value=""/>
</form:form>



<div class="container">	
	<div class="rTable">
      	<div class="rTableRow">
			<div class="rTableHead"><strong>Admin </strong></div>
		</div>
		<div class="rTableRow">
			<div class="rTableCell">${surveyReportDTO.admin.firstName} &nbsp; &nbsp; ${surveyReportDTO.admin.lastName}</div>
		</div>
	</div>
	<br>
<% int i=0; %>
<c:forEach var="surveyFeedbackDTO" items="${surveyReportDTO.feedbacks}">


	<div class="panel-group">
		 <div class="panel panel-default">
		 	<div class="panel-heading">
		        <h4 class="panel-title">
		        	<div class="rTable">
		        		<div class="rTableRow">
							<div class="rTableHead"><strong>Manager Name</strong></div>
							<div class="rTableHead"><strong>Work Location</strong></div>
						</div>
						<div class="rTableRow">
							<div class="rTableCell">${surveyFeedbackDTO.manager.firstName}&nbsp;${surveyFeedbackDTO.manager.lastName}</div>
							<div class="rTableCell">${surveyFeedbackDTO.manager.workLocation}</div>
						</div>
		        	</div>
		        	<br>
		          	<a data-toggle="collapse" href="#collapse<%=i%>">View response</a>
		        </h4>
		    </div>
		     <div id="collapse<%=i%>" class="panel-collapse collapse">
		        <div class="rTable">
		        		<div class="rTableRow">
							<div class="rTableHead4by4"><strong>User Name</strong></div>
							<div class="rTableHead4by4"><strong>Work Location</strong></div>
							<div class="rTableHead4by4"><strong>Risk Status</strong></div>
							<div class="rTableHead4by4"><strong>ePass Status</strong></div>
						</div><% int cnt = 0; %>
						<c:forEach var="userList" items="${surveyFeedbackDTO.users}">
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
							<div class="rTableCell4by4">${userList.firstName} &nbsp;&nbsp;${userList.lastName}</div>
							<div class="rTableCell4by4" >${userList.workLocation}</div>
<%-- 							<div class="rTableCell4by4" onclick="getResponse(${userList.userId})" ${fontColor} }> ${userList.riskStatus}</div> --%>
							<div class="rTableCell4by4"  ${fontColor} }> ${userList.riskStatus}</div>
							<div class="rTableCell4by4" id="allowed<%=cnt%>" onClick="doSumitEpass('${userList.userId}','${userList.epass.isAllowed}','allowed<%=cnt%>');">
								<c:if test="${userList.epass.isAllowed==true}">Allowed
								</c:if>
							<c:if test="${userList.epass.isAllowed==false}">Not Allowed
								</c:if>
							
							
							</div>
						</div><%cnt++; %>
						</c:forEach>
						
		        </div>
		        
		       
		      </div>
		 </div>
	</div>

	<% i++; %>
</c:forEach>
</p>
</div>	