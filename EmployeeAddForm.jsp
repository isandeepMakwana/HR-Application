<jsp:include page='/MasterPageTopSection.jsp' />
<script src='/stylethree/js/EmployeeAddForm.js'></script>
<form id='cancelAdditionForm' action='/stylethree/Employees.jsp' >
</form>
<script>
function setLeftBar(text){
var designation = document.createElement("p2");
designation.innerHTML=text;
removeATag=document.querySelectorAll(".content-left-panel a")[1];
document.querySelector(".content-left-panel").replaceChild(designation, removeATag);
document.querySelector(".content-left-panel p2").style.fontWeight=1000;
}
function populateDesignations()
{
var xmlHttpRequest=new XMLHttpRequest();
xmlHttpRequest.onreadystatechange=function(){
if(this.readyState==4)
{
if(this.status==200)
{
var responseData = this.responseText;
var designationComboBox=document.getElementById("designationCode");
var obj;
var splits=responseData.split(",");
for(var i=0;i<splits.length;i+=2)
{
obj=document.createElement("option");
obj.value=splits[i];
obj.text=splits[i+1];
designationComboBox.appendChild(obj);
}
}}
};
xmlHttpRequest.open('GET','designations',true)
xmlHttpRequest.send();
}

window.addEventListener('load',populateDesignations);
setLeftBar("Employees");
</script>
<h2>Employee (Add Module)</h2>
<form method='post' action='/stylethree/AddEmployee.jsp' onsubmit='return validateFrom(this)'>
<table>
<tr>
<td>Name</td>
<td><input type='text' id='name' name='name' maxlength='50' size='51'>
<span id='nameErrorSection' style='color:red'></span><br></td>
</tr>
<tr>
<td>Designation</td>
<td>
<select id='designationCode' name='designationCode'>
<option value='-1'>&lt;Select Deseignation&gt;</option>
</select>
<span id='designationCodeErrorSection' style='color:red'></span><br></td>
</td>
</tr>
<tr>
<td>Date of Birth</td>
<td><input type='date' id='dateOfBirth' name='dateOfBirth'>
<span id='dateOfBirthErrorSection' style='color:red'></span><br></td>
</tr>
<tr>
<td>Gender</td>
<td>
<input type='radio' name='gender' id='male' value='M'>
&nbsp; Male
&nbsp;&nbsp;&nbsp;
<input type='radio' name='gender' id='female' value='F'>&nbsp;Female&nbsp;&nbsp;&nbsp;<span id='genderErrorSection' style='color:red'></span>
<br>
</td>
</tr>
<tr>
<td>Indian ?</td>
<td><input type='checkbox' name='isIndian' id='isIndian'></td>
</tr>
<tr>
<td>Basic Salary</td>
<td><input type='text' name='basicSalary' id='basicSalary' maxlength='12' size='16' style='text-align: right;'><span id='basicSalaryErrorSection' style='color: red;'><br></span>
</td>
</tr>
<tr>
<td>PAN Number</td>
<td><input type='text' id='panNumber' name='panNumber' maxlength='15' size='16'>
<span id='panNumberErrorSection' style='color:red'></span><br></td>
</tr>
<tr>
<td>Aadhar Card Number</td>
<td><input type='text' id='aadharCardNumber' name='aadharCardNumber' maxlength='15' size='16'>
<span id='aadharCardNumberErrorSection' style='color:red'></span><br></td>
</tr>
<tr>
<td colsan='2'>
<button type='submit'>Add</button>&nbsp;&nbsp;<button type='Button' onclick='CencelAddition()'>Cancel</button></td>
</tr>
</table>
</form>
<jsp:include page='/MasterPageBottomSection.jsp' />