<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	String ss = null;//response.getHeader("Authorization");
	ss = request.getParameter("token");
%>
<html>
<body>
<jsp:include page="header.jsp" /> 
    <h1>File Upload with Jersey</h1>
  
    <form action="/ca/mvc/uploadFile" method="post" enctype="multipart/form-data">
  		<input type="hidden" id="token" name="token" value='<%=ss%>' %>
       <p>
        Select a file : <input type="file" name="file" size="45" />
       </p>
  
       <input type="submit" value="Upload It" />
    </form>
  
</body>
</html>
