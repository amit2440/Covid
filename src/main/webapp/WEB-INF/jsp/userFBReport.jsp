WEll-Come users!!!!

<body onload="UserAction();">

</body>
<script lang="javascript">

function UserAction() {
//     var xhttp = new XMLHttpRequest();
//     xhttp.onreadystatechange = function() {
//          if (this.readyState == 4 && this.status == 200) {
//              alert(this.responseText);
//          }
//     };
//     xhttp.open("GET", "http://localhost:8081/ca/reg/managers", true);
//     xhttp.setRequestHeader("Content-type", "application/json");
//     xmlhttp.setRequestHeader('Authorization', 'Bearer ' + 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI5ODYwMjc3OTgxIiwiaWF0IjoxNTg5ODI0ODE0LCJleHAiOjE1ODk5MTEyMTQsIlVzZXJJZCI6MTAwMX0.VdVzUsmLBOmSXM5hgJEsDK4ZQaA9lAGFDseZPDRk4KanR9iUbEKV7GXR5xkS-cNl6vZwACtWB-HZlOxNYfT3fA');
    	
//     xhttp.send("Your JSON Data Here");

var myObj = {"userId": "1002","firstName": "VirendrKumar", "lastName": "Bankhede"};
var myJSON = JSON.stringify(myObj);

	var xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange=function() {
	    if (this.readyState == 4 && this.status == 200) {
// 	      document.getElementById("demo").innerHTML = this.responseText;
	    	 alert(this.responseText);
	    }
	  };
	 
	  xhttp.open("PUT", "http://localhost:8081/ca/admin/updateUser", true);
	  xhttp.setRequestHeader("Content-type", "application/json");
	  xhttp.setRequestHeader('Authorization', 'Bearer ' + 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI5ODYwMjc3OTgxIiwiaWF0IjoxNTg5ODI2OTY2LCJleHAiOjE1ODk5MTMzNjYsIlVzZXJJZCI6MTAwMX0.irrvkszeMlERH4U1Kx2X1QuinntQPYw-Fv72dPPpMb_BzI9lNJWOI7KYWn_4mVy7OVv5haITlC_EabR4vZ4nYA');
	  xhttp.send(myJSON);
}
</script>