<jsp:include page='/MasterPageTopSection.jsp' />
<link rel='stylesheet' type='text/css' href='/stylethree/css/employees.css'>
<script>
function populateEmployeesGridTable()
{
var xmlHttpRequest = new XMLHttpRequest();
xmlHttpRequest.onreadystatechange=function()
{
if(this.readyState==4)
{
if(this.status==200)
{
responseData= this.responseText;
arrary();
}
}
}
xmlHttpRequest.open('GET','employees',true);
xmlHttpRequest.send()
}
function setLeftBar(text){
var designation = document.createElement("p2");
designation.innerHTML=text;
removeATag=document.querySelectorAll(".content-left-panel a")[1];
document.querySelector(".content-left-panel").replaceChild(designation, removeATag);
document.querySelector(".content-left-panel p2").style.fontWeight=1000;
}
function arrary()
{
function Employee(){
this.employeeId='';
this.name='';
this.designationCode=0;
this.dataOfBirth='';
this.gender='';
this.IsIdian=true;
this.basicSalary=0;
this.panNumber='';
this.aadharCardNumber='';
}
var selectedRow=null;
var employees=[];
function selectEmployee(row, employeeId)
{
if(row==selectedRow)return;
if(selectedRow!=null)
{
selectedRow.style.background='white';
selectedRow.style.color='black';
}
row.style.background='#7C7B7B';
row.style.color='white';
selectedRow=row;
var i;
for(i=0; i<employees.length;i++)
{
if(employees[i].employeeId==employeeId)
{
break;
}
}
var emp=employees[i];
document.getElementById('detailPanel_employeeId').innerHTML=emp.employeeId;
document.getElementById('detailPanel_name').innerHTML=emp.name;
document.getElementById('detailPanel_designation').innerHTML=emp.designation;
document.getElementById('detailPanel_dateOfBirth').innerHTML=emp.dateOfBirth;
document.getElementById('detailPanel_gender').innerHTML=emp.gender;
if(emp.isIndian){
document.getElementById('detailPanel_isIndian').innerHTML='Yes';
}
else
{
document.getElementById('detailPanel_isIdian').innerHTML='NO';
}
document.getElementById('detailPanel_basicSalary').innerHTML=emp.basicSalary;
document.getElementById('detailPanel_panNumber').innerHTML=emp.panNumber;
document.getElementById('detailPanel_aadharCardNumber').innerHTML=emp.aadharCardNumber;
}


//alert(responseData);
var splits=responseData.split(",");
var employee;
var i=0;
var j=0;
for(i=0;splits.length>i;i+=10)
{
employee = new Employee();
employee.employeeId= splits[i];
employee.name=splits[i+1];
employee.designationCode=splits[i+2];
employee.designation=splits[i+3];
employee.dateOfBirth=splits[i+4];
employee.gender=splits[i+5];
employee.isIndian=splits[i+6];
employee.basicSalary=splits[i+7];
employee.panNumber=splits[i+8];
employee.aadharCardNumber=splits[i+9];
employees[j]=employee;
j++;
}

function createDynamicRowClickHandler(rowAddress,employeeId)
{
return function()
{
selectEmployee(rowAddress,employeeId);
};
}

var employeesGridTable = document.getElementById("employeesGridTable");
var employeesGridTableBody= employeesGridTable.getElementsByTagName("tbody")[0];
var employeesGridTableBodyRowTemplate = employeesGridTableBody.getElementsByTagName("tr")[0];
// remove the ROW from DOM(Document object Model)
employeesGridTableBodyRowTemplate.remove();
var employeesGridTableColumnsTemplateCollection=employeesGridTableBodyRowTemplate.getElementsByTagName("td");
var cellTemplate;
var k;
var dynamicRow;
var dynamicRowCells;
var placeHolderFor;
for (k=0;k<employees.length;k++)
{
dynamicRow= employeesGridTableBodyRowTemplate.cloneNode(true);
employeesGridTableBody.appendChild(dynamicRow);
dynamicRowCells=dynamicRow.getElementsByTagName("td");
for(var i=0;i<dynamicRowCells.length;i++)
{
cellTemplate=dynamicRowCells[i];
placeHolderFor=cellTemplate.getAttribute("placeHolderId");
if(placeHolderFor==null) continue;
if(placeHolderFor=="serialNumberPlaceHolder")cellTemplate.innerHTML=(k+1)+".";
if(placeHolderFor=="employeeId")cellTemplate.innerHTML=employees[k].employeeId;
if(placeHolderFor=="name")cellTemplate.innerHTML=employees[k].name;
if(placeHolderFor=="designationCode")cellTemplate.innerHTML=employees[k].designationCode;
if(placeHolderFor=="designation")cellTemplate.innerHTML=employees[k].designation;
if(placeHolderFor=="dateOfBirth")cellTemplate.innerHTML=employees[k].dateOfBirth;
if(placeHolderFor=="isIndian")cellTemplate.innerHTML=employees[k].isIndian;
if(placeHolderFor=="basicSalary")cellTemplate.innerHTML=employees[k].basicSalary;
if(placeHolderFor=="panNumber")cellTemplate.innerHTML=employees[k].panNumber;
if(placeHolderFor=="aadharCardNumber")cellTemplate.innerHTML=employees[k].aadharCardNumber;
if(placeHolderFor=="editOption")cellTemplate.innerHTML="<a href='/stylethree/editEmployee?employeeId="+employees[k].employeeId+"'>Edit</a>";
if(placeHolderFor=="deleteOption")cellTemplate.innerHTML="<a href='/stylethree/confirmDeleteEmployee?employeeId="+employees[k].employeeId+"'>Delete</a>";
}
dynamicRow.onclick=createDynamicRowClickHandler(dynamicRow,employees[k].employeeId);
}// employees population loop ends

}
window.addEventListener('load',populateEmployeesGridTable);
setLeftBar("Employees");
</script>
<h2>Employees</h2>
<div class='employeeGrid'>
<table border='1' id='employeesGridTable'>
<thead>
<tr>
<th colspan='6' class='employeeGridHeader'>
<a href='/stylethree/EmployeeAddForm.jsp'>Add new Employees</a>
</th>
</tr>
<tr>
<th class='employeeGridSNOColumnTitle'>S.NO</th>
<th class='employeeGridIdColumnTitle'>Id.</th>
<th class='employeeGridNameColumnTitle'>Name</th>
<th class='employeeGridEmployeeColumnTitle'>Designations</th>
<th class='employeeGridEditLinkOptionColumnTitle'>Edit</th>
<th class='employeeGridDeleteLinkOptionColumnTitle'>Delete</th>
</tr>
</thead>
<tbody>
<tr style='cursor:pointer'>
<td style='text-align:right' placeHolderId='serialNumberPlaceHolder'></td>
<td placeHolderId='employeeId'></td>
<td placeHolderId='name'></td>
<td placeHolderId='designation'></td>
<td style='text-align:center' placeHolderId='editOption'></td>
<td style='text-align:center' placeHolderId='deleteOption'></td>
</tr>
</tbody>
</table>
</div>
<div style='height:19hv;margin-left:5px;margin-right:5px;margin-bottom:px;margin-top:5px;padding:5px;border:1px solid black;'>
<label style='background-color:gray;color:white;padding-left:5px;padding-right:5px;' onmousedown='background-color:red'>Details</label>
<table border='0' width='100%'>
<tr>
<td>
Employee Id: <span id='detailPanel_employeeId' style='margin-right:30px;'></span></td>
<td>Name :<span id='detailPanel_name' style='margin-right:30px;'></span></td>
<td>Designation : <span id='detailPanel_designation' style='margin-right:30px;'></span></td>
</tr>
<tr>
<td>Date Of Birth : <span id='detailPanel_dateOfBirth' style='margin-right:30px;'></span></td>
<td>Gender :<span id='detailPanel_gender' style='margin-right:30px;'></span></td>
<td>Is Indian: <span id='detailPanel_isIndian' style='margin-right:30px;'></span></td>
</tr>
<tr>
<td>Basic Salary: <span id='detailPanel_basicSalary' style='margin-right:30px;'></span></td>
<td>PAN Number :<span id='detailPanel_panNumber' style='margin-right:30px;'></span></td>
<td>Aadhar card Number : <span id='detailPanel_aadharCardNumber' style='margin-right:30px;'></span></td>
</tr>

</table>
</div>
<jsp:include page='/MasterPageBottomSection.jsp' />