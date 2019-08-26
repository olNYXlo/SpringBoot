<%@ taglib prefix ="form" uri = "http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<title>======Registration Form=====</title>
</head>

<script>  
function validateform(){  
var ID=document.myform.ID.value; 
var validID = "@gmail.com";
var validID2 = "@hotmail.com";


  
if (ID==null || ID==""){  
  alert("ID can't be blank");  
  return false;  
}else if(!ID.includes(validID) && !ID.includes(validID2)){  
  alert("Please enter a valid ID"); 
  return false;  
  }
}  
</script> 




<body>

<form:form name = "myform" action = "processForm" modelAttribute = "User" onsubmit = "return validateform()">

<!--  input PATH HAS TO MATCH ATTRIBUTE NAME -->
First Name : <form:input path = "firstName" />
<br><br>
<!--  input PATH HAS TO MATCH ATTRIBUTE NAME -->
Last Name : <form:input path = "lastName" />
<br><br>
<!--  input PATH HAS TO MATCH ATTRIBUTE NAME -->
User ID : <form:input path = "ID" />

<!-- 
<validator type = "requiredstring">
<param name = "ID"> ${User.ID }</param>
<message> ID is required. </message>

</validator>

<validator type = "regex">
<message> Please enter a valid ID </message>

</validator>
 -->



<br><br>
<!--  input PATH HAS TO MATCH ATTRIBUTE NAME -->
Address : <form:input path = "address" />
<br><br>

Favourite Language :

<form:radiobuttons path="favouriteLanguage" items="${User.languageOptions}" />

<!-- 
English <form:radiobutton path="favouriteLanguage" value="English" />
Chinese <form:radiobutton path="favouriteLanguage" value="Chinese" />
Japanese <form:radiobutton path="favouriteLanguage" value="Japanese" />
Korean <form:radiobutton path="favouriteLanguage" value="Korean" />
Thai <form:radiobutton path="favouriteLanguage" value="Thai" />

 -->




<br><br>

<!-- CREATES DROP DOWN MENU OF OPTIONS -->
Country :
<form:select path ="country">
<form:options items="${countryOptions}" />
<!-- 
<form:option value ="China" label ="China" />
<form:option value ="India" label ="India" />
<form:option value ="USA" label ="USA" />
<form:option value ="Paris" label ="Paris" />

 -->

</form:select>



<br><br>


<input type ="submit" />
</form:form>

</body>
</html>