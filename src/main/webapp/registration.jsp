<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Sign Up Form</title>

</head>
<body>
<input type="hidden" id="ststus" value="<%= request.getAttribute("status")%>">
<h1>Sign Up</h1>
<form method="post" action="register">
  <label for="name">Name:</label><br>
  <input type="text" id="name" name="name"><br>
   <label for="email">Email:</label><br>
  <input type="email" id="email" name="email"><br>
   <label for="password">Password:</label><br>
  <input type="password" id="password" name="password"><br><br>
   <label for="cnumber">Contact no:</label><br>
  <input type="text" id="cnumber" name="cnumber"><br><br>
 
  <input type="Submit" value="log in">
</form>
<!-- JS for message --> 
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<link rel="stylesheet" href="alert/dist/sweetalert.css">
	<script type="text/javascript">
	var status = d0cument.getElementById("status").value;
	is(status == "Success"){
		swal("Account created successfully");
	}
	</script>
	
</body>

</html>