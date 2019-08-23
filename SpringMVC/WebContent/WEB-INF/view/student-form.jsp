<%@ taglib prefix ="form" uri = "http://www.springframework.org/tags/form" %>

<!DOCTYPE html>

<html>
<head>
<title>Student Registration Form</title>
</head>

<body>

<form:form action = "processForm" modelAttribute="student">

<!--  input PATH HAS TO MATCH ATTRIBUTE NAME -->
First Name : <form:input path = "firstName" />
<br><br>
<!--  input PATH HAS TO MATCH ATTRIBUTE NAME -->
Last Name : <form:input path = "lastName" />

<br><br>

<!-- CREATES DROP DOWN MENU OF OPTIONS -->
Country :
<form:select path ="country">
<form:option value ="China" label ="China" />
<form:option value ="India" label ="India" />
<form:option value ="USA" label ="USA" />
<form:option value ="Paris" label ="Paris" />
</form:select>



<br><br>

<input type ="submit" value ="Submit" />

</form:form>

</body>


</html>







