<jsp:include page='/MasterPageTopSection.jsp' />
<script>
function CancelAddition(){
document.getElementById('CancelAdditionForm').submit();
}
function setLeftBar(text){
var designation = document.createElement("p2");
designation.innerHTML=text;
removeATag=document.querySelector(".content-left-panel a");
document.querySelector(".content-left-panel").replaceChild(designation, removeATag);
document.querySelector(".content-left-panel p2").style.fontWeight=1000;
}
function addDesignation(frm){
found=false;
var title = frm.title.value.trim();
var titleErrorSection = document.getElementById('titleErrorSection');
titleErrorSection.innerHTML="";
if(title.length==0){
titleErrorSection.innerHTML="Designation is required don't submit blank";
frm.title.focus();
return found;
}
var xmlHttpRequest = new XMLHttpRequest();
xmlHttpRequest.onreadystatechange=function()
{
if(this.readyState==4)
{
if(this.status==200)
{
var responseData =this.responseText;
if(responseData.trim()!="Designation added")
{
titleErrorSection.innerHTML=responseData;
found=false;
}
if(responseData.trim()=="Designation added")
{
found=true;
}
}
}
}
requestURL = 'addDesignation?title='+title.trim();
xmlHttpRequest.open('GET',requestURL,false);
xmlHttpRequest.send();
return found;
}
setLeftBar("Designation");
</script>
<h1>Designation (Add Module)</h1>
<span id='titleErrorSection' class='error'></span>
<form action="/stylethree/Notification.jsp" onsubmit='return addDesignation(this)'>
<br><label>Designation</label><br>
<input type='text' id='title' name='title' maxlength='35' size='36'>
<br><br>
<button type='submit'>Add</button>
<button type='Button' onclick='CancelAddition()'>Cancel</button>
</form>
<jsp:include page='/MasterPageBottomSection.jsp' />