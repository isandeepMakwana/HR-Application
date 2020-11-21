<jsp:include page='/MasterPageTopSection.jsp' />
<form id='cancelAdditionForm' action='/stylethree/Employees.jsp' >
</form>
<form id='submitAdditionForm' action='/stylethree/AddEmployeeNotification.jsp'>
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
setLeftBar("Employees");
function CencelAddition(){
document.getElementById('cancelAdditionForm').submit();
}
function SubmitAddition(){
document.getElementById('submitAdditionForm').submit();
}

window.addEventListener('load',populateDesignations);
function valid(){
found =true;
var nameErrorSection=document.getElementById('nameErrorSection');
nameErrorSection.innerHTML='';
var name=document.getElementById('name').value;
if(name==null|| name.length==0){
found=false;
nameErrorSection.innerHTML='Name required';
}
var designationCode=document.getElementById('designationCode').value;
var designationCodeErrorSection=document.getElementById('designationCodeErrorSection');
designationCodeErrorSection.innerHTML='';
if (designationCode==-1) 
{
found=false;
designationCodeErrorSection.innerHTML='Select designations';
}
var dateOfBirth=document.getElementById('dateOfBirth').value;
var dateOfBirthErrorSection=document.getElementById('dateOfBirthErrorSection');
dateOfBirthErrorSection.innerHTML='';
if (dateOfBirth.length==0) 
{found=false;
dateOfBirthErrorSection.innerHTML='Please enter date of birth';
}
var male=document.getElementById('male').checked;
var female=document.getElementById('female').checked;
var genderErrorSection=document.getElementById('genderErrorSection');
genderErrorSection.innerHTML='';
if(male==false && female==false)
{found=false;
genderErrorSection.innerHTML=' Please select gender';
}
var isIndian=document.getElementById('isIndian').value;
var basicSalary=document.getElementById('basicSalary').value;
var basicSalaryErrorSection=document.getElementById('basicSalaryErrorSection');
basicSalaryErrorSection.innerHTML='';
if(basicSalary==0)
{found=false;
basicSalaryErrorSection.innerHTML='Basic Salary required';
}
else
{
var v='0123456789.';
var e=0;
isBasicSalaryValid=true;
while(e<basicSalary.length)
{
if(v.indexOf(basicSalary.charAt(e))==-1)
{
basicSalaryErrorSection.innerHTML='Invalid basic salary';
isBasicSalaryValid=false;
	break;
found=false;
}e++;
}
if(isBasicSalaryValid)
{
var dot=basicSalary.indexOf('.');
if (dot!=-1)
{
var numberofFranctions=basicSalary.length-(dot+1);
if(numberofFranctions>2)
{
basicSalaryErrorSection.innerHTML='Invalid basic salary';
found=false;
}
}
}
}
var panNumber=document.getElementById('panNumber').value;
var panNumberErrorSection=document.getElementById('panNumberErrorSection');
panNumberErrorSection.innerHTML='';
if(panNumber.length==0)
{
found=false;
panNumberErrorSection.innerHTML='PAN number is required';
}
var aadharCardNumber=document.getElementById('aadharCardNumber').value;
var panNumberErrorSection=document.getElementById('aadharCardNumberErrorSection');
aadharCardNumberErrorSection.innerHTML='';
if(aadharCardNumber.length==0){found=false;
aadharCardNumberErrorSection.innerHTML='Aadhar Card Number is required';
}

if(found==true)
{
var dataToSend="name="+encodeURI(name);
dataToSend=dataToSend+"&designationCode="+encodeURI(designationCode);
dataToSend=dataToSend+"&dateOfBirth="+encodeURI(dateOfBirth);
var gender;
if(male)
{
gender='M';
}
else{
gender='F';
}
dataToSend=dataToSend+"&gender="+encodeURI(gender);
if(isIndian=='on')
{
isIndian=true;
}
else
{
isIndian=false
}
dataToSend=dataToSend+"&isIndian="+encodeURI(isIndian);
dataToSend=dataToSend+"&basicSalary="+encodeURI(basicSalary);
dataToSend=dataToSend+"&panNumber="+encodeURI(panNumber);
dataToSend=dataToSend+"&aadharCardNumber="+encodeURI(aadharCardNumber);
var xmlHttpRequest=new XMLHttpRequest();
xmlHttpRequest.onreadystatechange=function(){
if(this.readyState==4){
if(this.status==200){
var responseData=this.responseText;
if(responseData.trim()=='Employee added')
{
SubmitAddition();
}

//alert(responseData);
var splits=responseData.split(",");
if(splits[0].trim()!='panNumber' && splits[0].trim()=='aadharCardNumber')
{
aadharCardNumberErrorSection.innerHTML='Aadhar Card Number is exists';
}
if(splits[0].trim()=='panNumber' && splits[0].trim()!='aadharCardNumber')
{
panNumberErrorSection=document.getElementById('panNumberErrorSection');
panNumberErrorSection.innerHTML='PAN number is exists';
}
if(splits[0].trim()=='both')
{
panNumberErrorSection=document.getElementById('panNumberErrorSection');
panNumberErrorSection.innerHTML='PAN number is exists';
aadharCardNumberErrorSection.innerHTML='Aadhar Card Number is exists';
}
}
}
}
xmlHttpRequest.open('POST','addEmployee',true)
xmlHttpRequest.setRequestHeader("Content-type","application/x-www-form-urlencoded");
xmlHttpRequest.send(dataToSend);
}
}
</script>
<h2>Employee (Add Module)</h2>
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
<button type='button' onclick='valid()'>Add</button>&nbsp;&nbsp;<button type='Button' onclick='CencelAddition()'>Cancel</button></td>
</tr>
</table>
<jsp:include page='/MasterPageBottomSection.jsp' />