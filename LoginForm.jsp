<!DOCTYPE html>

<html lang="en">

<head>

<meta charset="UTF-8">

<title>Login for Hr Application</title>

<link rel='stylesheet' type='text/css' href='/stylethree/css/LoginForm.css'>
<script>
function ValidateUser(){
var user=document.getElementById('userName');
if(user.value.trim().length==0)
{
document.getElementById('error').innerHTML=" Invalid UserName & Password ";
user.focus();
return;
}
var pwd=document.getElementById("password");
if(pwd.value.trim().length==0)
{
document.getElementById('error').innerHTML="Invalid UserName & Password";
pwd.focus();
return;
}
if(pwd.value.trim().length<=4)
{
document.getElementById('error').innerHTML="Password Cantains must be 4 digits";
frm.password.focus();
return;
}
var dataToSend="username="+encodeURI(user.value);
dataToSend=dataToSend+"&password="+encodeURI(pwd.value);
var xmlHttpRequest=new XMLHttpRequest();
xmlHttpRequest.onreadystatechange=function()
{
if(this.readyState==4){
if(this.status==200){
var responseData=this.responseText;
//alert(responseData);
if(responseData.trim()=='successful login')
{
document.getElementById('loginSuccess').submit();
}
else
{
document.getElementById('error').innerHTML="Invalid Username & Password";
}
}
}
}
xmlHttpRequest.open('POST','login',true);
xmlHttpRequest.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
xmlHttpRequest.send(dataToSend);
}
</script>
</head>

<body>

<form method="post" id="loginSuccess" action="/stylethree/index.jsp">

</form>
<div class="main-container">

<div class="header">

<a href='/stylethree/LoginForm.jsp'><img src='/stylethree/images/logo.jpg' class="logo"></a>
<div class="brand-name">&nbsp;&nbsp; Stunning Programmer
</div>
</div>

<div class="content">

<center><h2>Administrator Login Page</h2>

<div class="content-box">

<span id="error" class="error">
</span>

<table >

<tbody>
<tr>
<td style="padding:10px;text-align: left; font-size:20px;">UserName : </td>
                    
<td style="padding:10px;">
<input type="text" name="userName" id="userName" size="35px"></td>
 
</tr>
                
<tr>
                    
<td style="padding:10px;text-align: center;font-size:20px;">Password : </td>
                    
<td style="padding:10px;">
<input type="password" name="password" id="password" size="35px"></td>
                
</tr>
                
<tr>
                    
<td></td>
                    
<td style="padding :10px;width: 120px;">
<button type="button" onclick='ValidateUser()'>Login</button></td>
                
</tr>
            
</tbody>
        
</table>



</div>

</center>

</div>

<div class="footer"> 
<p>&copy; Stunning Programmer 2020</p>

</div>

</div>

</body>

</html>