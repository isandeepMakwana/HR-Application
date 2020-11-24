<jsp:include page='/MasterPageTopSection.jsp' />
<script>
function setLeftBar(text){
var designation = document.createElement("p2");
designation.innerHTML=text;
removeATag=document.querySelectorAll(".content-left-panel a")[1];
document.querySelector(".content-left-panel").replaceChild(designation, removeATag);
document.querySelector(".content-left-panel p2").style.fontWeight=1000;
}
setLeftBar("Employees");
</script>
<h2>Employee (Delete Module)</h2>
<form methon='post' action='/stylethree/EmployeeDeleteNotification.jsp' >
</td>
Name : 
<b>${name}</b><br>
Designation : 
<b>${designation}</b><br>
Date Of Birth : 
<b>${dateOfBirth}</b><br>
Gender : 
<b>${gender}</b><br>
Nationality : 
<b>${isIndian}</b><br>
Basic salary : 
<b>${basicSalary}</b><br>
PAN Number : 
<b>${panNumber}</b><br>
Aadhar Card Number : 
<b>${aadharCardNumber}</b><br>
Are you sure you want to delete this Employee ?<br><br>
<table><tr><td>
<button type='submit'>Yes</button>
</form>
</td><td>
<form action='/stylethree/Employees.jsp'>
<button type='submit' >NO</button>
</form>
</td></tr></table>
<jsp:include page='/MasterPageBottomSection.jsp' />