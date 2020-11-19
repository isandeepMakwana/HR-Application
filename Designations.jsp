<jsp:include page='/MasterPageTopSection.jsp' />
<script>
function setLeftBar(text){
var designation = document.createElement("p2");
designation.innerHTML=text;
removeATag=document.querySelector(".content-left-panel a");
document.querySelector(".content-left-panel").replaceChild(designation, removeATag);
document.querySelector(".content-left-panel p2").style.fontWeight=1000;
}
function getAll()
{
var designationGridTableBody=document.querySelectorAll("tbody")[0];
var designationGridTableBodyRowTemplate = designationGridTableBody.querySelectorAll("tr")[0];
// remove the ROW from DOM(Document object Model)
designationGridTableBodyRowTemplate.remove();
var designationGridTableColumnsTemplateCollection=designationGridTableBodyRowTemplate.getElementsByTagName("td");
var xmlHttpRequest = new XMLHttpRequest();
xmlHttpRequest.onreadystatechange=function()
{
if(this.readyState==4){
if(this.status==200){
var responseData = this.responseText;
var splits=responseData.split(",");
var cellTemplate;
var k;
var dynamicRow;
var dynamicRowCells;
var placeHolderFor;
for (k=0;k<splits.length;k+=2)
{
dynamicRow= designationGridTableBodyRowTemplate.cloneNode(true);
designationGridTableBody.appendChild(dynamicRow);
dynamicRowCells=dynamicRow.getElementsByTagName("td");
for(var i=0;i<dynamicRowCells.length;i++)
{
cellTemplate=dynamicRowCells[i];
placeHolderFor=cellTemplate.getAttribute("placeHolder");
if(placeHolderFor==null) continue;
if(placeHolderFor=="serialNumberPlaceHolder")cellTemplate.innerHTML=((k/2)+1)+".";
if(placeHolderFor=="designation")cellTemplate.innerHTML=splits[k+1];
if(placeHolderFor=="editOption")cellTemplate.innerHTML="<a href='/stylethree/editDesignation?code="+splits[k]+"'>Edit</a>";
if(placeHolderFor=="deleteOption")cellTemplate.innerHTML="<a href='/styletwo/confirmDeleteDesignation?code="+splits[k]+"'>Delete</a>";
}
}//loop ends
}
}
}
xmlHttpRequest.open('GET','designations',true);
xmlHttpRequest.send();
}
setLeftBar("Designation");
window.addEventListener('load',getAll);
</script>
<div class='content-right-panel-table'>
<h2>Designation</h2>
<table border='1' class="designation-table">
<thead>
<tr>
<th colspan='4' style='text-align:right;padding:5px'>
<a href='/stylethree/DesignationAddForm.jsp'>Add new Designation</a>
</th>
</tr>
<tr>
<th style='width:60px; text-align:center'>S.NO</th>
<th style='width:200px; text-align:center'>Designation</th>
<th style='width:100px; text-align:center'>Edit</th>
<th style='width:100px; text-align:center'>Delete</th>
</tr>
</thead>
<tbody>
<tr>
<td style='text-align:right' placeHolder='serialNumberPlaceHolder'></td>
<td placeHolder='designation'></td>
<td style='text-align:center' placeHolder='editOption'></td>
<td style='text-align:center' placeHolder='deleteOption'></td>
</tr>
</tbody>
</table>
</div>
<jsp:include page='/MasterPageBottomSection.jsp' />