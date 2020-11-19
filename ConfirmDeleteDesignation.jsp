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
<h2>Designation (Delete Module)</h2>
<form method='post' action='/stylethree/DeleteDesignationNotification.jsp'>
<label>Designation :</label></td>
<b>${title}</b><br>
Are you sure you want to delete this designation ?<br><br>
<table><tr><td>
<button type='submit'>Yes</button>
</form>
</td><td>
<form action='/stylethree/Designations.jsp'>
<button type='submit' >NO</button>
</form>
</td></tr></table>
<jsp:include page='/MasterPageBottomSection.jsp' />