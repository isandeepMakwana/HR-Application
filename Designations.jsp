<jsp:include page='/MasterPageTopSection.jsp' />
<script>
function setLeftBar(text){
var designation = document.createElement("p2");
designation.innerHTML=text;
removeATag=document.querySelector(".content-left-panel a");
document.querySelector(".content-left-panel").replaceChild(designation, removeATag);
document.querySelector(".content-left-panel p2").style.fontWeight=1000;
}
setLeftBar("Designation");
</script>
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
<td style='text-align:right' placeHolder='serialNumber'>1.</td>
<td placeHolder='Designation'>Administration</td>
<td style='text-align:center' palceHolder='editOption'><a href='/styletwo/editDesignation?code=22'>Edit</a></td>
<td style='text-align:center' palceHolder='deleteOption'><a href='/styletwo/confirmDeleteDesignation?code=22'>Delete</a></td>
</tr>
</tbody>
</table>
<jsp:include page='/MasterPageBottomSection.jsp' />